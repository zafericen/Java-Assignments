
public class Players extends Users {
	//last dice to move player and keep the data
	private int lastDice = 0;
	//banker for adding property or giving money
	public static Banker banker;
	//wait turn for jail
	private int waitTurn = 0;
	//one rounds string kept in here and every time a roll dice method called it is 
	//reseting for future use
	public String roundString = "";
	//for knowing the difference between gotojail or freeparking
	public InteractiveSquares waitReason;
	
	public Players(String name, int money) {
		super(name, money);
	}
	
	public int getLastDice() {
		return lastDice;
	}
	public void setLastDice(int lastDice) {
		this.lastDice = lastDice;
	}
	public int getWaitTurn() {
		return waitTurn;
	}

	public void setWaitTurn(int waitTurn) {
		this.waitTurn += waitTurn;
	}
	//rolling last dice and taking action based on that dice 
	public void rollLastDice() {
		roundString = "";
		if (getWaitTurn() == 0) {
			addLocation(getLastDice());
			
		}else {
			//getting that rounds jail string
			roundString = roundString+ " " + getName() + 
					  waitReason.getRoundString() + "count(=" +
					((waitReason.getWaitTurns()+1)-getWaitTurn())+") " ;
			setWaitTurn(-1);
		}
	}
	//checking if the player passed the go square or not
	public void checkGo(int location) {
		if(location > getLocation()) {
			InteractiveSquares go = (InteractiveSquares) Squares.arrOfSquares.get(0);
			setMoney(go.getGiveAmount());
			
		}
		
	}
	//going specific location and checking go square
	@Override
	public void setLocation(int location) {
		int locationp = getLocation();
		super.setLocation(location);
		checkGo(locationp);
		//checking where player land and taking action based on that
		whenLand(Squares.arrOfSquares.get(getLocation()-1));
	}
	//moving amount of dice that rolled
	@Override
	public void addLocation(int location) {
		super.addLocation(location);
		checkGo(location);
		//checking where player land and taking action based on that
		whenLand(Squares.arrOfSquares.get(getLocation()-1));
	}
	//adding property to user from banker	
	@Override
	public void addPropertie(Properties propertie) {
		banker.givePropertie(propertie);
		setMoney(propertie.getCost()*-1);
		super.addPropertie(propertie);
	}
	//going to specific location but not checking go square
	@Override
	public void teleportToLocation(int locationChange) {
		super.teleportToLocation(locationChange);
		//checking where player land and taking action based on that
		whenLand(Squares.arrOfSquares.get(getLocation()-1));
	}
	//getting properties string name for show method
	public String getPropertiesString() {
		String returnString = " ";
		for (Properties propertie : getArrOfProperties()) {
			returnString = returnString + propertie.getName() + ",";
		}
		return returnString.substring(0, returnString.length()-1);
	}
	//paying rent to owner
	public void payRent(Properties propertie) {
		propertie.setRolledDice(getLastDice());
		int rent = propertie.getRent();
		setMoney(rent*-1);
		propertie.getOwner().setMoney(rent);
	}
	//checking where player land and taking action based on that
	public void whenLand(Squares squares) {
		if(squares instanceof Properties ) {
			//property calculation
			Properties propertie = (Properties) squares;
			if (propertie.getOwner().equals(this)){
				roundString = roundString+ " " + getName() + " has " + propertie.getName() + " ";
			}
			else if (propertie.getOwner().equals(banker)) {
				addPropertie(propertie);
				roundString = roundString+ " " + getName() + " bought "+ propertie.getName() + " ";
			}else {
				payRent(propertie);
				roundString = roundString+ " " + getName() + " paid rent for " + propertie.getName() + " ";
			}
		}
		else if (squares instanceof InteractiveSquares) {
			//interactive square calculations
			InteractiveSquares interactiveSquare = (InteractiveSquares) squares;
			if (interactiveSquare instanceof GoToJail) {
				GoToJail goToJail = (GoToJail) interactiveSquare;
				teleportToLocation((goToJail.teleportTo())); ;
			}
			else if ((interactiveSquare instanceof Jail) || (interactiveSquare instanceof FreeParking)) {
				setWaitTurn(interactiveSquare.getWaitTurns());
				waitReason = interactiveSquare;
				String s = interactiveSquare.getWaitTurns()==3 ? " went to jail ":" is in Free Parking ";
				roundString = roundString + " " + getName() + s;

				}
			else if (interactiveSquare instanceof TaxSquares) {
				setWaitTurn(interactiveSquare.getWaitTurns());
				setMoney(interactiveSquare.getGiveAmount());
				roundString = roundString+ " " + getName() + " paid Tax ";
			
				
			}
		}else if (squares instanceof ActionSquares){
			//card calculations
			ActionSquares actionSquare = (ActionSquares) squares;
			Cards card = actionSquare.getTopCard();
			roundString = roundString+ " " + getName() +  " draw " + card.getName();
			card.makeChange(this);
		}
		
	}

	


}
	



public abstract class Cards {
	//variables and static variables that will come handy
	public static Banker banker;
	public static Players player1;
	public static Players player2;
	private String name;
	private int moneyChange;
	private int positionChange;
	private int addChange;
	private boolean efectOnOther = false;

	public Cards(String name,int moneyChange,int positionChange,int addChange) {
		setName(name);
		setMoneyChange(moneyChange);
		setPositionChange(positionChange);
		setAddChange(addChange);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public int getMoneyChange() {
		return moneyChange;
	}


	public void setMoneyChange(int moneyChange) {
		this.moneyChange = moneyChange;
	}


	public int getPositionChange() {
		return positionChange;
	}


	public void setPositionChange(int positionChange) {
		this.positionChange = positionChange;
	}


	public int getAddChange() {
		return addChange;
	}


	public void setAddChange(int addChange) {
		this.addChange = addChange;
	}
	public boolean hasEfectOnOther() {
		return efectOnOther;
	}

	public void setEfectOnOther(boolean efectOnOther) {
		this.efectOnOther = efectOnOther;
	}
	
	public void makeChange(Users user){
		// method to change effected users attributes
		if (getMoneyChange() != 0) {
			if (!hasEfectOnOther()) {
				user.setMoney(getMoneyChange());
				banker.setMoney(getMoneyChange()*-1);	
			}
			else {
				user.setMoney(getMoneyChange());
				if (user.equals(player1)) {
					player2.setMoney(getMoneyChange()*-1);
				}
				else {
					player1.setMoney(getMoneyChange()*-1);
				}
			}
		}
		else if (getPositionChange() != 0) {
			user.teleportToLocation(getPositionChange());

		}
		else if (getAddChange() !=0){
			
			user.setLocation(getAddChange());

		}
	}
	
}

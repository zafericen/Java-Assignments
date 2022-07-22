
public class Banker extends Users{

	public Banker(String name, int money) {
		super(name, money);
	}
	//banker giving property from its list
	public void givePropertie(Properties propertie) {
		getArrOfProperties().remove(propertie);
		setMoney(propertie.getCost());
	}
}

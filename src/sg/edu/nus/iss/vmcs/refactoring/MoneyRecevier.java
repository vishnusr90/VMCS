package sg.edu.nus.iss.vmcs.refactoring;

public abstract class MoneyRecevier{
	
	private MoneyStore moneyStore;

	public MoneyRecevier(MoneyStore moneyStore){
		this.moneyStore = moneyStore;
	}

	public MoneyStore getStore(){
		return this.moneyStore;
	}

	public abstract boolean validateMoney(MoneyAttribute moneyAttr);
	
	public Money recognizeMoney(MoneyAttribute moneyAttr) {
		return getStore().findMoney(moneyAttr);
	}
	
	public Money getMoney(MoneyAttribute moneyAttr){
		if(validateMoney(moneyAttr)){
			return recognizeMoney(moneyAttr);
		}
		return null;
	}
}
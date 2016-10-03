package sg.edu.nus.iss.vmcs.refactoring;


public class CoinReceiver extends MoneyReceiver{

	public final static double minWeight = 5;
	public final static double maxWeight = 50;

	public CoinReceiver(CoinStore coinStore){
		super(coinStore);
	}

	public boolean validateMoney(MoneyAttribute moneyAttr){
		CoinAttribute coinAttributes = null;
		try{
			coinAttributes = (CoinAttribute)moneyAttr;
		}catch(Exception e){

		}
		if(coinAttributes == null) return false;

		if(coinAttributes.getWeight() < minWeight || coinAttributes.getWeight() > maxWeight) return false;
		
		if(this.getStore().findMoneyStoreIndex(coinAttributes) == -1){
			return false;
		}
		return true;
	}
}



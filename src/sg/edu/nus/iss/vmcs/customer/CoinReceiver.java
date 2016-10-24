package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.refactoring.MoneyReceiver;
import sg.edu.nus.iss.vmcs.store.CoinStore;
import sg.edu.nus.iss.vmcs.store.Money;
import sg.edu.nus.iss.vmcs.store.CoinAttribute;
import sg.edu.nus.iss.vmcs.store.MoneyAttribute;
import sg.edu.nus.iss.vmcs.store.StoreIterator;


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
		
		StoreIterator strItr = this.getStore().getIterator();
		strItr.first();
		while(strItr.hasNext()){
			Money strMoney = (Money) strItr.currentItem().getContent();
			if(strMoney.getAttributes().equals(moneyAttr))
				return true;
			strItr.next();
		}
//		if(this.getStore().findMoneyStoreIndex(coinAttributes) == -1){
//			return false;
//		}
		return false;
	}
}



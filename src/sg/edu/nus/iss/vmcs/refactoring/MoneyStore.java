package sg.edu.nus.iss.vmcs.refactoring;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public abstract class MoneyStore extends Store {
	
	public Money findMoney(MoneyAttribute attribute){
		for(StoreItem item: getItems()){
			Money money = (Money) item.getContent();
			if(money.getAttributes().equals(attribute)) return money;
		}	
		return null;
	}

	public int findMoneyStoreIndex(MoneyAttribute attribute){

		
		for(int i=0; i< getStoreSize() ; i++){
			Money money = (Money) getStoreItem(i).getContent();
			if(money.getAttributes().equals(attribute)) return i;
		}	
		return -1;
	}
}



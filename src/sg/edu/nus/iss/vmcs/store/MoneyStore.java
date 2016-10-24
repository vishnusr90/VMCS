package sg.edu.nus.iss.vmcs.store;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public abstract class MoneyStore extends Store {

    public static MoneyAttribute INVALID_COIN_WEIGHT;

//	public Money findMoney(MoneyAttribute attribute){
//		StoreIterator strItr = getIterator();
//		strItr.first();
//		while(strItr.hasNext()){
//			Money money = (Money) strItr.currentItem().getContent();
//			if(money.getAttributes().equals(attribute)) return money;
//			strItr.next();
//		}	
//		return null;
//	}

//	public int findMoneyStoreIndex(MoneyAttribute attribute){
//
//		
//		for(int i=0; i< getSize() ; i++){
//			Money money = (Money) getItem(i).getContent();
//			if(money.getAttributes().equals(attribute)) return i;
//		}	
//		return -1;
//	}

}



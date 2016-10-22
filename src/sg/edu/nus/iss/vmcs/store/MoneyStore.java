package sg.edu.nus.iss.vmcs.store;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public abstract class MoneyStore extends Store {

    public static MoneyAttribute INVALID_COIN_WEIGHT;

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
        
        
	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and then
	 * update the display on the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}.
	 * @return the number of cash transfered.
	 */
	public int transferAll()  {
		int i;
		int cc = 0; // coin quauntity;
		int size = this.getStoreSize();

		CoinStoreItem item;
		for (i = 0; i < size; i++) {
			item = (CoinStoreItem) this.getStoreItem(i);
			cc = cc + item.getQuantity();
			item.setQuantity(0);
		}

		return cc;
	}

}



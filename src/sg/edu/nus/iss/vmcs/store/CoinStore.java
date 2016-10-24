package sg.edu.nus.iss.vmcs.store;

import sg.edu.nus.iss.vmcs.store.MoneyStore;


public class CoinStore extends MoneyStore {
	
	public CoinStore() {
		super();
		
	}

	@Override
	public StoreIterator getIterator() {
		
		if(storeIterator == null){
			return new CoinStoreIterator(this);
		}
		else
			return storeIterator;
	}
	
}
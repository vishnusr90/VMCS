package sg.edu.nus.iss.vmcs.store;


public class NoteStore extends MoneyStore {

	public NoteStore() {
		super();
		
	}

	@Override
	public StoreIterator getIterator() {

		if(storeIterator == null){
			return new NoteStoreIterator(this);
		}
		else
			return storeIterator;
	}
	
	
	

}



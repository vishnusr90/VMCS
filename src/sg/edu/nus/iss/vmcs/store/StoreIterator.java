package sg.edu.nus.iss.vmcs.store;

public abstract class StoreIterator {
	
	private int cursor;
	private Store str;
	
	public StoreIterator(Store str) {
		
		this.cursor = 0;
		this.str = str;
		System.out.println(cursor);
	}

	public void first() {
		
		this.cursor = 0;
		System.out.println("First");
		
	}

	public void next() {
		
		this.cursor++;
		System.out.println("next" + cursor);
	}
	
	public boolean hasNext() {
		if(cursor < 0 || (cursor >= str.getSize()))
			return false;
		else
			return true;
	}
	
	public StoreItem currentItem() {
		System.out.println("current" + cursor);
		return str.getItem(cursor);
	}

}

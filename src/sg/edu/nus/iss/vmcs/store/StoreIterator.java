package sg.edu.nus.iss.vmcs.store;

public class StoreIterator {
	
	private int cursor;
	private final Store str;
	
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
            return cursor < str.getSize();
	}
	
	public StoreItem currentItem() {
		System.out.println("current" + cursor);
		return str.getItem(cursor);
	}

}

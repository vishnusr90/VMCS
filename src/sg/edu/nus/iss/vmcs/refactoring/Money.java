
package sg.edu.nus.iss.vmcs.refactoring;

import sg.edu.nus.iss.vmcs.store.StoreObject;

public abstract class Money extends StoreObject {
		
	private String name;
	private int value;
	private MoneyAttribute attributes;


	public Money(int value, String name, MoneyAttribute attributes){
		this.value = value;
		this.attributes = attributes;
		this.name = name;
	}	

	public int getValue(){
		return value;
	}

	public String getName() {
		return name;
	}

	public MoneyAttribute getAttributes(){
		return attributes;
	}

}
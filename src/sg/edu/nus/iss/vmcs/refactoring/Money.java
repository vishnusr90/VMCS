
package sg.edu.nus.iss.vmcs.refactoring;

import sg.edu.nus.iss.vmcs.store.StoreObject;

public abstract class Money extends StoreObject {
	private double value;
	private MoneyAttribute attributes;

	public Money(double value, MoneyAttribute attributes){
		this.value = value;
		this.attributes = attributes;
	}	

	public double getValue(){
		return value;
	}

	public MoneyAttribute getAttributes(){
		return attributes;
	}

}
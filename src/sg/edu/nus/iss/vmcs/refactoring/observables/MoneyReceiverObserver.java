package sg.edu.nus.iss.vmcs.refactoring.observables;

import sg.edu.nus.iss.vmcs.customer.MoneyReceiver;
import sg.edu.nus.iss.vmcs.store.Money;

public interface MoneyReceiverObserver {
	
	default void  MoneyReceiverStateChanged(MoneyReceiver source, MoneyReceiverState newState){
		
	}
	default void  MoneyRecevied(MoneyReceiver source, Money money){
		
	}
	default void  TotalMoneyReceviedChanged(MoneyReceiver source, int newtotal){
		
	}
}

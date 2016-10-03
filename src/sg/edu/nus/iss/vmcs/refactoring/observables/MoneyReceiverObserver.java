package sg.edu.nus.iss.vmcs.refactoring.observables;

import sg.edu.nus.iss.vmcs.refactoring.*;

public interface MoneyReceiverObserver {
	
	void  MoneyReceiverStateChanged(MoneyReceiver source, MoneyReceiverState newState);
	void  MoneyRecevied(MoneyReceiver source, Money money);
	void  TotalMoneyReceviedChanged(MoneyReceiver source, int newtotal);
	
}

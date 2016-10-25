package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.MoneyAttribute;
import sg.edu.nus.iss.vmcs.store.MoneyStore;
import sg.edu.nus.iss.vmcs.store.Money;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.vmcs.refactoring.observables.MoneyReceiverObserver;
import sg.edu.nus.iss.vmcs.refactoring.observables.MoneyReceiverState;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public abstract class MoneyReceiver{
	
	private List<MoneyReceiverObserver> observers;
	private MoneyStore moneyStore;
	
	/**List of the Money entered during the transaction.*/
	private List<Money> moneyInserted;
	

	public MoneyReceiver(MoneyStore moneyStore){
		this.moneyStore = moneyStore;
		observers = new ArrayList<MoneyReceiverObserver>();
		moneyInserted = new ArrayList<Money>();
	}

	public MoneyStore getStore(){
		return this.moneyStore;
	}

	public abstract boolean validateMoney(MoneyAttribute moneyAttr);
	
	public void subscribe(MoneyReceiverObserver observer){
		this.observers.add(observer);
	}
	
	public void unsubscribe(MoneyReceiverObserver observer){
		this.observers.remove(observer);
	}
	
	
	private void notifyMoneyReceiverStateChanged(MoneyReceiverState newState){
		for(MoneyReceiverObserver o : observers){
			o.MoneyReceiverStateChanged(this, newState);
		}
	}
	private void notifyMoneyRecevied(Money money){
		for(MoneyReceiverObserver o : observers){
			o.MoneyRecevied(this, money);
		}
	}
	
	private void notifyTotalMoneyReceviedChanged(){
		for(MoneyReceiverObserver o : observers){
			o.TotalMoneyReceviedChanged(this, getTotalInserted());
		}
	}
	
	
	
	public void startReceiver()
	{
		notifyMoneyReceiverStateChanged(MoneyReceiverState.Started);
	}
	

	/**
	 * When a coin is received the following will occur:
	 * <ol>
	 * <li>
	 * The Coin Input Box will be instructed to become deactivated&#46;
	 * </li>
	 * <li>
	 * The weight of the Coin will be send to the object Coin for it to 
	 * determine the denomination and value&#46;
	 * </li>
	 * <li>
	 * The Total Money Inserted Display will be updated&#46;
	 * </li>
	 * <li>
	 * If an invalid coin is entered, the Invalid Coin Display will be
	 * instructed to display INVALID COIN&#46;
	 * </li>
	 * <li>
	 * The Transaction Controller will be informed to process the current
	 * transaction based on the money received&#46;
	 * </li>
	 * </ol>
	 * @param weight the weight of the coin received&#46;
	 */
	public void receiveMoney(MoneyAttribute moneyAttribute){
		if(!validateMoney(moneyAttribute)){
			notifyMoneyReceiverStateChanged(MoneyReceiverState.InvalidMoney);
			return;
		}
		Money money = moneyStore.findMoney(moneyAttribute);
		moneyInserted.add(money);
		notifyMoneyRecevied(money);
		notifyTotalMoneyReceviedChanged();
	}

	/**
	 * This method will activate the Coin Input Box so that further coins
	 * can be received.
	 */
	public void continueReceive(){
		notifyMoneyReceiverStateChanged(MoneyReceiverState.Continued);
	}
	
	/**
	 * Instruct the Cash Store to update its totals and then re-set the Total
	 * Money Inserted Display to zero.
	 * @return return TRUE if cash has been stored, else return FALSE.
	 */
	public boolean storeCash(){
		for (Money money : moneyInserted) {
			int index = moneyStore.findMoneyStoreIndex(money.getAttributes());
			if(index == -1) return false;
		    StoreItem item = moneyStore.getItem(index);
		    if(item == null) return false;
		    item.increment();
		}
		resetReceived();
		notifyMoneyReceiverStateChanged(MoneyReceiverState.Completed);
		return true;
	}
	
	/**
	 * This method will deactivate the Coin Input Box in order to stop 
	 * receiving coins.
	 */
	public void stopReceive(){
		notifyMoneyReceiverStateChanged(MoneyReceiverState.Stoped);
	}
	
	
	/**
	 * This method handles the refunding of Coins entered so far to 
	 * the Customer.
	 */
	public void refundCash(){
		if(getTotalInserted()==0)
			return;
		
		notifyMoneyReceiverStateChanged(MoneyReceiverState.Refunded);
		moneyInserted = new ArrayList<Money>();
                resetReceived();
	}
	
	/**
	 * This method reset the coin received input.
	 */
	public void resetReceived(){
		moneyInserted = new ArrayList<Money>();
		//notifyTotalMoneyReceviedChanged();
		notifyMoneyReceiverStateChanged(MoneyReceiverState.Reseted);
	}
	
	/**
	 * This method activates or deactivates the Coin Input Box.
	 * @param active TRUE to activate, FALSE to deactivate the Coin Input Box.
	 */
	public void setActive(boolean active){
		if(active){ 
			notifyMoneyReceiverStateChanged(MoneyReceiverState.Activiated);
		
		}else{
			notifyMoneyReceiverStateChanged(MoneyReceiverState.Disabled);
		}
	}

	/**
	 * This method returns the total money inserted.
	 * @return the total money inserted.
	 */
	public int getTotalInserted() {
		int sum = 0;
		for (Money money : moneyInserted) {
			sum += money.getValue();
		}
		return sum;
	}
	
}
/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sg.edu.nus.iss.vmcs.store.CoinAttribute;

/**
 * This control object implements the coin denomination selection
 * (button presses by the customer) on the Customer Panel when coins
 * are entered for a drink to be dispensed&#46; Action will be performed in
 * corresponding to the button pressed.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class MoneyInputListener implements ActionListener{
	MoneyReceiver moneyReceiver;
	
	/**
	 * This constructor creates an instance of the Money Input Listener
	 * @param moneyReceiver the Money Receiver
	 */
	public MoneyInputListener(MoneyReceiver moneyReceiver){
		this.moneyReceiver=moneyReceiver;
	}
	
	/**
	 * This method performs actions in response to the coin input button being pressed.
	 */
	public void actionPerformed(ActionEvent ev){
		MoneyButton moneyButton=(MoneyButton)ev.getSource();
		moneyReceiver.receiveMoney(moneyButton.getMoney().getAttributes());
	}
}//End of class CoinInputListener
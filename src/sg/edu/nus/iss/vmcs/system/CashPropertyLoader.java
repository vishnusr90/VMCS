/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;



import sg.edu.nus.iss.vmcs.store.*;

/**
 * This control object manages the initialization of the CashStore&#46;
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class CashPropertyLoader extends FilePropertyLoader {

	private static final String NAME_LABEL     = "Name";
	private static final String WEIGHT_LABEL   = "Weight";
	private static final String VALUE_LABEL    = "Value";
	private static final String QUANTITY_LABEL = "Quantity";

	/**
	 * This constructor creates an instance of CashPropertyLoader object.
	 * @param filen the name of the cash property file.
	 */
	public CashPropertyLoader(String filen) {
		super(filen);
	}

	/**
	 * This method reads the data from the hash table and creates a CashStoreItem.
	 * @param index the index of the StoreItem.
	 * @return StoreItem the store item of the given index.
	 */
	public StoreItem getItem (int index) {
		int idx = index + 1;

		String name = getValue( new String(NAME_LABEL + idx));

		String weight = getValue(new String(WEIGHT_LABEL + idx));

		String value = getValue(new String(VALUE_LABEL + idx));
                
                String quantity = getValue(new String(QUANTITY_LABEL + idx));
		
                Coin coin = new Coin(Integer.parseInt(value), name, new CoinAttribute(Double.parseDouble(weight)));

		int qty = Integer.parseInt(value);

		CoinStoreItem item = new CoinStoreItem(coin, qty);
		return item;
	}

	/**
	 * This method updates the hash table with the data from the CashStoreItem.
	 * @param index the index of the item.
	 * @param cashItem the cash store item.
	 */
	public void setItem(int index, StoreItem cashItem) {
		int idx = index + 1;

		CoinStoreItem item = (CoinStoreItem) cashItem;
		Coin cn = (Coin) item.getContent();
		String itn = new String(NAME_LABEL + idx);

		setValue(itn, cn.getName());

		itn = new String(WEIGHT_LABEL + idx);
		setValue(itn, String.valueOf(((CoinAttribute)cn.getAttributes()).getWeight()));

		itn = new String(VALUE_LABEL + idx);
		setValue(itn, String.valueOf(cn.getValue()));

		itn = new String(QUANTITY_LABEL + idx);
		setValue(itn, String.valueOf(item.getQuantity()));
	}
}//End of class CashPropertyLoader
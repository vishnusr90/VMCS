/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.util.ArrayList;

import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreIterator;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.system.MainController;

/**
 * This interface object is part of the Customer Panel&#46; It is used by the Customer to select a drink.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class DrinkSelectionBox extends Panel{
	private ArrayList<DrinkSelectionItem> drinkSelectionItems;
	private TransactionController txCtrl;
	
	/**Array of integers providing identifiers for each selection button.*/
	
	/**
	 * This constructor creates an instance of the object.
	 * @param txCtrl the Transaction Controller
	 */
	public DrinkSelectionBox(TransactionController txCtrl){
		this.txCtrl=txCtrl;
		MainController mainCtrl=txCtrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		int drinkStoreSize=storeCtrl.getStoreSize(Store.DRINK);
		StoreIterator drinkStoreIterator=(StoreIterator) storeCtrl.getStore(Store.DRINK).getIterator();
		drinkStoreIterator.first();
		drinkSelectionItems=new ArrayList<DrinkSelectionItem>();
		
		setLayout(new GridBagLayout());
		for(int i=0;drinkStoreIterator.hasNext();i++){
			StoreItem storeItem=drinkStoreIterator.currentItem();
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)storeItem;
			StoreObject storeObject=drinksStoreItem.getContent();
			DrinksBrand drinksBrand=(DrinksBrand)storeObject;
			String drinksName=drinksBrand.getName();
			int drinksPrice=drinksBrand.getPrice();
			int drinksQuantity=drinksStoreItem.getQuantity();
			DrinkSelectionItem drinkSelectionItem =new DrinkSelectionItem(i,drinksName,drinksPrice,drinksQuantity,true,false);
			drinkSelectionItem.addListener(new DrinkSelectionListener(txCtrl,i));
			add(drinkSelectionItem,new GridBagConstraints(0,i,1,1,1.0,0.0,
				    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
				    new Insets(5,0,0,0),10,0)); 
			drinkSelectionItems.add(drinkSelectionItem);
			drinkStoreIterator.next();
		}
	}
	
	/**
	 * This method updates the stock status, name and price of a drink brand based on the values received.
	 * @param brand the brand of the drink
	 * @param quantity the quantity of the drink
	 * @param price the price of the drink
	 * @param name the name of the 
	 */
	public void update(int brand, int quantity, int price, String name){
		if(drinkSelectionItems==null||drinkSelectionItems.size()==0){
			return;
		}
		DrinkSelectionItem item=drinkSelectionItems.get(0);
		item.setQuantity(quantity);
		item.setPrice(price);
		item.setName(name);
	}
	
	/**
	 * This method will activate or deactivate the drink selection buttons.
	 * @param active TRUE to activate, FALSE to deactivate the drink selection buttons.
	 */
	public void setActive(boolean active){
		if(drinkSelectionItems==null||drinkSelectionItems.size()==0)
			return;
		MainController mainCtrl=txCtrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		for(int i=0;i<drinkSelectionItems.size();i++){
			DrinkSelectionItem item=drinkSelectionItems.get(i);
			StoreItem storeItem=storeCtrl.getStoreItem(Store.DRINK, i);
			int quantity=storeItem.getQuantity();
			item.setState(active);
		}
	}
	
	/**
	 * This method activates or deactivates the drink selection buttons on the Customer Panel.
	 * @param index the index of the drink brand.
	 * @param active TRUE to activate, FALSE to deactivate the drink selection buttons.
	 */
	public void setState(int index, boolean active){
		if(drinkSelectionItems==null||drinkSelectionItems.size()==0||index<-1||index>=drinkSelectionItems.size())
			return;
		drinkSelectionItems.get(index).setState(active);
	}
	
	/**
	 * This method activates or deactivates the drink selection buttons on the Customer Panel.
	 * This method also displays OUT OF STOCK messages where appropriate.
	 * @param index the index of the drink brand.
	 * @param active TRUE to activate, FALSE to deactivate the drink selection buttons.
	 */
	public void setItemState(int index, boolean active){
		if(drinkSelectionItems==null||drinkSelectionItems.size()==0)
			return;
		drinkSelectionItems.get(index).setItemState(active);
	}
	
	
}//DrinkSelectionBox
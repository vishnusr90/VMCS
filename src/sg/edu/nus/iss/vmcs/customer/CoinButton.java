/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.Button;
import sg.edu.nus.iss.vmcs.store.MoneyAttribute;

/**
 * This boundary class CoinButton represent the coin to be displayed on the CustomerPanel.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class CoinButton extends Button{
	private String name="";
	private int value=0;
	private MoneyAttribute attribute;
	
	/**
	 * This constructor create an instance of the CoinButton object.
	 * @param name the name of the button.
	 * @param value the value of the coin.
	 * @param weight the weight of the coin.
	 */
	public CoinButton(String name, int value, MoneyAttribute attribute){
		super(name);
		this.setName(name);
		this.setValue(value);
		this.setAttribute(attribute);
	}

	/**
	 * This method sets the name of the coin button.
	 * @param name the name of the button.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method get the name of the coin button.
	 * @return the name of the button.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the value of the coin.
	 * @param value the value of the coin.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * This method returns the value of the coin.
	 * @return the value of the coin.
	 */
	public int getValue() {
		return value;
	}

        /**
         * @return the attribute
         */
        public MoneyAttribute getAttribute() {
            return attribute;
        }

        /**
         * @param attribute the attribute to set
         */
        public void setAttribute(MoneyAttribute attribute) {
            this.attribute = attribute;
        }
}//End of class CoinButton
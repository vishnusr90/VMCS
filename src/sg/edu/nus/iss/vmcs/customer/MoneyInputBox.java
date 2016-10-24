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
import java.util.Collection;
import java.util.List;

import sg.edu.nus.iss.vmcs.store.MoneyStore;
import sg.edu.nus.iss.vmcs.store.CoinStoreItem;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.CoinAttribute;
import sg.edu.nus.iss.vmcs.store.CoinStore;
import sg.edu.nus.iss.vmcs.store.Money;
import sg.edu.nus.iss.vmcs.store.Note;
import sg.edu.nus.iss.vmcs.store.NoteAttribute;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;

/**
 * This interface object is part of the Customer Panel&#46; It is used to enter
 * Coins into the vending machine.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
@SuppressWarnings("serial")
public class MoneyInputBox extends Panel{
    private List<MoneyButton> btnMoneyButtons;

    
    public MoneyInputBox(List<MoneyReceiver> moneyReceivers){
        int j =0;
        btnMoneyButtons = new ArrayList<>();
        for(MoneyReceiver moneyReceiver : moneyReceivers){
            if(moneyReceiver == null) continue;
            j++;
            int cashStoreSize= moneyReceiver.getStore().getStoreSize();
            StoreItem[] cashStoreItems= moneyReceiver.getStore().getItems();
            
            MoneyInputListener coinInputListener = new MoneyInputListener(moneyReceiver);

            setLayout(new GridBagLayout());
            for(int i=0;i<cashStoreItems.length;i++){
                    Money money = (Money) cashStoreItems[i].getContent();
                    MoneyButton btn = new MoneyButton(money);
                    btn.addActionListener(coinInputListener);
                    add(btn,new GridBagConstraints(i,j,1,1,1.0,0.0,
                                GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
                                new Insets(0,0,0,0),10,8));
                    btnMoneyButtons.add(btn);
            }
            MoneyButton invalidBtn;
            if(moneyReceiver instanceof CoinReceiver){
                invalidBtn = new MoneyButton(Coin.createInvalid());
            }else{
                invalidBtn = new MoneyButton(Note.createInvalid());
            }
            invalidBtn.addActionListener(coinInputListener);
            btnMoneyButtons.add(invalidBtn);
            add(invalidBtn,new GridBagConstraints(cashStoreSize,j,1,1,1.0,0.0,
                        GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
                        new Insets(0,0,0,0),10,8));
        }

    }

    /**
     * This method activates the Coin Input Box if the parameter is TRUE&#46;
     * Otherwise, the Coin Input Box is deactivated.
     * @param active TRUE to activate the CoinInputBox, otherwise, deactivate the CoinInputBox.
     */
    public void setActive(boolean active){
        if(btnMoneyButtons!=null) btnMoneyButtons.forEach((btn) -> {
            btn.setEnabled(active);
        });
    }
}
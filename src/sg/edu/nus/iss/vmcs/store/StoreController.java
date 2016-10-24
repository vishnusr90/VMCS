/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This control object manages changes in CashStore attributes and 
 * the DrinksStore attributes.
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class StoreController {
       
    private final List<MoneyStore> moneyStores;
    private DrinksStore dStore;

    private final PropertyLoader cashLoader;
    private final PropertyLoader drinksLoader;

    /**
     * This constructor creates an instance of StoreController object.
     * @param cashLoader the cash loader.
     * @param drinksLoader the drinks loader.
     */
    public StoreController(
            PropertyLoader cashLoader,
            PropertyLoader drinksLoader) {
        this.cashLoader = cashLoader;
        this.drinksLoader = drinksLoader;
        moneyStores = new ArrayList<>();
    }

    /**
     * This method instantiate the {@link CashStore}, {@link DrinksStore} and initialize it.
     * @throws IOException if fail to initialize stores; reading properties.
     */
    public void initialize() throws IOException {
        moneyStores.add(new CoinStore());
        moneyStores.add(new NoteStore());
        dStore = new DrinksStore();
        initializeStores();
    }


    public CoinStore getCoinStore(){
        return (CoinStore) this.moneyStores.stream().filter((x) -> { return x instanceof CoinStore; }).findFirst().get();
    }

    public NoteStore getNoteStore(){
        return (NoteStore) this.moneyStores.stream().filter((x) -> { return x instanceof NoteStore; }).findFirst().get();
    }
    /**
     * This method initiates the initialization of the {@link DrinkStore} and {@link CashStore}
     * from data read-in from an input file.
     * @throws IOException if fail to initialize stores; reading properties.
     */
    private void initializeStores() throws IOException {
        initializeCashStore();
        initializeDrinkStore();
    }

    /**
     * This method initialize the {@link DrinksStore}.
     * @throws IOException if fail to initialize drinks store; reading properties.
     */
    private void initializeDrinkStore() throws IOException {
        // get the drink file from the environment property file;
        int numOfItems = drinksLoader.getNumOfItems();
        dStore.setStoreSize(numOfItems);

        for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) drinksLoader.getItem(i);
            StoreObject brand = item.getContent();
            StoreObject existingBrand = dStore.findObject(brand.getName());
            if (existingBrand != null) {
                item.setContent(existingBrand);
            }
            dStore.addItem(i, item);
        }
    }

    /**
     * This method initialize the {@link CashStore}.
     * @throws IOException if fail to initialize cash store; reading properties.
     */
    private void initializeCashStore() throws IOException {
        CoinStore store = getCoinStore();
        // get the cash file from the environment property file;
        int numOfItems = cashLoader.getNumOfItems();
        store.setStoreSize(numOfItems);

        for (int i = 0; i < numOfItems; i++) {
            CoinStoreItem item = (CoinStoreItem) cashLoader.getItem(i);
            store.addItem(i, item);
        }
        NoteStore noteStore = getNoteStore();
        noteStore.setStoreSize(4);
        noteStore.addItem(0, new StoreItem(new Note(200, "2$", new NoteAttribute(20, 50) ), 50));
        noteStore.addItem(1, new StoreItem(new Note(500, "5$", new NoteAttribute(22, 52) ), 50));
        noteStore.addItem(2, new StoreItem(new Note(1000, "10$", new NoteAttribute(24, 54) ), 50));
        noteStore.addItem(3, new StoreItem(new Note(5000, "50$", new NoteAttribute(26, 56) ), 50));
    }

    /**
     * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and
     * update the display on the Machinery Simulator Panel.
     * @param money to be stored.
     */
    public void storeMoney(Money money) {
         int idx;
        if(money instanceof Coin){
            idx = getCoinStore().findMoneyStoreIndex(money.getAttributes());
        }else{
            idx = getNoteStore().findMoneyStoreIndex(money.getAttributes());
        }
        this.getStoreItem(Store.COIN, idx).increment();
    }

    /**
     * This method return the total size of the {@link Store} of the given type of {@link Store}.
     * @param type the type of the Store (either CASH or DRINK).
     * @return the size of the store of the given type of Store.
     */
    public int getStoreSize(int type) {
        switch (type) {
            case Store.COIN:
                return getCoinStore().getStoreSize();
            case Store.NOTE:
                return getNoteStore().getStoreSize();
            default:
                return dStore.getStoreSize();
        }
    }

    /**
     * This method returns an array of {@link StoreItem} of the given type of {@link Store}.
     * @param type the type of Store.
     * @return an array of StoreItem.
     */
    public StoreItem[] getStoreItems(int type) {
        switch (type) {
            case Store.COIN:
                return getCoinStore().getItems();
            case Store.NOTE:
                return getNoteStore().getItems();
            default:
                return dStore.getItems();
        }
    }

    /**
     * This method will either:
     * <br>
     * - instruct the {@link CashStore} to update the quantity of a {@link Coin} denomination to new
     * value supplied and update the total cash held in the {@link CashStore}; or
     * <br>
     * - instruct the {@link DrinksStore} to update the drinks stock for a {@link DrinksBrand} required
     * to a new value supplied.
     * @param type the type of Store.
     * @param idx the index of the StoreItem.
     * @param qty the quantity of the StoreItem.
     */
    public void changeStoreQty(int type, int idx, int qty) {
        System.out.println("StoreController.changeStoreQty: type:"+ type+ " qty:"+ qty);
        switch (type) {
            case Store.COIN:
                getCoinStore().setQuantity(idx, qty);
            case Store.NOTE:
                getNoteStore().setQuantity(idx, qty);
            default:
                dStore.setQuantity(idx, qty);
        }
    }

    /**
     * This method returns the {@link StoreItem} with the given {@link Store} type and index of {@link StoreItem}.
     * @param type the type of Store.
     * @param idx the index of the StoreItem.
     * @return the StoreItem.
     */
    public StoreItem getStoreItem(int type, int idx) {
        switch (type) {
            case Store.COIN:
                return getCoinStore().getStoreItem(idx);
            case Store.NOTE:
                return getNoteStore().getStoreItem(idx);
            default:
                return dStore.getStoreItem(idx);
        }
    }

    /**
     * This method will sets the price for the {@link StoreItem} with the given index and price.
     * @param idx the index of the StoreItem.
     * @param pr the price of the StoreItem.
     */
    public void setPrice(int idx, int pr)  {
        DrinksStoreItem item;

        item = (DrinksStoreItem) dStore.getStoreItem(idx);
        DrinksBrand bd;

        bd = (DrinksBrand) item.getContent();

        bd.setPrice(pr);
    }

    /**
     * This method returns the total number of cash held in the {@link CashStore}.
     * @return the total number of cash held.
     */
    public int getTotalCash(){
        int i;
        int size;
        int tc = 0;
        for(MoneyStore store : this.moneyStores){
            size = store.getStoreSize();
            CoinStoreItem item;
            int qty;
            int val;
            Money m;
            for (i = 0; i < size; i++) {
                item = (CoinStoreItem) store.getStoreItem(i);
                qty = item.getQuantity();
                m = (Money) item.getContent();
                val = m.getValue();
                tc = tc + qty * val;
            }
        }
        return tc;
    }

    /**
     * This method will close down the store management function of the vending machine.
     * This involves saving the attributes of the stores to the property file.
     * @throws IOException if fail to save cash properties and drinks properties.
     */
    public void closeDown() throws IOException {
        // save back cash property;
        saveCashProperties();
        saveDrinksProperties();
    }

    /**
     * This method saves the attributes of the {@link CashStore} to the input file.
     * @throws IOException if fail to save cash properties.
     */
    private void saveCashProperties() throws IOException {
        int size = getCoinStore().getStoreSize();
        cashLoader.setNumOfItems(size);
        for (int i = 0; i < size; i++) {
            cashLoader.setItem(i, getCoinStore().getStoreItem(i));
        }
        cashLoader.saveProperty();
    }

    /**
     * This method saves the attributes of the {@link DrinksStore} to the input file.
     * It saves the drink property when simulation is ended.
     * @throws IOException if fail to save drinks properties.
     */
    private void saveDrinksProperties() throws IOException {
        int size = dStore.getStoreSize();
        drinksLoader.setNumOfItems(size);
        for (int i = 0; i < size; i++) {
                drinksLoader.setItem(i, dStore.getStoreItem(i));
        }
        drinksLoader.saveProperty();
    }

    /**
     * This method instructs the {@link DrinksStore} to dispense one drink, and then updates the 
     * {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It returns TRUE or FALSE to indicate whether dispensing
     * was successful&#46;
     * @param idx the index of the drinks to be dispensed&#46;
     */
    public void dispenseDrink(int idx)  {
        DrinksStoreItem item;
        item = (DrinksStoreItem) getStoreItem(Store.DRINK, idx);
        item.decrement();
    }

    /**
     * This method returns a {@link Store} of a specified type (i&#46;e&#46; Cash or Drink)&#46;
     * @param type the type of Store&#46;
     * @return the Store of the specified type&#46;
     */
    public Store getStore(int type) {
        switch (type) {
            case Store.COIN:
                return getCoinStore();
            case Store.NOTE:
                return getNoteStore();
            default:
                return (Store) dStore;
        }
    }

    /**
     * This method instructs the {@link CashStore} to issue a number of {@link Coin} of a specific
     * denomination, and then updates the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It return TRUE
     * or FALSE to indicate whether the change issue was successful&#46;
     * @param idx the index of the Coin&#46;
     * @param numOfCoins the number of Coin to deduct&#46; 
     */
    public void giveChange(int idx, int numOfCoins)  {
        CoinStoreItem item;
        item = (CoinStoreItem) getStoreItem(Store.COIN, idx);
        for (int i = 0; i < numOfCoins; i++)
                item.decrement();
    }
}//End of class StoreController
package sg.edu.nus.iss.vmcs.store;

public abstract class MoneyStore extends Store {

    public Money findMoney(MoneyAttribute attribute){
        StoreIterator strItr = getIterator();
        strItr.first();
        while(strItr.hasNext()){
            Money money = (Money) strItr.currentItem().getContent();
            if(money.getAttributes().equals(attribute)) return money;
            strItr.next();
        }
        return null;
    }

    public int findMoneyStoreIndex(MoneyAttribute attribute){
        for(int i=0; i< getSize() ; i++){
                Money money = (Money) getItem(i).getContent();
                if(money.getAttributes().equals(attribute)) return i;
        }	
        return -1;
    }


    /**
     * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and then
     * update the display on the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}.
     * @return the number of cash transfered.
     */
    public int transferAll()  {
            int i;
            int cc = 0; // coin quauntity;
            int size = this.getSize();
            StoreIterator strItr = getIterator();
            strItr.first();
            while(strItr.hasNext()){
                cc += strItr.currentItem().getQuantity();
                strItr.currentItem().setQuantity(0);
                strItr.next();
            }
            return cc;
    }

}



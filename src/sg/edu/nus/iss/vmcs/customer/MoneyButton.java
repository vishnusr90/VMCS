package sg.edu.nus.iss.vmcs.customer;

import java.awt.Button;
import sg.edu.nus.iss.vmcs.store.Money;

public class MoneyButton extends Button{
    private final Money money;

    public MoneyButton(Money money){
            this.money = money;
            this.setLabel(this.money.getName());
    }

    public Money getMoney(){
            return this.money;
    }

    @Override
    public String getLabel() {
        return this.money.getName();
    }
}
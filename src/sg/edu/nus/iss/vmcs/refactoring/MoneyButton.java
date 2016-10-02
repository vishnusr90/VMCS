package sg.edu.nus.iss.vmcs.refactoring;

import java.awt.Button;

public abstract class MoneyButton extends Button{
	private MoneyAttribute moneyAttribute;

	public MoneyButton(MoneyAttribute moneyAttribute){
		this.moneyAttribute = moneyAttribute;
	}
	
	public MoneyAttribute getMoneyAttribute(){
		return moneyAttribute;
	}
}
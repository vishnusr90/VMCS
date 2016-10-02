package sg.edu.nus.iss.vmcs.refactoring;


public class CoinAttribute implements MoneyAttribute {
	private double weight;
	public double getWeight(){
		return weight;
	}

	public boolean equals(MoneyAttribute moneyAttribute){
		if (moneyAttribute == null) return false;
		if(moneyAttribute instanceof CoinAttribute){
			CoinAttribute coinAttribute = (CoinAttribute) moneyAttribute;
			return (this.getWeight() == coinAttribute.getWeight());
		}
		return false;
	}
}
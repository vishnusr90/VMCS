package sg.edu.nus.iss.vmcs.refactoring;


public class Coin extends Money {

	public Coin(double value, CoinAttribute attributes){
		super(value, attributes);
	}

	public double getWeight(){
		return ((CoinAttribute)this.getAttributes()).getWeight();
	}

}


package sg.edu.nus.iss.vmcs.refactoring;


public class Coin extends Money {

	public Coin(int value,String name, CoinAttribute attributes){
		super(value,name, attributes);
	}

	public double getWeight(){
		return ((CoinAttribute)this.getAttributes()).getWeight();
	}

}

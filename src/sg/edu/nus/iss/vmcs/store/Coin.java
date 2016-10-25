package sg.edu.nus.iss.vmcs.store;


public class Coin extends Money {

    public Coin(int value,String name, CoinAttribute attributes){
            super(value,name, attributes);
    }

    public static Coin createInvalid(){
        return new Coin(-1, "Invalid", new CoinAttribute(-1));
    }

}

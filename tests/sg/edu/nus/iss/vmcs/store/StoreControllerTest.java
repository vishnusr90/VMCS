package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.CashPropertyLoader;
import sg.edu.nus.iss.vmcs.system.DrinkPropertyLoader;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;

public class StoreControllerTest extends TestCase{
	private String propertyFilename="vmcs.properties";
	
	@Before
	public void setup() throws Exception{
		
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testStoreControllerConstructor() throws Exception{
		Environment.initialize(propertyFilename);
		CashPropertyLoader cashLoader =
			new CashPropertyLoader(Environment.getCashPropFile());
		DrinkPropertyLoader drinksLoader =
			new DrinkPropertyLoader(Environment.getDrinkPropFile());
		cashLoader.initialize();
		drinksLoader.initialize();
		//Act
		StoreController storeController= StoreController.getInstance(cashLoader, drinksLoader);
		storeController.initialize();
		//Assert
		assertNotNull(storeController);
		assertNotNull(storeController.getStore(Store.COIN));
		assertNotNull(storeController.getStore(Store.DRINK));
	}
	
	@Test
	public void testInitialize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Act initialize
		storeController.initialize();		
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		
		//Assert
		assertNotNull(cashStore);
		assertNotNull(drinksStore);
	}

	@Test
	public void testInitializeStores() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Act initialize indirect Act initializeStores
		storeController.initialize();
		
		//Get Cash Store and test its elements
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin);
		}
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testInitializeDrinkStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Act initialize indirect Act initializeDrinkStore
		storeController.initialize();
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testInitializeCashStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Act initialize indirect Act initializeCashStore
		storeController.initialize();
		
		//Get Cash Store and test its elements
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			assertNotNull(coin);
		}
	}

	@Test
	public void testStoreCoin() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		storeController.initialize();
		
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			Coin coin1=(Coin)cashStoreItem.getContent();
			//Act storeCoin
			storeController.storeMoney(coin1);
			Coin coin2=(Coin) cashStore.findMoney(coin1.getAttributes());
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
	}

	@Test
	public void testGetStoreSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Initializing the Store
		storeController.initialize();
		
		//Get Cash Store and test its elements
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		//Act getSize and test looping the store with the store size
		int storeSize=storeController.getStoreSize(Store.COIN);
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin);
		}
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getSize and test looping the store with the store size
		storeSize=storeController.getStoreSize(Store.DRINK);
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testGetStoreItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		storeController.initialize();
		CoinStore s = (CoinStore) storeController.getStore(Store.COIN);
		//Act getItems
		for(int i=0;i<s.getSize();i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)s.getItem(i);
			//Assert
			assertNotNull(cashStoreItem);
		}
		
		//Act getItems
		Store d = (Store) storeController.getStore(Store.DRINK);
		for(int i=0;i<d.getSize();i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)d.getItem(i);
			//Assert
			assertNotNull(drinksStoreItem);
		}
	}

	@Test
	public void testChangeStoreQty() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			int qty1=12+i;
			//Act changeStoreQty
			storeController.changeStoreQty(Store.COIN, i, qty1);
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			int qty1=14+i;
			//Act changeStoreQty
			storeController.changeStoreQty(Store.DRINK, i, qty1);
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}

	@Test
	public void testGetStoreItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			//Act getItem
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			//Assert
			assertNotNull(cashStoreItem);
		}
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			//Act getItem
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			//Assert
			assertNotNull(drinksStoreItem);
		}
	}

	@Test
	public void testSetPrice() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		
	    DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			int price1=60+i;
			//Act setPrice
			storeController.setPrice(i, price1);
			DrinksStoreItem drinksStoreItem=((DrinksStoreItem)drinksStore.getItem(i));
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			int price2=drinksBrand.getPrice();
			//Assert
			assertEquals(price1,price2);
		}
	}

	@Test
	public void testGetTotalCash() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
	
		//Act getTotalCash
		int totalCash=storeController.getTotalCash();
		int countTotalCash=0;
                
                countTotalCash = storeController.getMoneyStores().stream().mapToInt(( MoneyStore x) -> {
                    int subSum = 0;
                    x.getIterator().first();
                    while(x.getIterator().hasNext()){
                        Money m = (Money) (x.getIterator().currentItem().getContent());
                        subSum += x.getIterator().currentItem().getQuantity() * m.getValue();
                        x.getIterator().next();
                    }
                    return subSum;
                }).sum();
                
		//Assert
		assertEquals(totalCash,countTotalCash);
	}

	@Test
	public void testTransferAll() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		
	
		
		int totalQ = storeController.getMoneyStores().stream().mapToInt((x)->{
                    int q = 0;
                    x.getIterator().first();
                    while(x.getIterator().hasNext()){
                        q += x.getIterator().currentItem().getQuantity();
                        x.getIterator().next();
                    }
                    return q;
                }).sum();
		
                //Act transferAll
		int numOfCashTransfered= storeController.getMoneyStores().stream().mapToInt((x)->{
                    return x.transferAll();
                }).sum();
	
		//Assert
		assertEquals(totalQ,numOfCashTransfered);
	}

	@Test
	public void testCloseDown() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		int cashStoreSize1=storeController.getStoreSize(Store.COIN);
		int drinksStoreSize1=storeController.getStoreSize(Store.DRINK);
		//Act closeDown
		storeController.closeDown();
		storeController.initialize();
		int cashStoreSize2=storeController.getStoreSize(Store.COIN);
		int drinksStoreSize2=storeController.getStoreSize(Store.DRINK);
		//Assert
		assertEquals(cashStoreSize1,cashStoreSize2);
		assertEquals(drinksStoreSize1,drinksStoreSize2);
	}

	@Test
	public void testDispenseDrink(int idx) throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			int qty1=drinksStoreItem.getQuantity();
			if(qty1==0)
				continue;
			//Act dispenseDrink
			storeController.dispenseDrink(i);
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2+1);
		}
	}

	@Test
	public void testGetStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		//Act getStore
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Assert
		assertNotNull(cashStore);
		assertNotNull(drinksStore);
	}

	@Test
	public void testGiveChange() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		MoneyStore cashStore=(MoneyStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			int qty1=cashStoreItem.getQuantity();
			if(qty1==0)
				continue;
			//Act give change
			storeController.giveChange(i,1);
			cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2+1);
		}
	}
}//End of class StoreControllerTest

package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.vmcs.customer.CoinReceiver;

import sg.edu.nus.iss.vmcs.system.MainController;

public class CashStoreTest extends TestCase{
	private String propertyFilename="vmcs.properties";
	
	@Before
	public void setup() throws Exception{	
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testCashStoreConstructor() throws Exception{
		//CashStore constructor does nothing
		CoinStore cashStore=new CoinStore();
		//Assert
		assertNotNull(cashStore);
	}
	
	@Test
	public void testSetgetSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		//Act getSize
		int storeSize=cashStore.getSize();
		//Act setStoreSize
		cashStore.setStoreSize(storeSize);
		//Assert
		assertEquals(storeSize,cashStore.getSize());
	}

	@Test
	public void testGetItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		//Act getItems
		Iterator storeItems=cashStore.getIterator();
		//Assert
		//assertTrue((storeItems==null||storeItems.length>=0));
	}

	@Test
	public void testAddItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		Coin coin=new Coin(10,"",new CoinAttribute(100));
		CoinStoreItem cashStoreItem=new CoinStoreItem(coin,1);
		//Act addItem
		cashStore.addItem(storeSize, cashStoreItem);
		int storeSize1=cashStore.getSize();
		//Assert
		assertEquals(storeSize,storeSize1);
	}

	@Test
	public void testgetItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		//Act getItem
		StoreItem storeItem=cashStore.getItem(-1);
		//Assert
		assertNull(storeItem);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			//Act getItem
			storeItem=cashStore.getItem(i);
			//Assert
			assertNotNull(storeItem);
		}
	}

//	@Test
//	public void testFindObject() throws Exception{
//		MainController mainCtrl=new MainController(propertyFilename);
//		mainCtrl.initialize();
//		StoreController storeController=mainCtrl.getStoreController();
//		storeController.initialize();
//		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
//		int storeSize=cashStore.getSize();
//		for(int i=0;i<storeSize;i++){
//			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
//			StoreObject storeObject1=cashStoreItem.getContent();
//			//Act findObject
//			StoreObject storeObject2=cashStore.findObject(storeObject1.getName());
//			//Assert
//			assertEquals(storeObject1,storeObject2);
//		}
//	}
	
	@Test
	public void testSetQuantity() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			int qty1=cashStoreItem.getQuantity();
			//Act setQuantity
			cashStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}
	
//	@Test
//	public void testFindCashStoreIndex() throws Exception{
//		MainController mainCtrl=new MainController(propertyFilename);
//		mainCtrl.initialize();
//		StoreController storeController=mainCtrl.getStoreController();
//		storeController.initialize();
//		MoneyStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
//		int storeSize=cashStore.getSize();
//		for(int i=0;i<storeSize;i++){
//			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
//			Coin coin=(Coin)cashStoreItem.getContent();
//			//Act findCashStoreIndex
//			int index=cashStore.findMoneyStoreIndex(coin.getAttributes());
//			//Assert
//			assertTrue(index>=0);
//		}
//	}
	
	@Test
	public void testIsValidWeight() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Act isValidWeight
			boolean isValidWeight= new CoinReceiver(cashStore).validateMoney(coin.getAttributes());
			//Assert
			assertTrue(isValidWeight);
		}
                assertTrue(true);
	}
	
	@Test
	public void testFindCoin() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CoinStore cashStore=(CoinStore)storeController.getStore(Store.COIN);
		int storeSize=cashStore.getSize();
		for(int i=0;i<storeSize;i++){
			CoinStoreItem cashStoreItem=(CoinStoreItem)cashStore.getItem(i);
			Coin coin1=(Coin)cashStoreItem.getContent();
			//Act findCoin
			//Coin coin2 = (Coin) cashStore.findMoney(coin1.getAttributes());
			//Assert
			//assertNotNull(coin2);
		}
	}
}//End of class CashStoreTest
package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class DrinksStoreTest extends TestCase{
	private String propertyFilename="vmcs.properties";
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testDrinksStoreConstructor() throws Exception{
		//DrinksStore constructor does nothing
		DrinksStore drinksStore=new DrinksStore();
		//Assert
		assertNotNull(drinksStore);
	}
	
	@Test
	public void testSetgetSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getSize
		int storeSize=drinksStore.getSize();
		//Act setStoreSize
		drinksStore.setStoreSize(storeSize);
		//Assert
		assertEquals(storeSize,drinksStore.getSize());
	}

	@Test
	public void testGetItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getItems
		//StoreItem[] storeItems=drinksStore.getItems();
		//Assert
		//assertTrue((storeItems==null||storeItems.length>=0));
	}

	@Test
	public void testAddItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getSize();
		DrinksBrand drinksBrand=new DrinksBrand();
		DrinksStoreItem drinksStoreItem=new DrinksStoreItem(drinksBrand,1);
		//Act addItem
		drinksStore.addItem(storeSize, drinksStoreItem);
		int storeSize1=drinksStore.getSize();
		//Assert
		assertEquals(storeSize,storeSize1);
	}

	@Test
	public void testgetItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getItem
		StoreItem storeItem=drinksStore.getItem(-1);
		//Assert
		assertNull(storeItem);
		int storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			//Act getItem
			storeItem=drinksStore.getItem(i);
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
//		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
//		int storeSize=drinksStore.getSize();
//		for(int i=0;i<storeSize;i++){
//			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
//			StoreObject storeObject1=drinksStoreItem.getContent();
//			//Act findObject
//			StoreObject storeObject2=drinksStore.findObject(storeObject1.getName());
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
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getItem(i);
			int qty1=drinksStoreItem.getQuantity();
			//Act setQuantity
			drinksStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}
}//End of class DrinksStoreTest
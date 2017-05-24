package bigbox.db;
import java.util.ArrayList;
import java.util.List;

import bigbox.business.Store;

public interface StoreReader {

	public ArrayList<Store> getAllStores(); 
	
	public void listStores();
	
	public void getStoresByDivision(String dNo);
}

package bigbox.db;
import bigbox.business.Store;

public interface StoreWriter {
	
	public void add(Store store);
	
	public void addStore();
	
	public void delete(String s, String t); 
}

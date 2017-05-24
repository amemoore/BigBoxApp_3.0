package bigbox.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import bigbox.business.Store;
import bigbox.util.Validator;

public class StoreDB implements StoreDAO{
	
	Scanner sc = new Scanner(System.in);
	
	public ArrayList<Store> getAllStores() { 
		
		ArrayList<Store> stores = new ArrayList<>();
		String sql = "SELECT * FROM stores ";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
        	while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
				String address = rs.getString(3);
				String city = rs.getString(4);
				String state = rs.getString(5);
				String zip = rs.getString(6);
				String divNo = rs.getString(7);
				String strNo = rs.getString(8);
				String sales = rs.getString(9);
                Store s = new Store(id, name, address, city, state, zip, divNo, strNo, sales);
                stores.add(s);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
        }
        return stores;
    }
	public void listStores() {
		System.out.println("STORE LIST\n");
		ArrayList<Store> stores=getAllStores();
		Store s = null;
		for (int i = 0; i < stores.size(); i++){
			s = stores.get(i);
			System.out.println(s);
		}
	}
	
	public void addStore() {
		int id = Validator.getInt(sc, "Enter id number:  ");
		String name = Validator.getString(sc, "Enter store name:  ");
		String address = Validator.getString(sc, "Enter store address:  ");
		String city = Validator.getString(sc, "Enter store city:  ");
		String state = Validator.getString(sc, "Enter store state:  ");
		String zip = Validator.getString(sc, "Enter store zip:  ");
		String divNo = Validator.getStringNumeric(sc, "Enter division number:  ", 3);
		String strNo = Validator.getStringNumeric(sc, "Enter store number:  ", 5);
		String sales = Validator.getString(sc, "Enter sales:  ");
		Store s = (new Store(id, name, address, city, state, zip, divNo, strNo, sales));
		add(s);
	}

	public void add(Store store) {
        String sql
                = "INSERT INTO stores (StoreId, DivisionNumber, StoreNumber, Sales, Name, Address, City, State, ZipCode) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try ( Connection connection = DBUtil.getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, store.getId());
            ps.setString(2, store.getName());
            ps.setString(3, store.getAddress());
            ps.setString(4, store.getCity());
            ps.setString(5, store.getState());
            ps.setString(6, store.getZip());
            ps.setString(7, store.getDivNo());
            ps.setString(8, store.getStrNo());
            ps.setString(9, store.getSales());
            ps.executeUpdate();
        	} 
        catch (SQLException e) {
            System.out.println(e);;
        }
    }
	
	public void delete(String divNo, String strNo) 
	{
		String sql = "DELETE FROM stores "
                   + "WHERE DivisionNumber = ? AND StoreNumber = ?";
		try ( Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
        	ps.setString(1, divNo);
        	ps.setString(2, strNo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);;
        }
    }
	public void getStoresByDivision(String dNo) {        
		String sql = "SELECT * "
				+ "FROM stores "
				+ "WHERE DivisionNumber = ?"; 
		try( Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
        	ps.setString(1, dNo);	
            ResultSet rs = ps.executeQuery();
        
        	while (rs.next())
        	{
                int id = rs.getInt(1);
                String name = rs.getString(2);
				String address = rs.getString(3);
				String city = rs.getString(4);
				String state = rs.getString(5);
				String zip = rs.getString(6);
				String divNo = rs.getString(7);
				String strNo = rs.getString(8);
				String sales = rs.getString(9);
                Store s = new Store(id, name, address, city, state, zip, divNo, strNo, sales);
                System.out.println(s);
        	}
        }
         catch (SQLException e) {
            System.out.println(e);
        }
	}
}


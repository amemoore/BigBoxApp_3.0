package bigbox.presentation;

import java.util.Scanner;
import bigbox.db.DAOFactory;
import bigbox.util.Validator;
import bigbox.db.StoreDAO;

public class BigBoxApp {
	
	private static StoreDAO storeDAO = null;
	
	public static void main(String[] args){
		
		//Welcome message
		System.out.println("Welcome to the Big Box App - Iteration #2 using MySQl");
		System.out.println();
		
		storeDAO = DAOFactory.getStoreDAO();
		Scanner sc = new Scanner(System.in);
		String operation = "";
		boolean isValid = false;

		//Loop through each of the menu items (list,division, add, delete, help, exit
		while (isValid == false){
			
			operation = displayMenuAndPrompt(sc,"Enter a command:  ");

			if (operation.equalsIgnoreCase("list")){
				storeDAO.listStores();
			}
			else if (operation.equalsIgnoreCase("div") || operation.equalsIgnoreCase("division")){	
				String dNo = Validator.getStringNumeric(sc, "Enter division number:  ", 3);
				System.out.println();
				storeDAO.getStoresByDivision(dNo);
			}	
			else if (operation.equalsIgnoreCase("add"))
			{
				storeDAO.addStore();
			}
			else if (operation.equalsIgnoreCase("del"))
			{
				String divNo = Validator.getStringNumeric(sc, "Enter division number:  ", 3);
				String strNo = Validator.getStringNumeric(sc, "Enter store number:  ", 5);
				System.out.println();
				storeDAO.delete(divNo, strNo);
			}
			else if (operation.equalsIgnoreCase("help")){
				System.out.println();
			}
			else if(operation.equalsIgnoreCase("exit")){
				System.out.println("Bye!");
				isValid=true;
			}
			else 
				System.out.println("Invalid input.");
				System.out.println();
		}
		sc.close();
	}
	
	public static String displayMenuAndPrompt(Scanner sc,String prompt){
		Validator.displayMenu();
		String operation = Validator.getString(sc, "Enter a command:  ");
		System.out.println();
		return operation;
	}	
}

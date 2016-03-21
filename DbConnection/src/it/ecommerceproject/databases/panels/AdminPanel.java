package it.ecommerceproject.databases.panels;

import it.ecommerceproject.databases.processes.*;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminPanel {

    public AdminPanel(String u) throws SQLException {
        
        Scanner input = new Scanner(System.in);
        
        int chosen = 0;
        
        AccountsManager aManager = new AccountsManager();
        
        do {
            System.out.println("\n1 - Create new user\n2 - Delete a user\n3 - Edit a user\n4 - Print all Users\n5 - Add new product\n6 - Delete a product\n7 - Edit a product\n 8 - Print all products\n9 - Exit\n\n--");
            
            chosen = input.nextInt();
            
            switch(chosen){
                case 1: if(aManager.addUser()) {System.out.println("User successfully created");} break;
                case 2: if(aManager.deleteUser(u) == 0) {System.out.println("User successfully removed");}break;
                case 3: if(aManager.editUser()) {System.out.println("User successfully edited");}break;
                case 4: aManager.printUsers(); break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 8: break;
                case 9: break;
                default: System.out.println(chosen + ": Unknown command");
            }
        } while(chosen != 9);
    }
    
}

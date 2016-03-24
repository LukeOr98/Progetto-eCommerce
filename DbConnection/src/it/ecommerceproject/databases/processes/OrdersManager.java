package it.ecommerceproject.databases.processes;

import it.ecommerceproject.databases.products.*;
import it.ecommerceproject.databases.accounts.*;
import it.ecommerceproject.databases.orders.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//Ordered = 0 --> nel carrello
//Ordered = 1 --> ordinato

public class OrdersManager {
    
    Scanner input = new Scanner(System.in);
    AccountHandler aHandler = new AccountHandler();
    ProductsHandler pHandler = new ProductsHandler();
    OrdersHandler oHandler = new OrdersHandler();

    public OrdersManager() {
    }
    
    public boolean addToCart(String logged) throws SQLException{
        aHandler.open();
        pHandler.open();
        oHandler.open();
        
        boolean done = false;
        String yesNo = "h";
        Product p;
        Account a;
        boolean found = false;
        int id = 0;
        
        do{
            System.out.println("Which product would you like to add? "); String pName = input.nextLine();
            List<Product> products = pHandler.getData();
            for(int i = 0; i<products.size(); i++){
                p = products.get(i);
                if(pName.equals(p.getName())){
                    found = true;
                    id = p.getID();
                }
            }
            if(found){
                List<Account> accounts = aHandler.getData();
                for(int i = 0; i<accounts.size(); i++){
                    a = accounts.get(i);
                    if(a.getUsername().equals(logged)){
                        oHandler.insert(new Order(a.getID(),id,0));
                        done = true;
                    }
                }
            }
            else {
                System.out.println("Product " + pName +" doesn't exists, do you want to retry? [y/n] "); yesNo = input.next();
            }
        }while(yesNo.equals("y"));
        
        aHandler.close();
        pHandler.close();
        oHandler.close();
        
        return done;
    }
    
    public void showCart(String logged) throws SQLException{
        aHandler.open();
        pHandler.open();
        oHandler.open();
        
        Account a;
        Product p;
        Order o;
        int id = 0;
        
        List<Product> products = pHandler.getData();
        List<Account> accounts = aHandler.getData();
        List<Order> orders = oHandler.getData();
        System.out.printf("%5s%20s%20s\n", "ID", "Name", "Description", "Price");
        for(int i = 0; i<accounts.size(); i++){
            a = accounts.get(i);
            if(a.getUsername().equals(logged)){
                id = a.getID();
            }
        }
        for(int i = 0; i<orders.size(); i++){
            o = orders.get(i);
            for(int j = 0; j<products.size(); j++){
                p = products.get(j);
                if((p.getID() == o.getProductID())&&(id == o.getUser())){
                    System.out.printf("%5d%20s%20.2fEUR\n", p.getID(), p.getName(), p.getPrice());
                }
            }
        }
        aHandler.close();
        pHandler.close();
        oHandler.close();
    }
}
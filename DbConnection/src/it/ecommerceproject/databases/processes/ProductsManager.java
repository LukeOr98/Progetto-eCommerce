package it.ecommerceproject.databases.processes;

import it.ecommerceproject.databases.products.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductsManager {
    
    ProductsHandler prodHandler = new ProductsHandler();
    
    Scanner input = new Scanner(System.in);

    public ProductsManager() {
        
    }
    
    public boolean addProduct() throws SQLException {
        
        prodHandler.open();
        
        boolean added = false;
        String yesNo = "h";
        boolean found = false;
        Product p;
        
        do{
            System.out.println("New Product's Name: "); String name = input.nextLine();
            System.out.println("New Product's Description: "); String desc = input.nextLine();
            System.out.println("New Product's Price: "); double price = input.nextDouble();
            List<Product> products = prodHandler.getData();
            for(int i = 0; i < products.size(); i++){
                p = products.get(i);
                if(name.equals(p.getName())){
                    found = true;
                }
            }
                if(!found){
                    try {
                        prodHandler.insert(new Product(name, desc, price,0));
                        added = true;
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                    do{
                        System.out.println("Product successfuly added, do tou want to add another product? [y/n] "); yesNo = input.next();
                    } while(!((yesNo.equals("y"))||(yesNo.equals("n"))));
                }
                else{
                    do{
                        System.out.println("Product " + name + " already exists, do you want to retry? [y/n] "); yesNo = input.next();
                        if(yesNo.equals("y")){
                            found = false;
                        }
                    }while(!((yesNo.equals("y"))||(yesNo.equals("n"))));
                }
        }while(yesNo.equals("y"));
        prodHandler.close();
        
        return added;
    }
    
    public int deleteProduct() throws SQLException{
        
        //removed = 0 --> rimosso
        //removed = 1 --> non esiste
        //removed = 2 --> loggato
        
        prodHandler.open();
        
        int removed = 2;
        boolean found = false;
        int id = 0;
        Product p;
        System.out.println("Name: "); String name = input.next();
        try {
            List<Product> products = prodHandler.getData();
            for(int i = 0; i<products.size(); i++){
                p = products.get(i);
                if(name.equals(p.getName())){
                    found = true;
                    id = p.getID();
                }
            }
            if(!found){
                removed = 1;
            }
                if(found){
                        prodHandler.delete(id);
                        removed = 0;
                    }
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        if(removed == 1){
            System.out.println("Product " + name + " doesn't exists");
        }
        prodHandler.close();
        return removed;
    }
    public boolean editProduct() throws SQLException{
        prodHandler.open();
        
        boolean done = false;
        Product p;
        System.out.println("Name: "); String user = input.next();
        try {
            List<Product> products = prodHandler.getData();
            System.out.println("New Name: "); String nname = input.next();
            System.out.println("New Description: "); String ndesc = input.next();
            System.out.println("New Price: "); double nprice = input.nextDouble();
            for(int i = 0; i<products.size(); i++){
                p = products.get(i);
                if(!done){
                    if(user.equals(p.getName())){
                        p.setName(nname);
                        p.setDescription(ndesc);
                        p.setPrice(nprice);
                        prodHandler.update(p);
                        done = true;
                    }
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        prodHandler.close();
        return done;
    }
    public void printProducts() throws SQLException{
        prodHandler.open();
        
        Product p;
        
        List<Product> products = prodHandler.getData();
        System.out.printf("%5s%20s%20s%20s%13s\n", "ID", "Name", "Description", "Price", "Bought");
        for(int i = 0; i<products.size(); i++){
            p = products.get(i);
            
            System.out.printf("%5d%20s%17s...%20.2fEUR%10d\n", p.getID(), p.getName(), p.getDescription(), p.getPrice(), p.getBought());
        }
    }
    public void printProduct() throws SQLException{
        prodHandler.open();
        
        boolean found = false;
        
        Product p;
        List<Product> products = prodHandler.getData();
        System.out.println("Name: "); String name = input.next();
        for(int i = 0; i<products.size(); i++){
            p = products.get(i);
            if(p.getName().equals(name)){
                System.out.println("\n\n" + p.getName() + "\n\n" + p.getDescription());
                found = true;
            }
        }
        if(!found){
            System.out.println("Product " + name + " doesn't exists\n");
        }
    }
    
}
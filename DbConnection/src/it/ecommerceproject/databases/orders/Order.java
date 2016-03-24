package it.ecommerceproject.databases.orders;

import it.ecommerceproject.util.Identificable;

public class Order extends Identificable{

	
	private int user;
	private int productID;
	
	
	protected Order(int id,int user, int productID){
		setID(id);
		this.user = user;
		this.productID = productID;
	}
	
	
	public Order(int user,int productID){
		this.user = user;
		this.productID = productID;
		setID(-1);
	}


	public int getUser() {
		return user;
	}


	public void setUser(int user) {
		this.user = user;
	}


	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
	

}

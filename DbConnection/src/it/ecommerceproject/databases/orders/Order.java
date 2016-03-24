package it.ecommerceproject.databases.orders;

import it.ecommerceproject.util.Identificable;

public class Order extends Identificable{

	
	private int user;
	private int productID;
        private int ordered;
	
	
	protected Order(int id,int user, int productID, int ordered){
		setID(id);
		this.user = user;
		this.productID = productID;
                this.ordered = ordered;
	}
	
	
	public Order(int user,int productID,int ordered){
		this.user = user;
		this.productID = productID;
                this.ordered = ordered;
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

        public int getOrdered() {
                return ordered;
        }

        public void setOrdered(int ordered) {
                this.ordered = ordered;
        }
	
	
	

}

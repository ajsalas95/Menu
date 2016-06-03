import java.util.ArrayList;

public class MenuModel {
	
	//Item class holds the name and price of each menu item
	class Item {
		private String name; //the name of the menu item
		private double price; //the price of the menu item as a double
		private String textPrice; //the price of the menu item as a String
	
		public Item(String theName, double thePrice){
			name = theName;
			price = thePrice;
		}
		
		/**
		 * sets the text price of the item
		 * @param theTextPrice text price of the item
		 */
		public void setTextPrice(String theTextPrice){
			textPrice = theTextPrice;
		}
		
		/**
		 * gets the name of the item
		 * @return the name of the item
		 */
		public String getItemName(){
			return name;
		}
		
		/**
		 * gets the item price for calculations
		 * @return the price of the item as a double
		 */
		public double getItemPrice(){
			return price;
		}
		
		/**
		 * gets the item price for GUI display purposes
		 * @return the price of the item as a string
		 */
		public String getTextPrice(){
			return textPrice;
		}
		
	}

    private ArrayList<Item> menu; //holds menu data
    private ArrayList<Item> order;//holds order data
    
    public MenuModel(){
    	menu = new ArrayList<Item>();
    	order = new ArrayList<Item>();
    }
    
    //methods to access menu data
    /**
     * adds an item to the menu
     * @param name the name of the menu item
     * @param price the price of the menu item as a double
     * @param textPrice the price of the menu item as a string
     */
    public void addMenuItem(String name, double price, String textPrice){
    	Item s = new Item(name, price);
    	s.setTextPrice(textPrice);
    	menu.add(s);
    }
    
    /**
     * gets the total menu size
     * @return the size of the menu
     */
    public int getNumMenuItems(){
    	return menu.size();
    }
    
    /**
     * gets a specific menu item
     * @param i the location of the menu item to be accessed
     * @return the menu item
     */
    public Item getMenuItem(int i){
    	return menu.get(i);
    }
    
    /**
     * gets the name of a specific menu item
     * @param i the location of the menu item to be accessed
     * @return the name of the menu item
     */
    public String getMenuItemName(int i){
    	return menu.get(i).getItemName();
    }
    
    
    
    //accesses the item in the order
    /**
     * adds an item to the order based off of the menu item's name
     * @param name the name of the item to add to the order
     */
    public void addOrderItem(String name){
    	double price = 0;
    	String textPrice = "";
    	for(int i = 0; i < menu.size(); i++){
    		if(name.equals(menu.get(i).getItemName())){ //looks for the item in menu and then adds it to order
    			price = menu.get(i).getItemPrice();
    			textPrice = menu.get(i).getTextPrice();
    		}
    	}
    	
    	Item s = new Item(name, price);
    	s.setTextPrice(textPrice);
    	order.add(s);
    	
    }
    
    /**
     * gets the number of items in the order
     * @return the number of items in the order
     */
    public int getNumOrderItems(){
    	return order.size();
    }
    
    /**
     * gets a certain order item
     * @param i the location of the order item to be accessed
     * @return the order item
     */
    public Item getOrderItem(int i){
    	return order.get(i);
    }
    
    /**
     * gets the current total of the order
     * @return the bill amount
     */
    public double getBillAmount(){
    	double total = 0;
    	for(int i = 0; i<order.size(); i++){
    		total = total + order.get(i).getItemPrice(); //calculates the total price
    	}
    	return total;
    }
    
    /**
     * gets the name of the order item
     * @param i the location of the order item to be accessed
     * @return the name of the order item
     */
    public String getOrderItemName(int i){
    	return order.get(i).getItemName();
    }
    
    /**
     * gets the order item text price based off of the item name
     * @param name the name of the order item
     * @return the text price of the order item
     */
    public String getOrderItemPrice(String name){
    	for(int i=0; i<menu.size(); i++){ //finds the item text price by going through the menu
    		if(name.equals(menu.get(i).getItemName())){
    			return menu.get(i).getTextPrice();
    		}
    	}
    	
    	return ""; //should never reach this
    }
    
    /**
     * gets the order text price based off a location in the arraylist
     * @param i the location of the item to be accessed
     * @return the order item text price
     */
    public String getOrderItemPrice(int i){
    	return order.get(i).getTextPrice();
    }
    
    /**
     * clears the order
     */
    public void removeOrder(){
    	order.clear();
    }
    
}
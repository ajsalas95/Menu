import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class McPatternsPresenter {
    MenuModel model;
    McPatternsGUI view;
    private File menu;
    
    public McPatternsPresenter(String name){
    	model = new MenuModel();
    	menu = new File(name);
    }
    
    void attachView(McPatternsGUI view) {
        this.view = view;
    }
    
    public void loadMenuItems() {
    	//File menu = new File("menu.txt"); //creates the file
    	try{
			Scanner readMenu = new Scanner(menu); //reads the file
			while(readMenu.hasNextLine()){
				String line = readMenu.nextLine(); //gets the line
				int seperator = line.indexOf('|'); //gets location of the between the item name and price
				String menuItem = line.substring(0, seperator); //menu item name
				String textPrice = line.substring(seperator+1); //menu item text price
				double price = Double.parseDouble(textPrice);   //menu item number price
				model.addMenuItem(menuItem, price, textPrice);  //create menu item
			}
			readMenu.close();
		} 
    	catch (FileNotFoundException e) {
			System.out.println("The menu file does not exist");
		}
    } 
    
    /**
     * checks to see if confirm cancel buttons should be displayed
     * @return true if there are items in the order false otherwise
     */
    public boolean showCancelConfirm(){ 
    	if(model.getNumOrderItems()==0){
    		return false;
    	}
    	return true;
    }
    
    /**
     * gets the names of the menu items to attach to the buttons
     * @return the menu item names for each button
     */
    public String[] getButtonLabels(){
    	String[] labels = new String[model.getNumMenuItems()];
    	for(int i =0; i<labels.length; i++){
    		labels[i] = model.getMenuItemName(i);
    	}
    	return labels;
    }
    
    /**
     * adds item to order
     * @param name the name of the item to add to the order
     */
    public void addToOrder(String name){
    	model.addOrderItem(name);
    }
    
    /**
     * gets the text price of the item
     * @param name the name of the item
     * @return the text price of the name
     */
    public String getTextPrice(String name){
    	return model.getOrderItemPrice(name);
    }
    
    /**
     * gets the current total to add to gui
     * @return the current total of the order
     */
    public String getCurrentTotal(){
    	String roundedAmount = "";
    	String amount = "Total: $" + model.getBillAmount();
    	int deciamlPlace = amount.indexOf(".");
    	if(amount.length() >= deciamlPlace+3) roundedAmount = amount.substring(0, deciamlPlace+3); //display 2 decimal places
    	else roundedAmount = amount.substring(0, deciamlPlace+2) + "0";
    	return roundedAmount;
    }
     
    /**
     * clears the current order
     */
    public void clearOrder(){
    	model.removeOrder();
    }
    
    /**
     * prints the order receipt after the credit card had be verified
     */
    public void printOrder(){
    	String s = "Order Confirmed, submitting to kitchen\nYour Reciept:\n";
    	//System.out.println("Order Confirmed, submitting to kitchen");
    	//System.out.println("Your Reciept:");
    	for(int i = 0; i < model.getNumOrderItems(); i++){
    		//System.out.println("   "+model.getOrderItemName(i) + " $" + model.getOrderItemPrice(i));
    		s = s + "   "+ model.getOrderItemName(i) + " $" + model.getOrderItemPrice(i) + "\n";
    	}
    	s = s + getCurrentTotal();
    	view.printOrder(s);
    	//System.out.println(getCurrentTotal());
    	
    }
    
    
}

public class AmExCC extends CreditCard {

	public AmExCC(String theNumber) {
		super(theNumber);
	}
	
	/**
	 * displays the name of the credit card
	 * @return the name of the credit card
	 */
	public String displayName(){
		return "American Express";
	}
	
	/**
	 * validates the credit card for future cases
	 */
	public void validate(){
		
	}
	
	
}

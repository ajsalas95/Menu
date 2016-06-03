
public class MasterCC extends CreditCard {

	public MasterCC(String theNumber) {
		super(theNumber);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * displays the name of the credit card
	 * @return the name of the credit card
	 */
	public String displayName(){
		return "MasterCard";
	}
	
	/**
	 * validates the credit card for future cases
	 */
	public void validate(){
		
	}

}

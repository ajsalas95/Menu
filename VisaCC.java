
public class VisaCC extends CreditCard {

	public VisaCC(String theNumber) {
		super(theNumber);
	}
	
	/**
	 * displays the name of the credit card
	 * @return the name of the credit card
	 */
	public String displayName(){
		return "VisaCard";
	}
	
	/**
	 * validates the credit card for future cases
	 */
	public void validate(){
		
	}

}

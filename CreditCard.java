
public class CreditCard {
	private String number;
	
	public CreditCard(String theNumber){
		number = theNumber;
	}
	
	public CreditCard check(){
		if(number.charAt(0) == '5'){
			if(number.length() == 16){
				int i = Integer.parseInt(number.substring(1,2));
				if(i>=1 && i<=5){
					return new MasterCC(number);
				}
				
				else {return null;}
			}
			
			else {return null;}
		}
		
		else if(number.charAt(0) == '4'){
			if(number.length() == 16 || number.length() == 13){
				return new VisaCC(number);
			}
			else {return null;}
		}
		
		else if(number.charAt(0) == '3'){
			if(number.length() == 15){
				if(number.charAt(1) == '4'  || number.charAt(1) == '7'){
					return new AmExCC(number);
				}
				
				else {return null;}
			}
			
			else {return null;}
		}
		
		else if(number.charAt(0) == '6'){
			
			if(number.length()==16){
			String sub = number.substring(1, 4);
			if(sub.equals("011")){
				return new DisCC(number);
			}
			 else{return null;}
			}
			
			else{return null;}
		}
		
		else{
			return null;
		}
	}
	
	public String displayName(){
		return "Generic CC";
	}
}

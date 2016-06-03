public class McPatterns {
    public static void main(String[] args){
    	if(args.length == 0){
        	System.out.println("enter a filename");
        }
    	if(args.length>0 ||args.length <2){
        	McPatternsGUI gui = new McPatternsGUI(new McPatternsPresenter(args[0])); //run the program
        }
        
        else{
        	System.out.println("enter a filename");
        }
    
    }
    
    
    	
    
 }
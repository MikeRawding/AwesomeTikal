package resources;



public class InvalidMoveException extends Exception{
		
	private String message;
		
	public InvalidMoveException(String m){
		message = m;
	}		
	
	public String getMessage(){
		return message;
	}	
}


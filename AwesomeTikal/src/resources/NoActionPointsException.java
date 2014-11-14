package resources;

public class NoActionPointsException extends Exception {

	private String message;
	
	public NoActionPointsException(String m){
		message = m;
	}		
	
	public String getMessage(){
		return message;
	}
	
}

package resources;

public class NoOwnerException extends Exception{
	
	private String message;
	
	public NoOwnerException(String m){
		message = m;
	}		
	
	public String getMessage(){
		return message;
	}

}

package resources;

public class UnoccupiedTileException extends Exception{
	
	private String message;
	
	public UnoccupiedTileException(String m){
		message = m;
	}		
	
	public String getMessage(){
		return message;
	}
}

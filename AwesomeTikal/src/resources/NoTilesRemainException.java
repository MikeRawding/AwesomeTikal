package resources;

public class NoTilesRemainException extends Exception{
	
	private String message;
	
	public NoTilesRemainException(String m){
		message = m;
	}		
	
	public String getMessage(){
		return message;
	}
}
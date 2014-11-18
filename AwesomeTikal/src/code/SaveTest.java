package code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveTest {
	public static void saveGame(Board b){
		//GameModel examp = new GameModel();
		System.out.println(b.toString());
		save(b);
	}
	
	public static void save(Serializable objectToSerialize){
		FileOutputStream fos = null;		
		try{
			fos = new FileOutputStream("SaveState.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objectToSerialize);
			oos.flush();
			oos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveTest {
	public static void main(String[] args){
		ExampleStrings examp = new ExampleStrings();
		System.out.println(examp.toString());
		save(examp);
	}
	
	public static void save(Serializable objectToSerialize){
		FileOutputStream fos = null;		
		try{
			fos = new FileOutputStream("SaveState.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objectToSerialize);
			oos.flush();
			oos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}

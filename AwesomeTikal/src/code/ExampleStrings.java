package code;

import java.io.Serializable;

public class ExampleStrings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String str1 = "Example1";
	public String str2 = "Example2";
	public String str3 = "Example3";
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append(str1 + "\n");
		out.append(str2 + "\n");
		out.append(str3 + "\n");
		System.out.println(out);
		return out.toString();
		
	}

}

package boardAction;

import java.io.File;
import java.io.IOException;

public class TestClass {

	public void go() throws IOException{
		System.out.print("FFFF");
		File f = new File("path");
		System.out.print(f.getAbsolutePath());
	}
}

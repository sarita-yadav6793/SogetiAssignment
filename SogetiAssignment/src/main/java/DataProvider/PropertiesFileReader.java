package DataProvider;
import Utils.CommonFunctionsLibrary;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
	public static String propvalue= null;
	public String readDefaultProperties(String key) {
		try (InputStream input = new FileInputStream("configs/default.properties")){
		Properties prop = new Properties();
		prop.load(input);
		propvalue= prop.getProperty(key);
		
	}
	
	catch(Exception e) {
		e.printStackTrace();
	}
		return propvalue;
	}
	
}

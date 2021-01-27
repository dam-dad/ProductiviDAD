package dad.productiviDAD.resourceUtils;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
/**
 * Class to work with resources
 *
 */
public class ResourceUtils {
	/**
	 * Copies the content of a resource as String
	 * 
	 * @param resourcePath	The path of the resource
	 * @return String. The content of the resource if it exists, else null.
	 */
	public static String getResourceAsString(String resourcePath) {
		try {
			return IOUtils.toString(ResourceUtils.class.getResourceAsStream(resourcePath),StandardCharsets.UTF_8);
		}catch(IOException e) {
			return null;
		}
	}
	
	/**
	 * Copies an internal resource and paste it into the desired folder
	 * 
	 * @param resource The path of the resource to copy
	 * @param dest File where it will be copied
	 * @throws Exception
	 */
	public static void copyResourceToFile(String resource,File dest)throws Exception{
		copyInputStreamToFile(FileUtils.class.getResourceAsStream(resource),dest);
	}
//	
//	public static void main(String[] args) {
//		String css=getResourceAsString("/css/ProjectCardStyle.txt");
//		css=css.replaceAll("\\$cardColor", "#E2B6CF");
//		css=css.replaceAll("\\$textColor", "white");
//		System.out.println(css);
//	}
}

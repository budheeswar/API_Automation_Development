package udb.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class CommonUtils {
	public Properties getConfigFile() {

		FileInputStream file = null;
		Properties properties = new Properties();
		String dir = System.getProperty("user.dir");
		try {
			// Provide the path to your properties file
			file = new FileInputStream(dir + "/src/test/resources/config.properties");
			properties.load(file);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the input stream
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}
	public static String getFormatedDate(String format) {
		String sDateFormat;
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Calendar currentDate = Calendar.getInstance();
		sDateFormat = sformat.format(currentDate.getTime());
		return sDateFormat;
	}

}

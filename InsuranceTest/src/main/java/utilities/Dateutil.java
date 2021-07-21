package utilities;

import java.util.Date;


public class Dateutil {

	/*Note - this method returns present date ,time , year, month*/
	public static String getTimeStamp() {
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}
}

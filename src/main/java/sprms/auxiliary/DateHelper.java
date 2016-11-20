package sprms.auxiliary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private DateHelper(){
		
	}
	
	public static Date parseDate(String dateStr){
		
		if(dateStr.equals("")){
			return null;
		}
		
		String temp[] = dateStr.split("/");
		
		String str=temp[2]+"-"+temp[0]+"-"+temp[1];
		
		Date ret = null;
		
		try {
			ret = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}

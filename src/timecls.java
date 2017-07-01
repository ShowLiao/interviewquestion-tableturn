import java.text.SimpleDateFormat;
import java.util.Date;

public class timecls {

	static Date mTime = new Date();
	public timecls() {
		
	}
	
	public static long getLCurrentTime() {
	    return mTime.getTime();
	}
	
	public static boolean diff10Mins(long time) {
		
	    long tenMins = (mTime.getTime() - time);
		
	    if ((tenMins-60*100) > 1)
		return true;
	    else
		return false;
		
	}
	
	public static String getStrCurrentTime() {
	    return mTime.toString();
	}
	
	public static String getDateLong2Str(long time) {
		
	    Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    String dateText = df2.format(date);
            System.out.println(dateText);
            return dateText;
        
	}
	
	public static String getTimeLong2Str(long time) {
		
	    Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            String dateText = df2.format(date);
            System.out.println(dateText);
            return dateText;
        
	}
}

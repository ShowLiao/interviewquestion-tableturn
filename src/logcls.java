
public class logcls {

	private static boolean debug = false;
	
	public static void printLog(String func, String str) {
		
		if (debug)
			System.out.println(func + "-" + str);
		
	}
	
	public static void printInfo(String func, String str) {
		
		System.out.println(func + "-" + str);
		
	}
}

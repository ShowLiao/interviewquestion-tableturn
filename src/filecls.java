import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class filecls {

	File mFile = null;
	static String result = "result.txt";
	static String issues = "issue.txt";
	public filecls() {
		
	}
	
	public static void writeResult(String info) {
		WriteFile(result, info+"\n");
	}
	
	public static void writeIssues(String info) {
		WriteFile(issues, info);
	}
	
//		how to use : filecls.WriteFile("1,1111,3333\n");
	private static boolean WriteFile(String fileName, String info) {
	
		BufferedWriter bw = null;
		FileWriter fw = null;
		logcls.printLog(loglist.writeFile, info);
		
		try {
			
			File file = new File(fileName);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			
	        bw.write(info);
	        
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
			
		} finally {
			
			try {
				
				if(null != bw)
					bw.close();
				
				if (null != fw)
					fw.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
        	
        return true;
	}
	
}

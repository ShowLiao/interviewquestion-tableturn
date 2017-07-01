import java.util.HashMap;

public class server {

	HashMap<Integer, clientAction> hMap = null;
	int oneMin = 60*1000;			//1 mins
	int addLeaveTime = oneMin * 3;	//3 mins
	
	public server() {
		hMap = new HashMap<Integer, clientAction>();
	}
	
	public void setTables(int numbers) {
		
		for (int i=0; i<numbers; i++) {
			
			clientAction client = new clientAction(i);
			client.init();
			
			hMap.put(i, client);
			
		}
	
	}
	
	public void openTable(int id) {		//swipe card
		
		clientAction client = hMap.get(id);
		if (this.checkResult(client))
		client.setSwipCard(true);
		hMap.put(id, client);
		
	}
	
	public void cusTouch(int id) {
		
		clientAction client = hMap.get(id);
		client.setTouch(oneMin);		//assume current time + 1 min
		
	}
	
	public void cusOrder(int id) {
		
		clientAction client = hMap.get(id);
		
		//for the case 5 to case 6 : Client left but no close/swipe_card action for next customer
		if (isClientActionClassEmpty(client)) {		
			
			long tmpTime = client.getStart();
			if (!client.isSwipCard()) {
				tmpTime = client.getTouch();
			}
			client.setEndTime(addLeaveTime);		
			this.endClientAction(client);
			client.setStart(tmpTime);
		}
		
		client.setOrder(true);
		hMap.put(id, client);
		
	}
	
	public void cusPay(int id) {
		
		clientAction client = hMap.get(id);
		client.setPaid(true);
		hMap.put(id, client);
	}
	
	public void closeTable(int id) {
		
		//save data into db/file
		clientAction client = hMap.get(id);
		
		//because simulate so not going to use current time
		//give assume how many mins they spend in the restaurant
		client.setEndTime(addLeaveTime);		
		this.endClientAction(client);
		
	}
	
	public void endClientAction(clientAction client) {
		
		checkResult(client);
		collectResult(client);
		
		//clean class
		client.close();
		hMap.put(client.getId(), client);
		
	}
	
	public void collectResult(clientAction client) {
		
		String str = "";
		long count = 0;
		
		if (0 != client.getStart()) {
			
			count = client.getStart();
			str = client.getStrStart();
			
		} else if (0 != client.getTouch()) {
			
			count = client.getTouch();
			str = client.getStrTouch();
			
		}
	
		long spend = client.getEndTime() - count;
		str = String.valueOf(spend/oneMin);
		
		//example : (1, 3) -> 1:id, 3:how much time spend in the restaurant 
		str = String.valueOf(client.getId()) + "," + str;
		filecls.writeResult(str);
		
	}
	
	public boolean checkResult(clientAction client) {
		
		boolean rc = true;
		if (client.isOrder() && !client.isPaid()) {
			
			String str = client.getTouch()>client.getStart()?client.getStrTouch():client.getStrStart();
			String err = String.valueOf(client.getId()) + ": time :" + str + loglist.didnotpay;
			filecls.writeIssues(err);
			rc = false;
			
		}
		
		return rc;
	}
	
	//for check client class : if didn't have a close/swipe_card action for next customer
	public boolean isClientActionClassEmpty(clientAction client) {
		
		if (client.isOrder()) {
			return false;
		} else if (client.isPaid())
			return false;
		
		return true;
		
	}
}

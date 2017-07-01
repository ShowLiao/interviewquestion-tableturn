

public class client {

	public static void main(String[] args) {
		
		// for simulator client actions
		
		//1.Swipe card from waiter
		//2.Touch panel
		//3.order
		//4.Payment
		
		//Table with table_id
		//note : maybe should add a feature for
		//wait 30 mins if there is no touch action then close/clean data/class and customer have to ask for swipe card again
		//-> the process will be more simple
		
		//result.txt : for save each table that customer spend in the resturant
		//issue.txt : customer didn't pay and leave
		
		server mServer = new server();
		mServer.setTables(3);        //how many tables in the restaurant
		
		//s1 : waiter swipe card -> customer order -> pay -> leave (by waiter click)
		System.out.println("case 1:");
		mServer.openTable(1);
		mServer.cusOrder(1);
		mServer.cusPay(1);
		mServer.closeTable(1);
		
		//s2 : waiter forget swipe card -> find the last touched time. 
		//     customer order -> pay -> leave
		System.out.println("case 2:");
		mServer.cusTouch(2);
		mServer.cusOrder(2);
		mServer.cusPay(2);
		mServer.closeTable(2);
		
		//s3 : only touched but without order
		System.out.println("case 3:");
		mServer.cusTouch(1);
		
		//s4 : waiter swipe card -> customer order -> leave
		System.out.println("case 4:");
		mServer.openTable(1);
		mServer.cusOrder(1);
		mServer.closeTable(1);
		
		//s5:  touched panel -> order -> pay -> don't know when customer leave
		System.out.println("case 5:");
		mServer.cusTouch(1);
		mServer.cusOrder(1);
		mServer.cusPay(1);
		
		//s6: continue for s5, next customer come -> no swipe card -> touch panel ->leave
		System.out.println("case 6:");
		mServer.openTable(1);
		mServer.cusTouch(1);
		
	}

}

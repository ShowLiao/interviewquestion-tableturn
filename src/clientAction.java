

public class clientAction {

	private int id = 0;
	private boolean swipCard = false;
	private long start = 0;
	private long touch = 0;
	private boolean order = false;
	private boolean paid = false;
	private long endtime = 0;
	
	public clientAction(int id) {
		this.id = id;
	}
	
	public void init() {
		this.reset();
	}
	
	public int getId() {
		return id;
	}
	
	public long getStart() {
		return start;
	}
	
	public void setStart() {
		this.start = timecls.getLCurrentTime();
	}
	
	public void setStart(long start) {
		this.start = 0;
	}
	
	public boolean isSwipCard() {
		return swipCard;
	}
	
	public void setSwipCard(boolean swipCard) {
		
		if (!swipCard)
			logcls.printLog(loglist.setSwipCard, String.valueOf(swipCard));
		
		if (this.isOrder() && !this.isPaid())
			logcls.printInfo(loglist.setSwipCard, loglist.didnotpay);
		
		this.reset();
		this.swipCard = swipCard;
		this.setStart();
		
	}
	
	public long getTouch() {
		return touch;
	}
	
	public void setTouch(long touch) {
		this.touch = timecls.getLCurrentTime() + touch;
	}
	
	public void setTouch() {
		this.touch = timecls.getLCurrentTime();
	}
	
	public String getStrTouch() {
	    
		return timecls.getDateLong2Str(this.getTouch());
	}
	
	public String getStrStart() {
	    
		return timecls.getDateLong2Str(this.getStart());
	}

	public boolean isOrder() {
		return order;
	}
	
	public void setOrder(boolean order) {
		this.order = order;
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public long getEndTime() {
		return endtime;
	}
	
	public void setEndTime(long endtime) {
		this.endtime = timecls.getLCurrentTime() + endtime;
	}

//	public void setEndTime() {
//		this.setEndTime(timecls.getLongTime());
//	}
	
	public String getStrEndTime() {
		return timecls.getDateLong2Str(this.getEndTime());
	}
	
	public void reset() {
		
		String func = "reset";
		logcls.printLog(func, loglist.reset);
		
		this.setStart(0);
		this.setOrder(false);
		this.setPaid(false);
		this.setEndTime(0);
		this.setTouch(0);
		
	}
	
	public void close() {
		
		this.setSwipCard(false);
		this.reset();
		
	}
}

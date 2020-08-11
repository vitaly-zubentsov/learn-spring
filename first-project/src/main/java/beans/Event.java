package beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

	private int id;
	private String msg;
	private Date date;
	private DateFormat df;
	
	Event(Date date, DateFormat df ) {
		this.date = date;
		this.df = df;
		Random random = new Random();
		this.id = random.nextInt(100);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", msg=" + msg + ", date=" + df.format(date) + "]";
	}
	
	
	
}

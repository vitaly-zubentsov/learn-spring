package  core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Event {


	private int id;
	
	@Value("some message")
	private String msg;

	@Autowired
	@Qualifier("date")
	private Date date;
	
	@Autowired
	@Qualifier("dateFormat")
	private DateFormat df;

	public Event() { 
		Random random = new Random();
		this.id = random.nextInt(100);
	}
	
//	Event(@Value("Some text") String msg,@Qualifier("date")Date date,@Qualifier("dateFomat") DateFormat df) {
//		this.msg = msg;
//		this.date = date;
//		this.df = df;
//		Random random = new Random();
//		this.id = random.nextInt(100);
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		String s = df.format(date);
		return "Event [id=" + id + ", msg=" + msg + ", date=" + df.format(date) + "]\n";
	}


}

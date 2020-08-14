package beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class Event {

	private int id;
	private String msg;
	private Date date;
	private DateFormat df;

	@Autowired
	Event(@Value("Some text") String msg,Date date,@Qualifier("dateFomat") DateFormat df) {
		this.msg = msg;
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
		return "Event [id=" + id + ", msg=" + msg + ", date=" + df.format(date) + "]\n";
	}


}

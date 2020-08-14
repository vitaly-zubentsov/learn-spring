package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("cashFileEventLogger")
public class CashFileEventLogger extends FileEventLogger{

	int cashSize;
	List<Event> cash = new ArrayList<>(); 

	@Autowired
	CashFileEventLogger(@Value("logs\\log.txt") String fileName, @Value("2")int cashSize) {
		super(fileName);
		this.cashSize = cashSize;
	}

	@Override
	public void logEvent(Event event) throws IOException {
		cash.add(event);

		if (cash.size()==cashSize) {
			writeEventsFromCach();
			cash.clear();
		}
	}

	private void writeEventsFromCach() throws IOException {
		for(Event event:cash) {
			super.logEvent(event);
		}
	}
	
	@PreDestroy
	public void destroy() throws IOException {
		if(!cash.isEmpty()) {
			writeEventsFromCach();
		}
	}
}

package  core.loggers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import core.beans.Event;

@Component
public class CashFileEventLogger extends FileEventLogger{

	@Value("2")
	int cashSize;
	List<Event> cash = new ArrayList<>(); 

	
	@Override
	public void logEvent(Event event) {
		cash.add(event);

		if (cash.size()==cashSize) {
			writeEventsFromCach();
			cash.clear();
		}
	}
	
	

	private void writeEventsFromCach() {
		for(Event event:cash) {
			super.logEvent(event);
		}
		cash.stream().forEach(super::logEvent);
	}
	
	@PreDestroy
	public void destroy() throws IOException {
		if(!cash.isEmpty()) {
			writeEventsFromCach();
		}
	}
}

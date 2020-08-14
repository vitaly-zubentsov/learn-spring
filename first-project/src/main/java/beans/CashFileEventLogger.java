package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class CashFileEventLogger extends FileEventLogger{

	int cashSize;
	List<Event> cash = new ArrayList<>(); 

	CashFileEventLogger(String fileName,int cashSize) {
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
	
	public void destroy() throws IOException {
		if(!cash.isEmpty()) {
			writeEventsFromCach();
		}
	}
}

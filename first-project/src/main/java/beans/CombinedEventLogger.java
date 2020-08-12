package beans;

import java.io.IOException;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger{

	Collection<EventLogger> loggers;

	CombinedEventLogger(Collection<EventLogger> loggers){
		this.loggers = loggers;
	}

	@Override
	public void logEvent(Event event) throws IOException {
		for (EventLogger logger : loggers) {
			logger.logEvent(event);
		}
	}
}

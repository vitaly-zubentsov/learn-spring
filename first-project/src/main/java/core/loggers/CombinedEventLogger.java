package  core.loggers;

import java.util.Collection;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import core.beans.Event;

@Component
public class CombinedEventLogger implements EventLogger{

	@Resource(name = "combinedLoggers")
	private Collection<EventLogger> loggers;

	
	@Override
	public void logEvent(Event event){
		for (EventLogger logger : loggers) {
			logger.logEvent(event);
		}
	}
}

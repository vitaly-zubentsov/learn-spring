package core.spring;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import core.beans.EventType;
import core.loggers.CashFileEventLogger;
import core.loggers.CombinedEventLogger;
import core.loggers.ConsoleEventLogger;
import core.loggers.EventLogger;
import core.loggers.FileEventLogger;


@Configuration
public class LoggersConf {

	@Resource(name="fileEventLogger")
	private FileEventLogger fileEventLogger;
	
	@Resource(name="consoleEventLogger")
	private ConsoleEventLogger consoleEventLogger;
	
	@Resource(name="cashFileEventLogger")
	private CashFileEventLogger cashFileEventLogger;
	
	@Resource(name="combinedEventLogger")
	private CombinedEventLogger combinedEventLogger;
	
	@Bean
    public Collection<EventLogger> combinedLoggers() {
        Collection<EventLogger> loggers = new ArrayList<EventLogger>(2);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }
	
	@Bean
	public Map<EventType,EventLogger> loggerMap() {
		Map<EventType,EventLogger> loggers = new  EnumMap<>(EventType.class);
	   loggers.put(EventType.INFO, consoleEventLogger);
	   loggers.put(EventType.ERROR, combinedEventLogger);
	   return loggers;
	}
	
    @Bean
    public EventLogger defaultLogger() {
        return cashFileEventLogger;
    }


	
}

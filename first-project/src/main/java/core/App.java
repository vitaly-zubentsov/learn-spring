package core;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import core.beans.Client;
import core.beans.Event;
import core.beans.EventType;
import core.loggers.EventLogger;
import core.spring.AppConfig;
import core.spring.LoggersConf;

@Service
public class App {

	@Autowired
	Client client;

	@Resource(name="defaultLogger")
	EventLogger eventLogger;

	@Resource(name="loggerMap")
	Map<EventType,EventLogger> loggers;
	
    public App() {}

	App (Client client, EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
		this.client = client; 
		this.eventLogger = eventLogger;
		this.loggers = loggers;
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ctx.register(AppConfig.class, LoggersConf.class);
		ctx.scan("core");
		ctx.refresh();

		App app = (App) ctx.getBean("app");
		Event event1 = (Event) ctx.getBean("event");
		Event event2 = (Event) ctx.getBean("event");
		Event event3 = (Event) ctx.getBean("event");

		EventType error = EventType.ERROR;
		EventType info = EventType.INFO;

		app.logEvent(event1, error);
		app.logEvent(event2, info);
		app.logEvent(event3, error);


		Event event4 = (Event) ctx.getBean("event");
	//	app.logEvent(event4, null);
		ctx.close();


	}


	public void logEvent(Event event, EventType eventType) {
		if (eventType==null) {
			//Если тип лога не определён, то используем cashFileLogger
			eventLogger.logEvent(event);
		} else {
			//Если тип лога info выводим только в консоль
			//Если тип лога error выводим и в консоль и в файл
			loggers.get(eventType).logEvent(event);
		}

	}
}

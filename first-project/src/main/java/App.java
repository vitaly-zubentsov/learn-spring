import java.io.IOException;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Client;
import beans.Event;
import beans.EventLogger;
import beans.EventType;

public class App {

	Client client;
	EventLogger eventLogger;
	Map<EventType,EventLogger> loggers;

	App (Client client, EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
		this.client = client; 
		this.eventLogger = eventLogger;
		this.loggers = loggers;
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

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
		app.logEvent(event4, null);
		
		ctx.close();


	}

	public void logEvent(Event event, EventType eventType) {

		try {
			if (eventType==null) {
				eventLogger.logEvent(event); //Если тип лога не определён то используем cashFileLogger
			} else {
			switch(eventType) {
			case INFO: //Если тип лога info выводим только в консоль
				loggers.get(eventType).logEvent(event);
				break;
			case ERROR: //Если тип лога error выводим и в консоль и в файл
				loggers.get(eventType).logEvent(event);
				loggers.get(EventType.INFO).logEvent(event);
				break;
			}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

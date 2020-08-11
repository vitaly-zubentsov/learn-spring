import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Client;
import beans.Event;
import beans.EventLogger;

public class App {

	Client client;
	EventLogger eventLogger;

	App (Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

		App app = (App) ctx.getBean("app");
		Event event1 = (Event) ctx.getBean("event");
		Event event2 = (Event) ctx.getBean("event");

		app.logEvent(event1);
		app.logEvent(event2);
		
	}

	public void logEvent(Event event) {
		eventLogger.logEvent(event);
	}
}

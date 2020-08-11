import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Client;
import beans.ConsoleEventLogger;

public class App {

	Client client;
	ConsoleEventLogger consoleEventLogger;

	App (Client client, ConsoleEventLogger consoleEventLogger) {
		this.client = client;
		this.consoleEventLogger = consoleEventLogger;
	}

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

		App app = (App) ctx.getBean("app");

		app.logEvent("Some event for 1");
		app.logEvent("Some event for 2");

	}

	public void logEvent(String messages) {
		consoleEventLogger.logEvent(messages.replace(client.getId(), client.getName()));
	}
}

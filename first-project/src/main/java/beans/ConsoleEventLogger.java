package beans;

import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}

package  core.loggers;

import org.springframework.stereotype.Component;

import core.beans.Event;

@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}

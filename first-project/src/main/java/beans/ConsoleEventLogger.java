package beans;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(String messages) {
        System.out.println(messages);
    }
}

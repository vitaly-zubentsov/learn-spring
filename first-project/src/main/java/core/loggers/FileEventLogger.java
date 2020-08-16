package  core.loggers;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import core.beans.Event;

@Component
public class FileEventLogger implements EventLogger {
	
	@Value("logs\\log.txt")
	private String fileName;
	
	private File file;
	
	
	@PostConstruct
	public void init() throws IOException {
		file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException(
                    "Can't write to file " + fileName);
        } else if (!file.exists()) {
            file.createNewFile();
        }
	}
	
	public void logEvent(Event event) {
			try {
				FileUtils.writeStringToFile(file, event.toString(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}

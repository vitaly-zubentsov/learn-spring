package beans;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger {
	String fileName;
	Event event;

	public FileEventLogger(String fileName){
		this.fileName = fileName;
	}

	public void logEvent(Event event) {
		File file = new File(fileName);
		try {
			FileUtils.writeStringToFile(file, event.toString(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}

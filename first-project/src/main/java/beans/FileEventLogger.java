package beans;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger {
	String fileName;
	File file;

	public FileEventLogger(String fileName){
		this.fileName = fileName;
	}

	public void init() throws IOException {
		file = new File(fileName);
		file.canWrite();
	}
	
	public void logEvent(Event event) throws IOException {
		FileUtils.writeStringToFile(file, event.toString(), true);
		}

}

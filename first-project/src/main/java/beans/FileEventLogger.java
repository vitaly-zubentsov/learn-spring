package beans;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {
	
	String fileName;
	
	File file;
	
	@Autowired
	public FileEventLogger(@Value("logs\\log.txt") String fileName){
		this.fileName = fileName;
	}

	@PostConstruct
	public void init() throws IOException {
		file = new File(fileName);
		file.canWrite();
	}
	
	public void logEvent(Event event) throws IOException {
		FileUtils.writeStringToFile(file, event.toString(), true);
		}

}

package core.spring;

import java.text.DateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import core.beans.Client;


@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

	@Autowired
	private Environment dataFromEnviroment;


	@Bean
	@Scope("prototype")
	public Date date() {
		return new Date();
	}

	@Bean
	public DateFormat dateFormat() {
		return DateFormat.getDateTimeInstance();
	}

	@Bean
	public Client client() {
		Client client = new Client();
		client.setId(dataFromEnviroment.getRequiredProperty("id"));
		client.setName(dataFromEnviroment.getRequiredProperty("name"));
		client.setGreeting(dataFromEnviroment.getProperty("greeting"));
		return client;
	}





}


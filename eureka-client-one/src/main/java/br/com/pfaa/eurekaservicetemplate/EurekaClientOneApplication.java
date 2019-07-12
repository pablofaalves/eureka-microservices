package br.com.pfaa.eurekaservicetemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import br.com.pfaa.eurekaservicetemplate.rest.controller.IGreetingsController;

@SpringBootApplication
@RestController
public class EurekaClientOneApplication implements IGreetingsController {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Value("${spring.application.name}")
	private String appName;
	 
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientOneApplication.class, args);
	}

	@Override
	public String greetings() {
		Application application = eurekaClient.getApplication(appName); 
		StringBuilder builder = new StringBuilder(String.format("Greetings from %s!", application.getName()));
		builder.append("<br>");
		
		builder.append("Other applications registered: <br>");
		eurekaClient.getApplications().getRegisteredApplications().stream()
			.filter(x -> !x.equals(application))
			.forEach(x -> builder.append(" - ").append(x.getName()).append("<br>"));
		
		return builder.toString();
	}

}

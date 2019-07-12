 	package br.com.pfaa.eurekaservicetemplate.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface IGreetingsController {

	@GetMapping("/greetings")
    String greetings();
}

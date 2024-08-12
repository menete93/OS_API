package com.menete.ORDEM_SERVICO.domain.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	
public class helloController {

	
	 @GetMapping("/")
	    public String hello() {
	        return "hello"; // Nome do arquivo HTML (index.html)
	    }	
	
}

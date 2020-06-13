package com.code.advanceHibernateSpringProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecurityController {

	@GetMapping("/loginForm")
	public String managerLogin() {
		
		return "login-form";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "authority-exception";
		
	}

	
}

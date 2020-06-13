package com.code.advanceHibernateSpringProject.Configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class JavaDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		// Adding java configration class
		return new Class[] {JavaServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		// Adding url pattern
		return new String [] {"/"};
	}

}

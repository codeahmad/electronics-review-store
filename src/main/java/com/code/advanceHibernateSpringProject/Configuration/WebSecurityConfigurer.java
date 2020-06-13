package com.code.advanceHibernateSpringProject.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	// Creating dataSource object
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()

				.antMatchers("/list").permitAll()
				.antMatchers("/deleteProduct/**")
				.hasRole("CEO")

				.antMatchers("/addProduct/**")
				.hasAnyRole("CEO","MANAGER")
				
				.antMatchers("/updateProduct/**")
				.hasAnyRole("CEO","MANAGER","EMPLOYEE")
				
				.and().
				formLogin().loginPage("/loginForm")
				.loginProcessingUrl("/checkUserCredentials").permitAll()
				.and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
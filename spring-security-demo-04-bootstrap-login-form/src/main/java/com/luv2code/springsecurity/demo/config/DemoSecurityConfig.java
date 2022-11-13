package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		// Add our users for in memory authentication
//		
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//		.withUser(users.username("John").password("test123").roles("EMPLOYEE"))
//		.withUser(users.username("Mary").password("test123").roles("MANAGER"))
//		.withUser(users.username("Susan").password("test123").roles("ADMIN"));
//		
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.authorizeRequests()
//		.anyRequest()
//		.authenticated()
//		.and().formLogin()
//		.loginPage("/showMyLoginPage")
//		.loginProcessingUrl("/authenticateTheUser")
//		.permitAll();
//		
//	}
//}

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		/* Note: {noop} is the id for the password encoding algorithm to be used.
		 * {noop} means no operations i.e. simply store the password as plain text
		 * If we don't put it before the actual password in the below code then we will
		 * get:
		 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
		 * 
		 */
		UserDetails john = User.builder()
				.username("John")
				.password("{noop}test123")
				.roles("EMPLOYEE")
				.build();
		
		UserDetails mary = User.builder()
                .username("Mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

		UserDetails susan = User.builder()
                .username("Susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "ADMIN")
                .build();
		
		return new InMemoryUserDetailsManager(john, mary, susan);
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// By default the path to validate the credentials is: "/authenticateTheUser"
		return http.authorizeHttpRequests(configurer -> 
				configurer.anyRequest().authenticated()
				)
				.formLogin(configurer-> 
						configurer.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.permitAll())
						.build();
	}
	
}

package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
}

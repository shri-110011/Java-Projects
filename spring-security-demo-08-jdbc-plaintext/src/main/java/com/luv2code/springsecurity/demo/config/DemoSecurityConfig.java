package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	// add a reference to our security data source
//	private DataSource securityDataSource;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		// use jdbc authentication ... oh yeah!!!
//		
//		auth.jdbcAuthentication().dataSource(securityDataSource);
//		
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.authorizeRequests()
///*		 .anyRequest().authenticated() says that request for any resource path must be authenticated
// * 		Note: antMatchers can't come if we use anyRequest.
// */
////			.anyRequest().authenticated()
//			.antMatchers("/").hasRole("EMPLOYEE")
//			.antMatchers("/leaders/**").hasRole("MANAGER")
//			.antMatchers("/systems/**").hasRole("ADMIN")
//		.and()
//		.formLogin()
//			.loginPage("/showMyLoginPage")
//			.loginProcessingUrl("/authenticateTheUser")
//			.permitAll()
//		.and()
//			.logout()
//			.permitAll()
//		.and()
//		.exceptionHandling()
//			.accessDeniedPage("/access-denied");
//		
//	}
//}

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig {
	
	@Autowired
	// add a reference to our security data source
	private DataSource securityDataSource;

	
	@Bean
	public JdbcUserDetailsManager userDetailsManager() {
		return new JdbcUserDetailsManager(securityDataSource);
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// By default the path to validate the credentials is: "/authenticateTheUser"
		return http.authorizeHttpRequests(configurer ->
				configurer
//					.anyRequest().authenticated()
					.antMatchers("/").hasRole("EMPLOYEE")
					.antMatchers("/leaders/**").hasRole("MANAGER")
					.antMatchers("/systems/**").hasRole("ADMIN")
				)
				.formLogin(configurer-> 
						configurer.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.permitAll())
				.logout(configurer -> 
					configurer.permitAll()
				)
				.exceptionHandling(configurer->
						configurer.accessDeniedPage("/access-denied"))
				.build();
	}
	
}
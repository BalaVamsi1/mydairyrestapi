package com.bala.mydairyrestapi.security;

import javax.sql.DataSource;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsManager configureDataSource(DataSource dataSource) {
		UserDetailsManager userDetailsManger=new JdbcUserDetailsManager(dataSource);
		return userDetailsManger;
	}
	
	@Bean
	public SecurityFilterChain authorizeHttpRequests(HttpSecurity http) throws Exception
	{
		http
		.authorizeHttpRequests(
				
				(authorize) -> {
					authorize
					.requestMatchers(HttpMethod.DELETE, "/entries/**").hasAuthority("ROLE_ADMIN")
					.requestMatchers(HttpMethod.PUT, "/entries/**").hasAuthority("ROLE_ADMIN,ROLE_MANAGER")
					
					.anyRequest().authenticated();
					}
				
				)
		.httpBasic()
		.and()
		.csrf().disable()
		;
	
		
		
		
		return http.build();
	}
	
	
	//user password roles
	//in mermory password type
//	@Bean
//	public InMemoryUserDetailsManager configureInMemoryUsers() {
//		
//		//create userdetails
//		UserDetails user1=User.builder().username("BalaVamsi").password("{noop}Lanke").roles("ADMIN","MANAGER","EMPLOYEE").build();
//		UserDetails user2=User.builder().username("sentham").password("{noop}bikes").roles("MANAGER","EMPLOYEE").build();
//		UserDetails user3=User.builder().username("sangeetha").password("{noop}jerry123").roles("EMPLOYEE").build();
//		
//		
//		//create inmemoryuserdetailsmanager object
//		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user1,user2,user3);
//		
//		
//		return inMemoryUserDetailsManager;
		
	//}

}

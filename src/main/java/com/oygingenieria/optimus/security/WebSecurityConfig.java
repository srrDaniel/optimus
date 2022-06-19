package com.oygingenieria.optimus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.oygingenieria.optimus.services.UsuarioImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{

	@Autowired
	private UsuarioImp userDetailsService;
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder()
	{ 
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
				.antMatchers("/", "/landing/**","/dashboard/**", "/auth/**","/email/**", "/errores/**")
				// Las vistas públicas no requieren autenticación
					.permitAll()
				// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest()
					.authenticated()
				.and()
					// El Formulario de Login no requiere autenticación
					.formLogin()
					.loginPage("/auth/login")
					.permitAll()
					.defaultSuccessUrl("/optimus/")
					.failureUrl("/auth/login?error=true")
					.usernameParameter("username")
                .and()
					.logout()
					.permitAll()
					.logoutSuccessUrl("/")
				.and()
	                .exceptionHandling()
	                .accessDeniedPage("/errores/errors-403"); 
		
	}
	
}

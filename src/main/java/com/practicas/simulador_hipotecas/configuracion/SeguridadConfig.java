package com.practicas.simulador_hipotecas.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.practicas.simulador_hipotecas.servicio.ClienteServicio;



@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(clienteServicio).passwordEncoder(encoder);

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Evitar inyeccion de codigo malicioso
		http.authorizeRequests()
				.antMatchers("/").hasAuthority("CLIENTE")
				.anyRequest().permitAll().and()
				.formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll()

				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
				

		
	}

}

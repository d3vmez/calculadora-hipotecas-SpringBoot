package com.practicas.simulador_hipotecas.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	/**
	 * Crea un {@link BCryptPasswordEncoder} para ser utlizado por la aplicacion
	 * 
	 * @return Un {@link BCryptPasswordEncoder} 
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

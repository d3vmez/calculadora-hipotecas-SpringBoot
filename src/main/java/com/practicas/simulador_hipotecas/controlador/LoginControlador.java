package com.practicas.simulador_hipotecas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.practicas.simulador_hipotecas.utilidades.RutaUtil;

/**
 * 
 * Controlador para el login
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Controller
public class LoginControlador {

	@GetMapping(RutaUtil.RUTA_LOGIN)
	public String login() {
		return "login";
	}
	
}

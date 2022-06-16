package com.practicas.simulador_hipotecas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.utilidades.RutaUtil;

@Controller
public class RegistroControlador {

	@GetMapping(RutaUtil.RUTA_REGISTRO)
	public String registro(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "registro";
	}
}

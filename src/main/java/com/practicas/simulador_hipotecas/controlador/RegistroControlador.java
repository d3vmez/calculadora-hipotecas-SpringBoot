package com.practicas.simulador_hipotecas.controlador;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.modelo.Rol;
import com.practicas.simulador_hipotecas.servicio.*;
import com.practicas.simulador_hipotecas.utilidades.RutaUtil;

@Controller
public class RegistroControlador {

	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private RolServicio rolServicio;

	@GetMapping(RutaUtil.RUTA_REGISTRO)
	public String verRegistro(Model model) {
		String existeEmail = (String) model.getAttribute("existeEmail");
		String errorClave = (String) model.getAttribute("errorClave");
		Cliente cliente = (Cliente) model.getAttribute("cliente");
		if (existeEmail != null) {
			model.addAttribute("cliente", cliente);
			model.addAttribute("existeEmail", existeEmail);	
		}else if (errorClave != null){
			model.addAttribute("cliente", cliente);
			model.addAttribute("errorClave", errorClave);		
		}else {
			model.addAttribute("cliente", new Cliente());
		}
		return "registro";
	}

	@PostMapping(RutaUtil.RUTA_REGISTRO_SUBMIT)
	public String submitRegistro(@ModelAttribute("cliente") Cliente cliente, Model model,
			RedirectAttributes redirectAttributes, @RequestParam(value = "clave2", required = false) String clave2) {
		// Comprobar que no exista el email
		if (clienteServicio.existeEmail(cliente.getEmail())) {
			redirectAttributes.addFlashAttribute("existeEmail", "Ya existe un cliente con este email");
			redirectAttributes.addFlashAttribute("cliente", cliente);
			return "redirect:" + RutaUtil.RUTA_REGISTRO;
		}

		if (!clave2.equals(cliente.getClave())) {
			redirectAttributes.addFlashAttribute("errorClave", "Las contraseñas no coinciden");
			redirectAttributes.addFlashAttribute("cliente", cliente);
			return "redirect:" + RutaUtil.RUTA_REGISTRO;
		}

		// Asignar rol CLIENTE al cliente
		Rol rol = new Rol();
		Optional<Rol> rolOp = rolServicio.obtener(1);
		if (!rolOp.isEmpty()) {
			rol = rolOp.get();
			cliente.setRol(rol);
		}
		// Asignar fecha de alta
		java.util.Date fecha = new java.util.Date();
		java.sql.Date fechaSQl = new Date(fecha.getTime());
		cliente.setFechaAlta(fechaSQl);
		clienteServicio.guardar(cliente, true);
		return "redirect:" + RutaUtil.RUTA_LOGIN;
	}
}

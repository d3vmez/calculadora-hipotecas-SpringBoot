package com.practicas.simulador_hipotecas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.servicio.HipotecaServicio;
import com.practicas.simulador_hipotecas.utilidades.RutaUtil;
import com.practicas.simulador_hipotecas.modelo.Amortizacion;

@Controller
public class HipotecaControlador {
	
	@Autowired
	private HipotecaServicio hipotecaServicio;
	
	
	@GetMapping(path= {RutaUtil.RUTA_HIPOTECA})
	public String mortgage(Model model) {
		
		model.addAttribute("hipoteca", new Hipoteca());
		
		
		return "index";
	}
	
	@SuppressWarnings("static-access")
	@PostMapping(RutaUtil.RUTA_HIPOTECA_SUBMIT)
	public String mortgageSubmit(@ModelAttribute("hipoteca") Hipoteca hipoteca, Model model) {
		
		// validacion el importe inicial no puede ser inferior al 10% del precio del inmueble ni inferior al total del importe abonado al inicio
		if(hipoteca.getCapitalInmueble() * 0.1 > hipoteca.getCapitalAportado() || hipoteca.getCapitalInmueble() < hipoteca.getCapitalAportado()) {
			model.addAttribute("errorImporte", "Este importe tiene que ser superior al 10% del precio del inmueble y no mayor");
			return "index";
		}
		
		hipoteca.setTotalIntereses(0);
		hipotecaServicio.calcularCuota(hipoteca);		
		hipotecaServicio.calcularAmortizaciones(hipoteca);
		System.out.println(hipoteca.getTipoInteres().toString());
	
		model.addAttribute("amortizaciones", hipoteca.getAmortizaciones());
		model.addAttribute("hipoteca", hipoteca);
		System.out.println();
		
		return "index";
	}
	
	

}

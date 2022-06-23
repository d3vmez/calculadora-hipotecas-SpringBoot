package com.practicas.simulador_hipotecas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practicas.simulador_hipotecas.modelo.Hipoteca;
import com.practicas.simulador_hipotecas.modelo.Simulacion;
import com.practicas.simulador_hipotecas.servicio.impl.HipotecaFijaServicio;
import com.practicas.simulador_hipotecas.servicio.impl.HipotecaVariableServicio;
import com.practicas.simulador_hipotecas.servicio.impl.SimulacionServicio;
import com.practicas.simulador_hipotecas.utilidades.RutaUtil;

@Controller
public class HipotecaControlador {

	@Autowired
	private HipotecaFijaServicio hipotecaFijaServicio;

	@Autowired
	private HipotecaVariableServicio hipotecaVariableServicio;

	@Autowired
	private SimulacionServicio simulacionServicio;

	@GetMapping(path = { RutaUtil.RUTA_INICIO })
	public String mortgage(Model model) {
		System.out.println("entro");

		if (model.getAttribute("amortizaciones2") != null) {
			model.addAttribute("amortizaciones", model.getAttribute("amortizaciones2"));
			model.addAttribute("hipoteca2", model.getAttribute("hipoteca"));
		}

		if(model.getAttribute("porcentaje")!=null) {
			model.addAttribute("infoPorcentaje","Con los datos que has introducido hay un "+model.getAttribute("porcentaje")+"% de posibilidades"
					+ "de que sea más caro a tipo fijo");
		}
		
		model.addAttribute("hipoteca", new Hipoteca());

		return "index";
	}

	@SuppressWarnings("static-access")
	@PostMapping(RutaUtil.RUTA_HIPOTECA_SUBMIT)
	public String mortgageSubmit(@ModelAttribute("hipoteca") Hipoteca hipoteca, Model model,
			RedirectAttributes redirectAttributes) {

		// validacion el importe inicial no puede ser inferior al 10% del precio del
		// inmueble ni inferior al total del importe abonado al inicio
		if (hipoteca.getCapitalInmueble() * 0.1 > hipoteca.getCapitalAportado()
				|| hipoteca.getCapitalInmueble() < hipoteca.getCapitalAportado()) {
			model.addAttribute("errorImporte",
					"Este importe tiene que ser superior al 10% del precio del inmueble y no mayor");
			return "index";
		}

		float interes = hipotecaFijaServicio.calcularTasaInteres(hipoteca);
		hipoteca.setTasaInteres(interes);

		hipoteca.setTotalIntereses(0);

		if (hipoteca.esTipoFijo()) {
			hipotecaFijaServicio.calcularCuota(hipoteca);
			hipotecaFijaServicio.calcularAmortizaciones(hipoteca);
		} else {
			hipotecaVariableServicio.calcularCuota(hipoteca);
			hipotecaVariableServicio.calcularAmortizaciones(hipoteca);

		}

		redirectAttributes.addFlashAttribute("amortizaciones2", hipoteca.getAmortizaciones());
		redirectAttributes.addFlashAttribute("hipoteca", hipoteca);

		//Simulacion al darle al boton de calcular
		Simulacion simulacion = new Simulacion();
		simulacionServicio.generarHipotecas(hipoteca, simulacion);
		simulacionServicio.calcularProbabilidad(simulacion);
		redirectAttributes.addFlashAttribute("porcentaje", simulacion.getPorcentaje());

		return "redirect:" + RutaUtil.RUTA_INICIO;
	}

}

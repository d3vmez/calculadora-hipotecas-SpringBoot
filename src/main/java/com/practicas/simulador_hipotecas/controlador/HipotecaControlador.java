package com.practicas.simulador_hipotecas.controlador;

import org.apache.log4j.Logger;
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
import com.practicas.simulador_hipotecas.utilidades.ValidacionUtil;

/**
 * 
 * Controlador para la calculadora de hipotecas
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Controller
public class HipotecaControlador {
	
	Logger log = Logger.getLogger(HipotecaControlador.class);

	@Autowired
	private HipotecaFijaServicio hipotecaFijaServicio;

	@Autowired
	private HipotecaVariableServicio hipotecaVariableServicio;

	@Autowired
	private SimulacionServicio simulacionServicio;

	@GetMapping(path = { RutaUtil.RUTA_INICIO})
	public String mortgage(Model model) {

		// Si el modelo contiene amortizaciones se envían a la vista
		if (model.getAttribute("amortizaciones") != null) {
			model.addAttribute("amortizaciones", model.getAttribute("amortizaciones"));
	
		}

		// Si el modelo contiene el porcentaje de la simulación se envía a la vista
		if(model.getAttribute("porcentaje")!=null) {
			model.addAttribute("infoPorcentaje","Con los datos que has introducido hay un "+model.getAttribute("porcentaje")+"% de posibilidades"
					+ "de que sea más caro a tipo fijo");
		}
		
		// Si el modelo no contiene un objeto hipoteca, se crea
		if(model.getAttribute("hipoteca") == null) {
			model.addAttribute("hipoteca", new Hipoteca());
		}
		
		model.addAttribute("hipoteca", model.getAttribute("hipoteca"));
		
		return RutaUtil.RUTA_HIPOTECA_VISTA;
	}

	@PostMapping(RutaUtil.RUTA_HIPOTECA_SUBMIT)
	public String mortgageSubmit(@ModelAttribute("hipoteca") Hipoteca hipoteca, Model model,
			RedirectAttributes redirectAttributes) {

		// Validaciones
		/////////////////////////////////////////////////////////////

		// Comprobación del importe inicial aportado cumple las siguientes
		// dos condiciones:
		// El importe incial tiene que tener un valor superior a importe * porcentaje
		// El importe incial no puede ser superior al capital del inmueble

		if (!ValidacionUtil.esImporteInicialValido(hipoteca.getCapitalInmueble(), hipoteca.getCapitalAportado())) {
			model.addAttribute("errorImporte",
					"Este importe tiene que ser superior al 10% del precio del inmueble y no mayor");
			return RutaUtil.RUTA_HIPOTECA_VISTA;
		}
		
		// Comprobación de que la edad del solicitante de la hipoteca
		// se ecuentre en el rango [18 - 65]
		// Si no se cumple la condición se redirige a la vista con un
		// mensaje de error

		if (!ValidacionUtil.esEdadCorrecta(hipoteca.getEdad())) {
			// Si la condición se cumple, mediante la clase Model se enviará a la
			// vista un atributo que contiene un mensaje de error
			model.addAttribute("errorEdad", "La edad tiene que estar entre 18 y 65 años");
			return RutaUtil.RUTA_HIPOTECA_VISTA;
		}
			
		// Comprobación de que la edad del solicitante de la hipoteca
		// más la duración de la hipoteca no supere los 65 años
		// Si no se cumple la condición se redirige a la vista con un
		// mensaje de error

		if (!ValidacionUtil.esEdadCorrectaConCuota(hipoteca.getEdad(), hipoteca.getPlazo())) {
			// Si la condición se cumple, mediante la clase Model se enviará a la
			// vista un atributo que contiene un mensaje de error
			model.addAttribute("errorEdad", "Ya eres muy mayor");
			return RutaUtil.RUTA_HIPOTECA_VISTA;
		}
			
		/////////////////////////////////////////////////////////////
		
		//hipotecaFijaServicio.calcularTasaInteres(hipoteca);
		
		// Se realiza la simulación para comprobar si la contratación
		// de una hipoteca variable es más beneficiosa que una fija
		
		// Se crea una instancia de Simulacion para almecenar tanto
		// la hipoteca hija como una lista de hipotecas variables
		Simulacion simulacion = new Simulacion();
		simulacionServicio.generarHipotecas(hipoteca, simulacion);
		simulacionServicio.calcularProbabilidad(simulacion);

		// Depende si la hipoteca es fija o variable los cálculos varían
		if (hipoteca.esTipoFijo()) {
			// Se calcula la cuota mensual de la hipoteca
			// Se calculan las amortizaciones de cada uno de los meses
			hipotecaFijaServicio.calcularAmortizaciones(hipoteca);
		} else {
			
			// Se calcula la cuota mensual de la hipoteca,
			// aunque varía año a año
			// Se calculan las amortizaciones de cada uno de los meses
			hipotecaVariableServicio.calcularAmortizaciones(hipoteca);
	
		}

		//Envio de datos al endpoint RUTA_INICIO
		// TODO seguir depurando aqui
		redirectAttributes.addFlashAttribute("porcentaje", simulacion.getPorcentaje());
		redirectAttributes.addFlashAttribute("amortizaciones", hipoteca.getAmortizaciones());
		redirectAttributes.addFlashAttribute("hipoteca", hipoteca);

		return "redirect:" + RutaUtil.RUTA_INICIO;
	}
	
	
}

package com.practicas.simulador_hipotecas.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.ui.Model;

public class ValidacionUtil {

	private static final int EDADMAX = 65;
	private static final int EDADMIN = 18;

	// Validaciones para la edad
	/////////////////////////////////////////////////////////////////

	/**
	 * 
	 * Método para comprobar que la edad del solicitante de la hipoteca se ecuentra
	 * en el rango [EDADMIN - EDADMAX]
	 * 
	 * @param Model model
	 * @param Date  fechaNacimiento
	 * @return true si la edad es correcta
	 */
	public static boolean esEdadCorrecta(Model model, Date fechaNacimiento) {

		int edad = calcularEdad(fechaNacimiento);

		if (edad < EDADMIN || edad > EDADMAX) {
			// Si la condición se cumple, mediante la clase Model se enviará a la
			// vista un atributo que contiene un mensaje de error
			model.addAttribute("errorEdad", "La edad tiene que estar entre 18 y 65 años");
			return false;
		}

		return true;

	}

	/**
	 * 
	 * Método para comprobar que la edad del solicitante más la duración de la
	 * hipoteca no supere la EDADMAX
	 * 
	 * @param Model model
	 * @param Date  fechaNacimiento
	 * @param int   plazo
	 * @return true si la edad es correcta
	 */
	public static boolean esEdadCorrectaConCuota(Model model, Date fechaNacimiento, int plazo) {

		int edad = calcularEdad(fechaNacimiento);

		if (plazo + edad > EDADMAX) {
			// Si la condición se cumple, mediante la clase Model se enviará a la
			// vista un atributo que contiene un mensaje de error
			model.addAttribute("errorEdad", "Ya eres muy mayor");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * Método para calcular la edad en años del solicitante
	 * 
	 * @param Date fechaNacimiento
	 * @return
	 */
	private static int calcularEdad(Date fechaNacimiento) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaActualTemp = sdf.format(new Date());
		int edad = 0;
		try {
			Date fechaActual = sdf.parse(fechaActualTemp);

			long diff = fechaActual.getTime() - fechaNacimiento.getTime();
			System.out.println(diff);

			TimeUnit time = TimeUnit.DAYS;
			long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
			edad = (int) diffrence / 365;

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return edad;
	}

	/////////////////////////////////////////////////////////////////

	// Validación del importe inicial aportado para la hipoteca
	/////////////////////////////////////////////////////////////////

	/**
	 * 
	 * Método para comprobar si el importe inicial aportado es válido
	 * 
	 * @param Model model
	 * @param double capitalInmueble
	 * @param double capitalAportado
	 * @return true si el importe inicial aportado es válido
	 */
	public static boolean esImporteInicialValido(Model model, double capitalInmueble, double capitalAportado) {

		if (capitalInmueble * 0.1 > capitalAportado || capitalInmueble < capitalAportado) {
			model.addAttribute("errorImporte",
					"Este importe tiene que ser superior al 10% del precio del inmueble y no mayor");
			return false;
		}
		
		return true;
	}
	
	/////////////////////////////////////////////////////////////////

}

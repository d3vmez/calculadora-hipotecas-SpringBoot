package com.practicas.simulador_hipotecas.utilidades;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * Clase que contiene las validaciones realizadas en el controlador HipotecaControlador
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Component
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
	public static boolean esEdadCorrecta (Date fechaNacimiento) {

		int edad = calcularEdad(fechaNacimiento);

		if (edad < EDADMIN || edad > EDADMAX) {
			
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
	public static boolean esEdadCorrectaConCuota (Date fechaNacimiento, int plazo) {

		int edad = calcularEdad(fechaNacimiento);

		if (plazo + edad > EDADMAX) {

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
	private static int calcularEdad(Date fecha) {
		
		LocalDate fechaNacimiento = Instant.ofEpochMilli(fecha.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
		
		LocalDate fechaActual = LocalDate.now();
		
		Period diferenciaFechas = Period.between(fechaNacimiento, fechaActual);
		
		int edad = diferenciaFechas.getYears();
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
	public static boolean esImporteInicialValido(double capitalInmueble, double capitalAportado) {

		if (capitalInmueble * 0.1 > capitalAportado || capitalInmueble <= capitalAportado) {

			return false;
		}

		return true;
	}

	/////////////////////////////////////////////////////////////////

}

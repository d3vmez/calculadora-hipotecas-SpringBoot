package com.practicas.simulador_hipotecas.modelo;

public interface CalculoInteres {
	
	public static float EURIBOR = 5/12000;
	
	default double calcularInteresFijo(float tasaInteres, double capitalPorAmortizar){

		return capitalPorAmortizar * tasaInteres;
		
	}
	
	default double calcularInteresVariable(float tasaInteres, double capitalPorAmortizar) {
		
		float tasaInteresVariable =   (tasaInteres + EURIBOR);
		return capitalPorAmortizar * tasaInteresVariable;
	}

}

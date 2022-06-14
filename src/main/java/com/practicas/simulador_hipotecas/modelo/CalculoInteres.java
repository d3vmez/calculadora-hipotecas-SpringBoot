package com.practicas.simulador_hipotecas.modelo;

public interface CalculoInteres {
	
	static float EURIBOR = 0.5f/(100*12);
	
	default double calcularInteresFijo(float tasaInteres, double capitalPorAmortizar){
		System.out.println(tasaInteres + " " + capitalPorAmortizar);
		System.out.println("Fijo: " + capitalPorAmortizar * tasaInteres);
		return capitalPorAmortizar * tasaInteres;
		
	}
	
	default double calcularInteresVariable(float tasaInteres, double capitalPorAmortizar) {
		
		return capitalPorAmortizar * tasaInteres;
	}

}

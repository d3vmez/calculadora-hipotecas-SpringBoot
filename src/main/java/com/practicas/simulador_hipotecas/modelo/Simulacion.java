package com.practicas.simulador_hipotecas.modelo;

import java.util.ArrayList;
import java.util.List;

public class Simulacion {
	
	private List<Hipoteca> hipotecasVariables = new ArrayList<>();
	private Hipoteca hipotecaFija;
	private int nSimulaciones;
	private float porcentaje;
	
	// Constructores
	/////////////////////////////////////////////////////////////////
	public Simulacion() {
		super();
		
	}

	public Simulacion(List<Hipoteca> hipotecasVariables, Hipoteca hipotecaFija, int nSimulaciones) {
		super();
		this.hipotecasVariables = hipotecasVariables;
		this.hipotecaFija = hipotecaFija;
		this.nSimulaciones = nSimulaciones;
	}
	
	// Métodos de acceso
	/////////////////////////////////////////////////////////////////
	
	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public List<Hipoteca> getHipotecasVariables() {
		return hipotecasVariables;
	}

	public void setHipotecasVariables(Hipoteca hipoteca) {
		this.hipotecasVariables.add(hipoteca);
	}

	public Hipoteca getHipotecaFija() {
		return hipotecaFija;
	}

	public void setHipotecaFija(Hipoteca hipotecaFija) {
		this.hipotecaFija = hipotecaFija;
	}

	public int getnSimulaciones() {
		return nSimulaciones;
	}

	public void setnSimulaciones(int nSimulaciones) {
		this.nSimulaciones = nSimulaciones;
	}

	@Override
	public String toString() {
		return "Simulacion [hipotecasVariables=" + hipotecasVariables + ", hipotecaFija=" + hipotecaFija
				+ ", nSimulaciones=" + nSimulaciones + "]";
	}
	
}

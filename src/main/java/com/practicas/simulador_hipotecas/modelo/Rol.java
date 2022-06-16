package com.practicas.simulador_hipotecas.modelo;

public class Rol {


	private int id;
	private RolTipo rol;

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(int id, RolTipo rol) {
		super();
		this.id = id;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolTipo getRol() {
		return rol;
	}

	public void setRol(RolTipo rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + rol + "]";
	}
}

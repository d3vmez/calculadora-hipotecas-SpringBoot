package com.practicas.simulador_hipotecas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM ('CLIENTE')")
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

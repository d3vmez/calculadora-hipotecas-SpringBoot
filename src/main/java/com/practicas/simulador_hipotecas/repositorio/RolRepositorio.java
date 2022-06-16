package com.practicas.simulador_hipotecas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicas.simulador_hipotecas.modelo.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer>{
	
    public List<Rol> findAllByRol(String nombre);
    
}


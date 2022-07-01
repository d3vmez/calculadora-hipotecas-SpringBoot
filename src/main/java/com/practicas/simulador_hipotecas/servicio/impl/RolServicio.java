package com.practicas.simulador_hipotecas.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Rol;
import com.practicas.simulador_hipotecas.repositorio.RolRepositorio;

/**
 * 
 * Este servicio se encarga de los CRUD para los objetos Rol
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Service
public class RolServicio {
	
	@Autowired
    private RolRepositorio rolRepositorio;
	
    /**
     * 
     * Método para obtener un rol según su id
     * 
     * @param Integer id
     * @return Optional<Rol> 
     */
    public Optional<Rol> obtener(Integer id) {
        return rolRepositorio.findById(id);
    }

    /**
     * 
     * Método para obtener todos los roles del origen de datos
     * 
     * @return List<Rol>
     */
    public List<Rol> obtenerTodos() {
        return (List<Rol>) rolRepositorio.findAll();
    }

    /**
     * 
     * Método para guardar un rol en el origen de datos
     * 
     * @param Rol rol
     */
    public void guardar(Rol rol) {
        rolRepositorio.save(rol);   
    }

    /**
     * 
     * Método para eliminar un Rol del origen de datos
     * 
     * @param Rol rol
     */
    public void eliminar(Rol rol) {
        rolRepositorio.delete(rol);
    }
}

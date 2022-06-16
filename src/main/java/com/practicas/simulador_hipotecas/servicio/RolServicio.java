package com.practicas.simulador_hipotecas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Rol;
import com.practicas.simulador_hipotecas.repositorio.RolRepositorio;

@Service
public class RolServicio {
	
	@Autowired
    private RolRepositorio rolRepositorio;
	
    public Optional<Rol> obtener(Integer id) {
        return rolRepositorio.findById(id);
    }

    public List<Rol> obtenerTodos() {
        return (List<Rol>) rolRepositorio.findAll();
    }

    public void guardar(Rol rol) {
        rolRepositorio.save(rol);   
    }

    public void eliminar(Rol rol) {
        rolRepositorio.delete(rol);
    }
}

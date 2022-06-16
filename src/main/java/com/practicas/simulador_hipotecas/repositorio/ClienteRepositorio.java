package com.practicas.simulador_hipotecas.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicas.simulador_hipotecas.modelo.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
	
	public Optional<Cliente> findByEmail(String email);

}

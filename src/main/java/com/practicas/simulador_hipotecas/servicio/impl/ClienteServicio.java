package com.practicas.simulador_hipotecas.servicio.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practicas.simulador_hipotecas.modelo.Cliente;
import com.practicas.simulador_hipotecas.repositorio.ClienteRepositorio;

/**
 * 
 * Este servicio se encarga de los CRUD para los objetos de cliente y de generar
 * un usuario con rol para la sesión de la aplicación
 * 
 * @author Marcos
 * @author Pablo
 *
 */
@Service
public class ClienteServicio implements UserDetailsService{
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * 
	 * Método que devuelve una lista con todos los clientes guardados en 
	 * el origen de datos
	 * 
	 * @return List<Cliente> clientes
	 */
	public List<Cliente> obtenerTodos() {
		
		return (List<Cliente>) clienteRepositorio.findAll();
		
	}

	/**
	 * 
	 * Método que devuelve el cliente con el id pasado por parámetro
	 * 
	 * @param Integer id
	 * @return Optional<Cliente> cliente con el id pasado por parámetro
	 */
	public Optional<Cliente> obtener(Integer id) {
		
		return clienteRepositorio.findById(id);
		
	}

	/**
	 * 
	 * Método para guardar o actualizar un cliente en el origen de datos
	 * 
	 * @param Cliente cliente
	 * @param boolean esAlta
	 */
	public void guardar(Cliente cliente, boolean esAlta) {
		
		// Si es alta se codifica la clave del cliente
		if(esAlta) {
			String clave = cliente.getClave();
			String claveEncode = passwordEncoder.encode(clave);
			cliente.setClave(claveEncode);
		}

		cliente = clienteRepositorio.save(cliente);
		
	}

	/**
	 * 
	 * Método para eliminar un cliente determinado del origen de datos
	 * 
	 * @param Cliente cliente
	 */
	public void eliminar(Cliente cliente) {
		
		clienteRepositorio.delete(cliente);
		
	}

	/**
	 * 
	 * Método para obtener un cliente con un determinado email del origen de datos
	 * 
	 * @param String email
	 * @return Optional<Cliente> cliente con el email pasado por parámetro
	 */
	public Optional<Cliente> obtenerPorEmail(String email) {

		return clienteRepositorio.findByEmail(email);
		
	}

	/**
	 * 
	 * Método para eliminar logicamente un cliente con un determinado id
	 * del origin de datos
	 * 
	 * @param Integer id del cliente
	 */
	public void eliminarLogicamente(Integer id) {

			// Se obtiene al cliente
			Optional<Cliente> clienteOp  = clienteRepositorio.findById(id);
			Cliente cliente = new Cliente();
			
			// Se comprueba de que exista en el origen de datos
			if(clienteOp.isPresent()) {
				
				cliente = clienteOp.get();
				
				// Se genera la fecha de hoy como fecha de baja
				java.util.Date fecha = new java.util.Date();
				java.sql.Date fechaSQl = new Date(fecha.getTime());
				
				// Se le asigna esta fecha de baja al cliente 
				// en cuestión
				cliente.setFechaBaja(fechaSQl);
				clienteRepositorio.save(cliente);
				
			}
				
	}
	
	/**
	 * 
	 * Método para comprobar que el cliente con un determinado email
	 * existe en el origen de datos
	 * 
	 * @param String email
	 * @return true si el cliente con el email existe
	 */
	public boolean existeEmail(String email) {
        Optional<Cliente> clienteOp = clienteRepositorio.findByEmail(email);
        if(clienteOp.isEmpty()) {
            return false;
        }
        return true;
    }
	
	/**
	 *
	 * Método para cargar un usuario en la sesión y asignarle un rol después de comprobar
	 * sus credenciales
	 *
	 * @return {@link User} para la sesión de la aplicación
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Obtener al usuario de la BD por email
		Optional<Cliente> opCliente = obtenerPorEmail(email);

		// Crear usuario para la sesion de la aplicacion
		User springUsuario = null;

		if (opCliente.isPresent()) {

			Cliente cliente = opCliente.get();

			// Dar rol al usuario de la sesion
			Set<GrantedAuthority> rol = new HashSet<>();
			rol.add(new SimpleGrantedAuthority(cliente.getRol().getRol().toString()));

			springUsuario = new User(email, cliente.getClave(), rol);

		} else {

			throw new UsernameNotFoundException("Usuario con email: " + email + " no encontrado");

		}

		return springUsuario;
	}
	
}

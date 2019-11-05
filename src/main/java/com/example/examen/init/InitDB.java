package com.example.examen.init;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.examen.entity.Usuario;
import com.example.examen.repository.AuthoryRepository;
import com.example.examen.repository.UsuarioRepository;



@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthoryRepository authorityRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
	
	/*	this.usuarioRepository.deleteAll();
		this.authorityRepository.deleteAll();
		
		Usuario cliente = new Usuario();
		cliente.setUsername("cliente");
		cliente.setPassword(passwordEncoder.encode("123"));
		cliente.setNombre("cliente");
		cliente.setEnable(true);
		
		Usuario camareros = new Usuario();
		camareros.setUsername("camarero");
		camareros.setPassword(passwordEncoder.encode("123"));
		camareros.setNombre("camarero");
		camareros.setEnable(true);
		
		Usuario gerente = new Usuario();
		gerente.setUsername("gerente");
		gerente.setPassword(passwordEncoder.encode("123"));
		gerente.setNombre("gerente");
		gerente.setEnable(true);
        
		
		cliente.addAuthority("ROLE_CLIENTE");
		
		camareros.addAuthority("ROLE_CAMARERO");
		camareros.addAuthority("ACCESS_PEDIDO_READ");
		camareros.addAuthority("ACCESS_PEDIDO_WRITE");
		
		gerente.addAuthority("ROLE_GERENTE");
		gerente.addAuthority("ACCESS_REST1");
		gerente.addAuthority("ACCESS_REST2");
        
        List<Usuario> usuarios = Arrays.asList(camareros, gerente,cliente);        
        this.usuarioRepository.saveAll(usuarios);*/
	}
}

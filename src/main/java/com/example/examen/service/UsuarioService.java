package com.example.examen.service;

import java.util.Optional;

import com.example.examen.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Integer>{
	Optional<Usuario> findByUsername(String username) throws Exception;
}

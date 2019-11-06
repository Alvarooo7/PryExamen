package com.example.examen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.example.examen.entity.Pedido;
import com.example.examen.entity.Usuario;

public interface PedidoService extends CrudService<Pedido, Long>{
	Optional<Pedido> findByUsuario(Usuario usuario);
	@Query(value="Select top 1 pe from Pedido pe order by 1 desc", nativeQuery=true)
	Optional<Pedido> fetchUltimopedido();
	
}

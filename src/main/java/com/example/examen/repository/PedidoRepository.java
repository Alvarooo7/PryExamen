package com.example.examen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.examen.entity.Pedido;
import com.example.examen.entity.Usuario;


public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Optional<Pedido> findByUsuario(Usuario usuario);
	//Busca a nivel de las ENTITIES
	@Query(value="Select top 1 * from Pedido pe order by 1 desc", nativeQuery=true)
	Optional<Pedido> fetchUltimopedido();
}

package com.example.examen.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.examen.entity.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{

	@Transactional
	@Modifying
	@Query(value="delete DetallePedido d where d.pedido.id =:codigo ")
	void deleteByPedido(Long codigo) throws Exception;
	
}

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
	@Query(value="delete detalle_pedido where pedido_id =:codigo " , nativeQuery=true)
	
	void deleteByPedido(Long codigo) throws Exception;
	//Por defecto nativeQuery es false, se utilizan todas las clases existentes en el proyecto no en BD OJO
	//@Query(value="delete DetallePedido d where d.pedido.id =:codigo ")
}

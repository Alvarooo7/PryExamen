package com.example.examen.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.example.examen.entity.DetallePedido;
import com.example.examen.entity.Pedido;

public interface DetallePedidoService extends CrudService<DetallePedido, Integer>{

	
	@Query(value="delete detalle_pedido where pedido_id = ?1  ", nativeQuery=true)
	void deleteByPedido(Long codigo) throws Exception;
}

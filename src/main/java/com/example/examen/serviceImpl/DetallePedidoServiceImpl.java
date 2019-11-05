package com.example.examen.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.examen.entity.DetallePedido;
import com.example.examen.repository.DetallePedidoRepository;
import com.example.examen.service.DetallePedidoService;
@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<DetallePedido> findAll() throws Exception {
		return detallePedidoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DetallePedido> findById(Integer id) throws Exception {
		return detallePedidoRepository.findById(id);
	}

	@Override
	@Transactional
	public DetallePedido save(DetallePedido entity) throws Exception {
		return detallePedidoRepository.save(entity);
	}

	@Override
	@Transactional
	public DetallePedido update(DetallePedido entity) throws Exception {
		return detallePedidoRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		detallePedidoRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		detallePedidoRepository.deleteAll();
	}

}

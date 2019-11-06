package com.example.examen.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.examen.entity.Pedido;
import com.example.examen.entity.Usuario;
import com.example.examen.repository.PedidoRepository;
import com.example.examen.service.PedidoService;
@Service
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pedido> findAll() throws Exception {
		return pedidoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pedido> findById(Long id) throws Exception {
		return pedidoRepository.findById(id);
	}

	@Override
	@Transactional
	public Pedido save(Pedido entity) throws Exception {
		return pedidoRepository.save(entity);
	}

	@Override
	@Transactional
	public Pedido update(Pedido entity) throws Exception {
		return pedidoRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws Exception {
		pedidoRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		pedidoRepository.deleteAll();
	}

	@Override
	public Optional<Pedido> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return pedidoRepository.findByUsuario(usuario);
	}

	@Override
	public Optional<Pedido> fetchUltimopedido() {
		// TODO Auto-generated method stub
		return pedidoRepository.fetchUltimopedido();
	}

	

}

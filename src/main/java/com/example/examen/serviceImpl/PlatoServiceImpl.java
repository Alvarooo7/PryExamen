package com.example.examen.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.entity.Plato;
import com.example.examen.repository.PlatoRepository;
import com.example.examen.service.PlatoService;

@Service
public class PlatoServiceImpl implements PlatoService {

	@Autowired
	private PlatoRepository plato ;
	@Override
	public List<Plato> findAll() throws Exception {
		// TODO Auto-generated method stub
		return plato.findAll();
	}

	@Override
	public Optional<Plato> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return plato.findById(id);
	}

	@Override
	public Plato save(Plato entity) throws Exception {
		// TODO Auto-generated method stub
		return plato.save(entity);
	}

	@Override
	public Plato update(Plato entity) throws Exception {
		// TODO Auto-generated method stub
		return plato.save(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		plato.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		plato.deleteAll();
	}

}

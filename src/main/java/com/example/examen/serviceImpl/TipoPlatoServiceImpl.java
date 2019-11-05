package com.example.examen.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.entity.TipoPlato;
import com.example.examen.repository.TipoPlatoRepository;
import com.example.examen.service.TipoPlatoService;

@Service
public class TipoPlatoServiceImpl implements TipoPlatoService {

	@Autowired TipoPlatoRepository tipo ;
	
	@Override
	public List<TipoPlato> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tipo.findAll();
	}

	@Override
	public Optional<TipoPlato> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tipo.findById(id);
	}

	@Override
	public TipoPlato save(TipoPlato entity) throws Exception {
		// TODO Auto-generated method stub
		return tipo.save(entity);
	}

	@Override
	public TipoPlato update(TipoPlato entity) throws Exception {
		// TODO Auto-generated method stub
		return tipo.save(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		tipo.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		tipo.deleteAll();
	}

}

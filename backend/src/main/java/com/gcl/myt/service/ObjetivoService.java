package com.gcl.myt.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcl.myt.exception.ResourceNotFoundException;
import com.gcl.myt.model.Objetivo;
import com.gcl.myt.model.User;
import com.gcl.myt.model.enums.StatusObjetivo;
import com.gcl.myt.repository.ObjetivoRepository;

@Service
public class ObjetivoService {

	@Autowired
	private ObjetivoRepository repository;

	public Collection<Objetivo> listarTodos(){
		return repository.findByStatus(StatusObjetivo.ATIVO);
	}
	
	public void criar(Objetivo o,Long idUser) {
		o.setUser(new User(idUser));
		o.setStatus(StatusObjetivo.ATIVO);
		repository.save(o);
	}

	public Objetivo obter(Long id) {
		return repository.findOptionalByIdAndStatus(id, StatusObjetivo.ATIVO)
				.orElseThrow(() -> new ResourceNotFoundException("Objetivo", "Id", id));
	}

	public void atualizar(Objetivo o,Long idUser) {
		verificarSeExiste(o.getId());
		o.setUser(new User(idUser));
		repository.save(o);
	}

	public void excluir(Long id) {
		Objetivo objetivo = verificarSeExiste(id);
		objetivo.setStatus(StatusObjetivo.INATIVO);
		repository.save(objetivo);
	}

	private Objetivo verificarSeExiste(Long id) {
		return repository.findOptionalByIdAndStatus(id, StatusObjetivo.ATIVO)
				.orElseThrow(() -> new ResourceNotFoundException("Objetivo", "Id", id));
	}

}

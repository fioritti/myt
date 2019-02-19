package com.gcl.myt.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gcl.myt.model.Objetivo;
import com.gcl.myt.security.CurrentUser;
import com.gcl.myt.security.UserPrincipal;
import com.gcl.myt.service.ObjetivoService;

@RestController
@RequestMapping("/objetivos")
@PreAuthorize("hasRole('USER')")
public class ObjetivoController {
	
	@Autowired
	private ObjetivoService service;
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Collection<Objetivo> listarTodos(){
		return service.listarTodos();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void criar(@Valid @RequestBody Objetivo o, @CurrentUser UserPrincipal userPrincipal) {
		service.criar(o,userPrincipal.getId());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Objetivo obter(@PathVariable Long id) {
		return service.obter(id);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping
	public void atualizar(@Valid @RequestBody Objetivo o, @CurrentUser UserPrincipal userPrincipal) {
		service.atualizar(o, userPrincipal.getId());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	

}

package com.gcl.myt.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gcl.myt.exception.ResourceNotFoundException;
import com.gcl.myt.model.Objetivo;
import com.gcl.myt.model.User;
import com.gcl.myt.model.enums.StatusObjetivo;
import com.gcl.myt.repository.ObjetivoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ObjetivoServiceTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private ObjetivoRepository repository;

	@InjectMocks
	private ObjetivoService service;

	@Test
	public void deveCriarObjetivo() {
		Long idUser = 55L;
		Objetivo o = criarObjetivo("Estudos", 1L);
		service.criar(o,idUser);
		Mockito.verify(repository, Mockito.times(1)).save(o);
	}

	@Test
	public void deveListarTodosObjetivos() {
		service.listarTodos();
		Mockito.verify(repository, Mockito.times(1)).findByStatus(StatusObjetivo.ATIVO);
	}

	@Test
	public void deveObterObjetivo() {
		Long idObjetivo = 10l;
		Optional<Objetivo> objetivoEsperado = criarOptionalObjetivo("Estudos", idObjetivo);

		Mockito.when(repository.findOptionalByIdAndStatus(idObjetivo, StatusObjetivo.ATIVO))
				.thenReturn(objetivoEsperado);

		Objetivo objetivoRetornado = service.obter(idObjetivo);

		assertEquals(objetivoEsperado.get(), objetivoRetornado);
	}

	@Test
	public void deveLancarExceptionAoObterObjetivoPorIdInvalido() {
		Long idObjetivo = 10l;
		Mockito.when(repository.findOptionalByIdAndStatus(idObjetivo, StatusObjetivo.ATIVO))
				.thenReturn(Optional.empty());

		exceptionRule.expect(ResourceNotFoundException.class);
		exceptionRule.expectMessage("Objetivo not found with Id: '10'");

		service.obter(idObjetivo);
	}

	@Test
	public void deveAtualizarObjetivo() {
		Long idUser = 1L;
		Objetivo o = criarObjetivoComId("Estudos", 1L, 22L);

		Mockito.when(repository.findOptionalByIdAndStatus(o.getId(), StatusObjetivo.ATIVO)).thenReturn(Optional.of(o));

		service.atualizar(o,idUser);
		Mockito.verify(repository, Mockito.times(1)).save(o);
	}

	@Test
	public void deveLancarExceptionAoAtualizarObjetivoComIdInvalido() {
		Long idObjetivo = 10l;
		Long idUser = 1L;
		Objetivo objetivo = criarObjetivoComId("Estudos",1L, idObjetivo);
		Mockito.when(repository.findOptionalByIdAndStatus(idObjetivo, StatusObjetivo.ATIVO))
				.thenReturn(Optional.empty());

		exceptionRule.expect(ResourceNotFoundException.class);
		exceptionRule.expectMessage("Objetivo not found with Id: '10'");

		service.atualizar(objetivo,idUser);
	}

	@Test
	public void deveExcluirObjetivo() {
		Long idObjetivo = 21l;
		Objetivo objetivoEsperado = criarObjetivoComId("Estudos", 1L, idObjetivo);
		objetivoEsperado.setStatus(StatusObjetivo.INATIVO);

		Mockito.when(repository.findOptionalByIdAndStatus(idObjetivo, StatusObjetivo.ATIVO))
				.thenReturn(Optional.of(objetivoEsperado));

		service.excluir(idObjetivo);

		Mockito.verify(repository, Mockito.times(1)).save(objetivoEsperado);
	}

	@Test
	public void deveLancarExceptionAoExcluirObjetivoPorIdInvalido() {
		Long idObjetivo = 10l;
		Mockito.when(repository.findOptionalByIdAndStatus(idObjetivo, StatusObjetivo.ATIVO))
				.thenReturn(Optional.empty());

		exceptionRule.expect(ResourceNotFoundException.class);
		exceptionRule.expectMessage("Objetivo not found with Id: '10'");

		service.excluir(idObjetivo);
	}

	private Optional<Objetivo> criarOptionalObjetivo(String nomeObjetivo, Long idUser) {
		User user = new User();
		user.setId(idUser);
		user.setName("MockUser");
		Objetivo o = new Objetivo(nomeObjetivo, user);
		return Optional.ofNullable(o);
	}

	private Objetivo criarObjetivo(String nomeObjetivo, Long idUser) {
		User user = new User();
		user.setId(idUser);
		user.setName("MockUser");
		Objetivo o = new Objetivo(nomeObjetivo, user);
		return o;
	}

	private Objetivo criarObjetivoComId(String nomeObjetivo, Long idUser, Long idObjetivo) {
		Objetivo o = criarObjetivo(nomeObjetivo, idUser);
		o.setId(idObjetivo);
		return o;
	}

}

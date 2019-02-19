package com.gcl.myt.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcl.myt.model.Objetivo;
import com.gcl.myt.model.enums.StatusObjetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {

	public Collection<Objetivo> findByStatus(StatusObjetivo status);

	public Optional<Objetivo> findOptionalByIdAndStatus(Long id, StatusObjetivo status);

}

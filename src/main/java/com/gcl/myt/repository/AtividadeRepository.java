package com.gcl.myt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcl.myt.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}

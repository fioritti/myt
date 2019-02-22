package com.gcl.myt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcl.myt.model.Objetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {

}

package com.gcl.myt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gcl.myt.model.Objetivo;
import com.gcl.myt.repository.ObjetivoRepository;

@Service
public class ObjetivoService extends AbstractService<Objetivo, Long> {

    @Autowired
    private ObjetivoRepository repository;

    @Override
    protected JpaRepository<Objetivo, Long> getRepository() {
        return repository;
    }

}

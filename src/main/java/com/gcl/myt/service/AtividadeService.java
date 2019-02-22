package com.gcl.myt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gcl.myt.model.Atividade;
import com.gcl.myt.repository.AtividadeRepository;

@Service
public class AtividadeService extends AbstractService<Atividade, Long> {

    @Autowired
    private AtividadeRepository repository;
    
    
    @Override
    public Atividade save(Atividade atividade) {
    	if(atividade.isSemIntervalo() || atividade.getHorarioFinal() != null) {
    		atividade.finalizarAtividade();
    	}
    	return super.save(atividade);
    }

    @Override
    protected JpaRepository<Atividade, Long> getRepository() {
        return repository;
    }

}

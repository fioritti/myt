package com.gcl.myt.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.gcl.myt.model.enums.StatusAtividade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Atividade extends AbstractModel<Long> {
	
	public Atividade() {
		this.status = StatusAtividade.EM_ANDAMENTO;
	}
	
	
	@NotBlank(message = "O nome da atividade deve ser especificado")
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="ID_OBJETIVO")
	private Objetivo objetivo;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@Column(nullable = false)
	private LocalTime horarioInicial;

	private LocalTime horarioFinal;
	
	@Transient
	private boolean semIntervalo;
	
	@Column(nullable = false)
	private StatusAtividade status;
	
	
	public void finalizarAtividade() {
		this.status = StatusAtividade.FINALIZADA;
	}
	


}

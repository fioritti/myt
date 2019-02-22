package com.gcl.myt.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

import com.gcl.myt.model.enums.StatusObjetivo;

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
public class Objetivo extends AbstractModel<Long> {
	
	public Objetivo() {
		this.status = StatusObjetivo.ATIVO;
	}

	@NotBlank(message = "O nome do objetivo deve ser especificado")
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private StatusObjetivo status;

}

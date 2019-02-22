package com.gcl.myt.model.enums;

public enum StatusAtividade {
	EM_ANDAMENTO("Em andamento"),FINALIZADA("Finalizada");
	
	private StatusAtividade(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

package com.menete.ORDEM_SERVICO.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	
	@OneToMany(mappedBy = "cliente")
	private List<Os> list = new ArrayList<>();
	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
		// TODO Auto-generated constructor stub
	}

	public List<Os> getList() {
		return list;
	}

	public void setList(List<Os> list) {
		this.list = list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

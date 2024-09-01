package com.menete.ORDEM_SERVICO.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Custommer extends Person implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	
	@OneToMany(mappedBy = "customer")
	private List<Os> list = new ArrayList<>();
	

	public Custommer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Custommer(Integer id, String name, String cpf, String cellPhone) {
		super(id, name, cpf, cellPhone);
		// TODO Auto-generated constructor stub
	}

	public List<Os> getList() {
		return list;
	}

	public void setList(List<Os> list) {
		this.list = list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package com.menete.ORDEM_SERVICO.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Technician extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "technician")
	private List<Os> list = new ArrayList<>();
	
	public Technician() {
		super();
		// TODO Auto-generated constructor stub	
	}

	public Technician(Integer id, String name, String cpf, String cellPhone) {
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
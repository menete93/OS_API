package com.menete.ORDEM_SERVICO.domain.dto;

import java.io.Serializable;

import com.menete.ORDEM_SERVICO.domain.entity.Custommer;

import jakarta.validation.constraints.NotEmpty;

public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = " field Name is required")
	private String name;

	@NotEmpty(message = " field cpf is required")
	private String cpf;

	@NotEmpty(message = " field cellphone is required")
	private String cellPhone;

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(Custommer obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.cellPhone = obj.getCellPhone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

}

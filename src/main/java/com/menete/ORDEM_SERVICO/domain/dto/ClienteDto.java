package com.menete.ORDEM_SERVICO.domain.dto;

import java.io.Serializable;

import com.menete.ORDEM_SERVICO.domain.Cliente;

import jakarta.validation.constraints.NotEmpty;

public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = " O campo Nome e requerido")
	private String nome;

	@NotEmpty(message = " O campo CPF e requerido")
	private String cpf;

	@NotEmpty(message = " O campo Telefone e requerido")
	private String telefone;

	public ClienteDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDto(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

package com.menete.ORDEM_SERVICO.domain.dto;

import java.io.Serializable;

import com.menete.ORDEM_SERVICO.domain.Tecnico;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = " field Name is required")
	private String nome;

	@NotEmpty(message = " field cpf is required")
	private String cpf;

	@NotEmpty(message = " field cellphone is required")
	private String telefone;

	public TecnicoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TecnicoDto(Tecnico obj) {
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

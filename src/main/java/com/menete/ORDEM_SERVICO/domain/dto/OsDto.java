package com.menete.ORDEM_SERVICO.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menete.ORDEM_SERVICO.domain.Os;
import com.menete.ORDEM_SERVICO.domain.Prioridade;
import com.menete.ORDEM_SERVICO.domain.Status;

import jakarta.validation.constraints.NotEmpty;

public class OsDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	@NotEmpty(message = "o campo observacoes e obrigatorio!")
	private String observacoes;
	private Integer status;
	private Integer tecnico;
	private Integer cliente;

	public OsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OsDto(Os obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	
    public void setPrioridade(Integer prioridade) {
        this.prioridade = Prioridade.toEnum(prioridade).getCod();
    }

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

}

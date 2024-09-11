package com.menete.ORDEM_SERVICO.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menete.ORDEM_SERVICO.domain.entity.Os;
import com.menete.ORDEM_SERVICO.domain.enums.Priority;
import com.menete.ORDEM_SERVICO.domain.enums.Status;

import jakarta.validation.constraints.NotEmpty;

public class OsAllDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime openDate;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime closeDate;
	private Integer priority;
	@NotEmpty(message = "field Observations is required!")
	private String observations;
	private Integer status;
	private Integer technician;
	private Integer customer;

	public OsAllDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OsAllDTO(Os obj) {
		super();
		this.id = obj.getId();
		this.openDate = obj.getOpenDate();
		this.closeDate = obj.getCloseDate();
		this.priority = obj.getPrioridade().getCod();
		this.observations = obj.getObservatios();
		this.status = obj.getStatus().getCod();
		this.technician = obj.getTechnitian().getId();
		this.customer = obj.getCustomer().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}

	public LocalDateTime getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDateTime closeDate) {
		this.closeDate = closeDate;
	}

	public Priority getPriority() {
		return Priority.toEnum(this.priority);
	}

	
    public void setPriority(Integer priority) {
        this.priority = Priority.toEnum(priority).getCod();
    }

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTechnician() {
		return technician;
	}

	public void setTechnician(Integer technician) {
		this.technician = technician;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

}


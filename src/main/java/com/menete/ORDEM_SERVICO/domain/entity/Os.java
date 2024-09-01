package com.menete.ORDEM_SERVICO.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.menete.ORDEM_SERVICO.domain.enums.Priority;
import com.menete.ORDEM_SERVICO.domain.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

	

@Entity
public class Os {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd//MM/yyyy HH:mm")
	@Column(name = "open_date")
	private LocalDateTime openDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "close_date")
	private LocalDateTime closeDate;
	
	private Integer priority;
	private String observations;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Custommer customer;

	public Os() {
		super();
		this.setOpenDate(LocalDateTime.now());
		this.setPrioridade(Priority.LOW);
		this.setStatus(Status.OPEN);
		
		// TODO Auto-generated constructor stub
	}

	public Os(Integer id,
			Priority priority, String observations, Status status, Technician technician,
			Custommer customer) {
		super();
		this.id = id;
		//this.setOpenDate(LocalDateTime.now());
		this.priority = (priority == null) ? 0 : priority.getCod();
		this.observations = observations;
		this.status = (status == null) ? 0 : status.getCod();
		this.technician = technician;
		this.customer = customer;
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

	public Priority getPrioridade() {
		return Priority.toEnum(this.priority);
	}

	public void setPrioridade(Priority priority) {
		this.priority = priority.getCod();
	}

	public String getObservatios() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Technician getTechnitian() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Custommer getCustomer() {
		return customer;
	}

	public void setCustomer(Custommer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Os other = (Os) obj;
		return Objects.equals(id, other.id);
	}

	


}


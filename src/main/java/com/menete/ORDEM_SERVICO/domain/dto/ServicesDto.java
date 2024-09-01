package com.menete.ORDEM_SERVICO.domain.dto;

import com.menete.ORDEM_SERVICO.domain.entity.Services;
import com.menete.ORDEM_SERVICO.domain.enums.Provinces;
import com.menete.ORDEM_SERVICO.domain.service.ServicesService;

import jakarta.validation.constraints.NotEmpty;

public class ServicesDto {
	
	@NotEmpty(message = " field id is required")
	private Integer id;
	
	@NotEmpty(message = " field service name is required")
	private String serviceName;
	
	@NotEmpty(message = " field province is required")
	private Provinces province;
	
	@NotEmpty(message = " field location description is required")
	private String locationDescription;

	public ServicesDto(Services obj) {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String service_name) {
		this.serviceName = service_name;
	}

	public Provinces getProvince() {
		return province;
	}

	public void setProvince(Provinces province) {
		this.province = province;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	
	
	
}

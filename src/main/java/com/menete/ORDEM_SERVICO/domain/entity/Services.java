package com.menete.ORDEM_SERVICO.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import com.menete.ORDEM_SERVICO.domain.enums.Provinces;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="SERVICES")
public class Services implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String serviceName;
	private Provinces province;
	private String locationDescription;
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Services(String id, String serviceName, Provinces province, String locationDescription) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.province = province;
		this.locationDescription = locationDescription;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
		Services other = (Services) obj;
		return Objects.equals(id, other.id);
	}

	
	
}

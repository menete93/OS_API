package com.menete.ORDEM_SERVICO.domain;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="SERVICES")
public class Services {

	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String service_name;
	private String province;
	private String location_description;
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Services(String id, String service_name, String province, String location_description) {
		super();
		this.id = id;
		this.service_name = service_name;
		this.province = province;
		this.location_description = location_description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLocation_description() {
		return location_description;
	}
	public void setLocation_description(String location_description) {
		this.location_description = location_description;
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

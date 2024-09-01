package com.menete.ORDEM_SERVICO.domain.enums;

public enum Provinces {

	MAPUTO(0, "MP"), GAZA(1, "GZ"), INHAMBANE(2, "INH"), 
	SOFALA(2, "SOF"), MANICA(2, "MAN"), ZAMBEZIA(2, "ZAM"),
	TETE(2, "TETE"), CABO_DELGAGO(2, "CAB"), NIASSA(2, "NIA"),
	NAMPULA(2, "NAM");
	
	private Integer cod;
	private String descricao;
	
	private Provinces(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Status toEnum(Integer integer) {

		if (integer == null) {
			return null;
		}

		for (Status x : Status.values()) {

			if (integer.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid province!" + integer);
	}


}

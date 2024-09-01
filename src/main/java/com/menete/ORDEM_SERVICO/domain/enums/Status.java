package com.menete.ORDEM_SERVICO.domain.enums;

public enum Status {

	OPEN(0, "OPEN"), PROCESSING(1, "PROCESSING"), CLOSED(2, "CLOSED");

	private Integer cod;
	private String descricao;

	private Status(Integer cod, String descricao) {
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

		throw new IllegalArgumentException("Invalid Status!" + integer);
	}


}


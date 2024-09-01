package com.menete.ORDEM_SERVICO.domain.enums;

public enum Priority {

	LOW(0, "LOW"), MEDIUM(1, "MEDIUM"), HIGH(2, "HIGH");

	private Integer cod;
	private String descricao;

	private Priority(Integer cod, String descricao) {
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

	public static Priority toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Priority x : Priority.values()) {

			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("invalid priority! " + cod);
	}

}

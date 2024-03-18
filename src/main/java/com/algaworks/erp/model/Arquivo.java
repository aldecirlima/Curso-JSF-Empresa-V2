package com.algaworks.erp.model;

import java.util.Objects;

import javax.persistence.Transient;

public class Arquivo {

	private String nomeExibicao;

	private String nomeCompleto;

	@Transient
	private byte[] bytes;

	public String getNomeExibicao() {
		return nomeExibicao;
	}

	public void setNomeExibicao(String nome) {
		this.nomeExibicao = nome;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeExibicao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		return Objects.equals(nomeExibicao, other.nomeExibicao);
	}

}

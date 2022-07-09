package br.com.treinamento.curso3.request;

import java.time.LocalDate;

public class CategoriaPostRequest {
	private String tipo;

	public CategoriaPostRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriaPostRequest(String tipo) {
		super();
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

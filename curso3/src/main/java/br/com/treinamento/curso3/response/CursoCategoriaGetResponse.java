package br.com.treinamento.curso3.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursoCategoriaGetResponse {
	private String tipo;
	private List<String> descricaoCursos = new ArrayList();
	private List<LocalDate> dataInicio = new ArrayList();
	private List<LocalDate> dataTermino = new ArrayList();

	public CursoCategoriaGetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CursoCategoriaGetResponse(String tipo, List<String> descricaoCursos, List<LocalDate> dataInicio,
			List<LocalDate> dataTermino) {
		super();
		this.tipo = tipo;
		this.descricaoCursos = descricaoCursos;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getDescricaoCursos() {
		return descricaoCursos;
	}

	public void setDescricaoCursos(List<String> descricaoCursos) {
		this.descricaoCursos = descricaoCursos;
	}

	public List<LocalDate> getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(List<LocalDate> dataInicio) {
		this.dataInicio = dataInicio;
	}

	public List<LocalDate> getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(List<LocalDate> dataTermino) {
		this.dataTermino = dataTermino;
	}
}

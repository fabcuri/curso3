package br.com.treinamento.curso3.response;

import java.time.LocalDate;

public class CursoGetResponse {
	private int idCurso;
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataTermino;

	public CursoGetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CursoGetResponse(int idCurso, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

}
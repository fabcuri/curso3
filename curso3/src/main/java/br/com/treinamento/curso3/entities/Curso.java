package br.com.treinamento.curso3.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurso")
	private Integer idCurso;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@Column(name = "dataInicio")
	private LocalDate dataInicio;

	@Column(name = "dataTermino")
	private LocalDate dataTermino;

	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curso(Integer idCurso, String descricao, LocalDate dataInicio, LocalDate dataTermino, Categoria categoria) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.categoria = categoria;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}

package br.com.treinamento.curso3.response;

public class CategoriaGetResponse {
	private int idCategoria;
	private String tipo;

	public CategoriaGetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriaGetResponse(int idCategoria, String tipo) {
		super();
		this.idCategoria = idCategoria;
		this.tipo = tipo;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

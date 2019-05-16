package entities;

import java.io.Serializable;

public class Frutas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Integer id;
	private double preco;
	private Integer quantidade;
	private String fornecedor;

	public Frutas() {

	}

	public Frutas(String nome,Integer id, double preco, Integer quantidade, String fornecedor) {
		super();
		this.nome = nome;
		this.id = id;
		this.preco = preco;
		this.quantidade = quantidade;
		this.fornecedor = fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "Frutas [nome=" + nome + ", id=" + id + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fornecedor=" + fornecedor + "]";
	}

	

}

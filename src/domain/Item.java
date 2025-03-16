package domain;

import java.io.Serializable;

import exceptions.ItemException;

public class Item implements Serializable{

	private static final long serialVersionUID = 6790133997129548569L;
	
	private final long codigo;
	private final String descricao;
	private double preco;

	public Item(long codigo, String descricao, double preco) throws ItemException {

		if (descricao == null || descricao.isBlank() || descricao.isEmpty()) {
			throw new ItemException("Descrição informada não é válida!");
		}

		if (preco <= 0) {

			throw new ItemException("Preço deve ser maior que zero!");
		}

		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) throws ItemException {

		if (preco <= 0) {

			throw new ItemException("Preço deve ser maior que zero!");
		}
		this.preco = preco;
	}

}

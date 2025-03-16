package dtos;

import java.util.ArrayList;

import domain.Item;

public class CategoriaDto {

	private final String nome;

	private ArrayList<Item> itens = new ArrayList<>();

	public CategoriaDto(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + "]\n";
	}

}

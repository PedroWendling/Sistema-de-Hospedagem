package domain;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.CategoriaException;

public class Categoria implements Serializable {

	private static final long serialVersionUID = 112332402647613205L;

	private final String nome;

	private ArrayList<Item> itens = new ArrayList<>();

	public Categoria(String nome) throws CategoriaException {

		if (nome == null || nome.isBlank() || nome.isEmpty()) {
			throw new CategoriaException("Nome informado não é válido!");
		}
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void addItem(Item item) {
		itens.add(item);
	}

	public void removeItem(Item item) {
		itens.remove(item);
	}

}

package domain;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.ItemException;

public class Conta implements IConta, Serializable {

	private static final long serialVersionUID = 33779269584752491L;

	private ArrayList<ItemConta> itens = new ArrayList<>();

	public void addItem(Item item, int qtd) {
		try {
			itens.add(new ItemConta(item, qtd));
		} catch (ItemException e) {
			System.out.println("Erro ao adicionar item: " + e.getMessage());
		}
	}

	public void removeItem(int index) {
		itens.remove(index);
	}

	public double getTotal() {

		double totalConsumo = 0.0;

		for (ItemConta i : itens) {
			totalConsumo += i.getTotal();
		}

		return totalConsumo;
	}

	public StringBuilder listar() {

		StringBuilder sb = new StringBuilder();
		sb.append("Itens Consumidos:\n\n");

		if (itens.size() == 0) {
			sb.append("Nao ha itens consumidos" + "\n");
			return sb;
		}

		for (ItemConta i : itens) {
			sb.append("Data: " + i.getDataHora() + "\tItem: " + i.getDescricao() + "\tValor: " + i.getPreco() + " * "
					+ i.getQtde() + " = R$ " + i.getTotal() + "\n");
		}

		sb.append("\nTotal Consumo:" + this.getTotal() + "\n");
		return sb;
	}

}

package dtos;

import java.util.ArrayList;

public class ContaDto {

	private ArrayList<ItemContaDto> itens = new ArrayList<>();

	public double getTotal() {

		double totalConsumo = 0.0;

		for (ItemContaDto i : itens) {
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

		for (ItemContaDto i : itens) {
			sb.append("Data: " + i.getDataHora() + "\tItem: " + i.getDescricao() + "\tValor: " + i.getPreco() + " * "
					+ i.getQtde() + " = R$ " + i.getPreco() * i.getQtde() + "\n");
		}

		sb.append("\nTotal Consumo:" + this.getTotal());
		return sb;
	}

}

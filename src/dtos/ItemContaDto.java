package dtos;

import java.util.Date;

public class ItemContaDto {

	private final Date dataHora;
	private final double preco;
	private final int qtde;

	private final ItemDto item;

	public ItemContaDto(ItemDto item, int qtde) {
		super();
		this.dataHora = new Date();
		this.preco = item.getPreco();
		this.qtde = qtde;
		this.item = item;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public double getPreco() {
		return preco;
	}

	public int getQtde() {
		return qtde;
	}

	public String getDescricao() {
		return item.getDescricao();
	}

	public double getTotal() {
		return this.getQtde() * this.getPreco();
	}

	@Override
	public String toString() {
		return "ItemContaDto [data=" + dataHora + ", preco=" + preco + ", qtde=" + qtde + "]";
	}

}

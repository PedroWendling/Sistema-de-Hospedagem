package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import exceptions.ItemException;

public class ItemConta implements Serializable {

	private static final long serialVersionUID = -2740641483000066169L;
	private final Date dataHora;
	private final double preco;
	private final int qtde;

	private final Item item;

	public ItemConta(Item item, int qtde) throws ItemException {
	    if (qtde <= 0) {
	        throw new ItemException("A quantidade não pode ser menor ou igual a zero!");
	    }
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
		BigDecimal total = BigDecimal.valueOf(this.getQtde()).multiply(BigDecimal.valueOf(this.getPreco())).setScale(2,
				RoundingMode.HALF_UP);

		return total.doubleValue();
	}

}

package dtos;

import java.util.Date;

import domain.Pagamento;
import enums.ETipoPagamento;
import exceptions.PagamentoException;

public class PagamentoDto {

	private final ETipoPagamento tipo;
	private final Date data;
	private final double valor;

	public PagamentoDto(ETipoPagamento tipo, double valor) {
		this.data = new Date();
		this.tipo = tipo;
		this.valor = valor;
	}

	public Pagamento criarPagamento(ETipoPagamento tipo, double valor) throws PagamentoException {
		return new Pagamento(tipo, valor);

	}

	public ETipoPagamento getTipo() {
		return tipo;
	}

	public Date getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "PagamentoDto [data=" + data + ", valor=" + valor + ", tipo=" + tipo + "]";
	}

}

package domain;

import java.io.Serializable;
import java.util.Date;

import enums.ETipoPagamento;
import exceptions.PagamentoException;

public class Pagamento implements Serializable{

	private static final long serialVersionUID = -5048801880935936731L;
	
	private final ETipoPagamento tipo;
	private final Date data;
	private final double valor;

	public Pagamento(ETipoPagamento tipo, double valor) throws PagamentoException {
		
		if ( valor <= 0) {
			throw new PagamentoException("O valor não pode ser menor ou igual a zero!");
		}
		this.data = new Date();
		this.tipo = tipo;
		this.valor = valor;
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

}

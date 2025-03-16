package domain;

import java.io.Serializable;

import enums.EEstadoOcupacao;
import exceptions.AcomodacaoException;

public class Acomodacao implements IAcomodacao, Serializable {

	private static final long serialVersionUID = -3148413778643721157L;

	private final int numero;
	private final int ocupacaoMax;
	private EEstadoOcupacao estadoOcupacao;

	private TipoAcomodacao tipo;

	public Acomodacao(int numero, int ocupacaoMax, TipoAcomodacao tipo) throws AcomodacaoException {

		if (numero <= 0) {
			throw new AcomodacaoException("Numero deve ser inteiro maior que zero!");
		}

		if (ocupacaoMax <= 0) {
			throw new AcomodacaoException("Ocupação Máxima deve ser inteiro maior que zero!");
		}

		if (tipo == null) {
			throw new AcomodacaoException("Tipo da Acomodacao deve ser informado (não nulo)!");
		}

		this.numero = numero;
		this.ocupacaoMax = ocupacaoMax;
		this.tipo = tipo;
		this.estadoOcupacao = EEstadoOcupacao.DISPONIVEL;
	}

	@Override
	public int getNumero() {
		return numero;
	}

	@Override
	public int getOcupacaoMax() {
		return ocupacaoMax;
	}

	@Override
	public EEstadoOcupacao getEstadoOcupacao() {
		return estadoOcupacao;
	}

	@Override
	public void setEstadoOcupacao(EEstadoOcupacao estadoOcupacao) {
		this.estadoOcupacao = estadoOcupacao;
	}

	@Override
	public String getTipo() {
		return tipo.getNome();
	}

	@Override
	public double getTarifaDiaria() {
		return tipo.getTarifaDiaria();
	}

	@Override
	public double getAdicionalAcompanhante() {
		return tipo.getAdicionalAcompanhante();
	}

}

package domain;

import java.io.Serializable;

import exceptions.TipoAcomodacaoException;

public class TipoAcomodacao implements Serializable {

	private static final long serialVersionUID = -7743288878774212647L;

	private final String nome;
	private double tarifaDiaria;
	private double adicionalAcompanhante;

	public TipoAcomodacao(String nome, double tarifaDiaria, double adicionalAcompanhante)
			throws TipoAcomodacaoException {

		if (nome == null || nome.isBlank() || nome.isEmpty()) {
			throw new TipoAcomodacaoException("Tipo deve ter um nome informado!");
		}

		this.nome = nome;
		this.setTarifaDiaria(tarifaDiaria);
		this.setAdicionalAcompanhante(adicionalAcompanhante);
	}

	public String getNome() {
		return nome;
	}

	public double getTarifaDiaria() {
		return tarifaDiaria;
	}

	public void setTarifaDiaria(double tarifaDiaria) throws TipoAcomodacaoException {

		if (tarifaDiaria <= 0) {
			throw new TipoAcomodacaoException("Tarifa diaria deve ter valor maior que zero.");
		}

		this.tarifaDiaria = tarifaDiaria;
	}

	public double getAdicionalAcompanhante() {
		return adicionalAcompanhante;
	}

	public void setAdicionalAcompanhante(double adicionalAcompanhante) throws TipoAcomodacaoException {

		if (tarifaDiaria <= 0 || adicionalAcompanhante <= 0) {
			throw new TipoAcomodacaoException("Tarifa de adicional de acompanhante deve ter valor maior que zero.");
		}

		this.adicionalAcompanhante = adicionalAcompanhante;
	}

}

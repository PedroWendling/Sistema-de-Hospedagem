package dtos;

import enums.EEstadoOcupacao;

public class AcomodacaoDto {

	private int numero;
	private int ocupacaoMax;
	private EEstadoOcupacao estadoOcupacao;
	private String tipo;
	private double tarifaDiaria;
	private double adicionalAcompanhante;

	public AcomodacaoDto(int numero, int ocupacaoMax, String tipo) {
		this.numero = numero;
		this.ocupacaoMax = ocupacaoMax;
		this.tipo = tipo;
	}

	public AcomodacaoDto(int numero, int ocupacaoMax, EEstadoOcupacao estadoOcupacao, String tipo, double tarifaDiaria,
			double adicionalAcompanhante) {
		this(numero, ocupacaoMax, tipo);
		this.estadoOcupacao = estadoOcupacao;
		this.tarifaDiaria = tarifaDiaria;
		this.adicionalAcompanhante = adicionalAcompanhante;
	}

	public int getNumero() {
		return numero;
	}

	public int getOcupacaoMax() {
		return ocupacaoMax;
	}

	public EEstadoOcupacao getEstadoOcupacao() {
		return estadoOcupacao;
	}

	public String getTipo() {
		return tipo;
	}

	public double getTarifaDiaria() {
		return tarifaDiaria;
	}

	public double getAdicionalAcompanhante() {
		return adicionalAcompanhante;
	}

	@Override
	public String toString() {
		return "[numero=" + numero + ", ocupacaoMax=" + ocupacaoMax + ", estadoOcupacao=" + estadoOcupacao + "\ntipo="
				+ tipo + ", tarifaDiaria=" + tarifaDiaria + ", adicionalAcompanhante=" + adicionalAcompanhante + "]";
	}

}

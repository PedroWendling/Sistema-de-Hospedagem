package dtos;

public class TipoAcomodacaoDto {

	private String nome;
	private double tarifaDiaria;
	private double adicionalAcompanhante;

	public TipoAcomodacaoDto(String nome, double tarifaDiaria, double adicionalAcompanhante) {
		super();
		this.nome = nome;
		this.tarifaDiaria = tarifaDiaria;
		this.adicionalAcompanhante = adicionalAcompanhante;
	}

	public String getNome() {
		return nome;
	}

	public double getTarifaDiaria() {
		return tarifaDiaria;
	}

	public double getAdicionalAcompanhante() {
		return adicionalAcompanhante;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", tarifaDiaria=" + tarifaDiaria + ", adicionalAcompanhante=" + adicionalAcompanhante
				+ "]\n";
	}

}

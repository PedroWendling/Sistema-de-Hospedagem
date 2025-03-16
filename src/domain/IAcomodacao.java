package domain;

import enums.EEstadoOcupacao;

public interface IAcomodacao {

	public int getNumero();

	public int getOcupacaoMax();

	public EEstadoOcupacao getEstadoOcupacao();

	public void setEstadoOcupacao(EEstadoOcupacao e);

	public String getTipo();

	public double getTarifaDiaria();

	public double getAdicionalAcompanhante();

}

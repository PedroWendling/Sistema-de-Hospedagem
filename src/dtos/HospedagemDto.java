package dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import domain.Conta;
import domain.IConta;
import domain.IHospede;

public class HospedagemDto {

	private final String id;
	private Date checkin;
	private Date checkout;

	private ArrayList<PagamentoDto> pagamento = new ArrayList<>();
	private String hospede;
	private ArrayList<IHospede> acompanhantes = new ArrayList<>();
	private Integer acomodacao;
	private IConta conta;

	public HospedagemDto(String hospede, Integer acomodacao) {
		this.id = UUID.randomUUID().toString();
		this.hospede = hospede;
		this.acomodacao = acomodacao;
		this.checkin = new Date();
		this.conta = new Conta();
	}

	public HospedagemDto(String id, String hospede, Integer acomodacao, Date checkin) {
		this.id = id;
		this.hospede = hospede;
		this.acomodacao = acomodacao;
		this.checkin = checkin;
		this.conta = new Conta();
	}

	public String getId() {
		return this.id;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public ArrayList<PagamentoDto> getPagamento() {
		return pagamento;
	}

	public String getHospede() {
		return hospede;
	}

	public ArrayList<IHospede> getAcompanhantes() {
		return acompanhantes;
	}

	public Integer getAcomodacao() {
		return acomodacao;
	}

	public IConta getConta() {
		return conta;
	}

	@Override
	public String toString() {
		return "-----------------------------------------------------------------------------------------------------\n\t ID : "
				+ id + "\n\t Checkin: " + checkin + "\n\t Hospede: " + hospede + "\n\t Acomodacao: " + acomodacao + "\n"
				+ "-----------------------------------------------------------------------------------------------------\n";
	}

}

package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import enums.EEstadoOcupacao;
import enums.ETipoPagamento;
import exceptions.HospedagemException;
import exceptions.PagamentoException;
import util.UtilDate;

public class Hospedagem implements Serializable {

	private static final long serialVersionUID = 4732903490933255540L;

	private static int inicioCheckin = 13;
	private static int limiteCheckout = 12;

	private final String id;
	private Date checkin;
	private Date checkout;

	private ArrayList<Pagamento> pagamento = new ArrayList<>();
	private IHospede hospede;
	private ArrayList<IHospede> acompanhantes = new ArrayList<>();
	private IAcomodacao acomodacao;
	private IConta conta;

	public Hospedagem(IHospede hospede, IAcomodacao acomodacao) throws HospedagemException {

		if (acomodacao.getEstadoOcupacao() != EEstadoOcupacao.DISPONIVEL) {
			throw new HospedagemException("Acomodação indisponível!");
		}

		this.id = UUID.randomUUID().toString();
		this.hospede = hospede;
		this.acomodacao = acomodacao;
		this.checkin = new Date();
		this.conta = new Conta();
		acomodacao.setEstadoOcupacao(EEstadoOcupacao.OCUPADO);
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

	public ArrayList<Pagamento> getPagamento() {
		return pagamento;
	}

	public IHospede getHospede() {
		return hospede;
	}

	public ArrayList<IHospede> getAcompanhantes() {
		return acompanhantes;
	}

	public IAcomodacao getAcomodacao() {
		return acomodacao;
	}

	public IConta getConta() {
		return conta;
	}

	public void setCheckout(Date date) throws HospedagemException {
		if (date.before(checkin)) {
			throw new HospedagemException("Data de checkout não pode ser menor que data de check-in");
		}
		this.checkout = date;
	}

	public void fazerPagamento(ETipoPagamento tipo, double valor) throws PagamentoException {

		if (valor <= 0 || valor > getValorFinal()) {
			throw new PagamentoException("Valor inválido para fazer o pagamento");
		}

		pagamento.add(new Pagamento(tipo, valor));
		return;
	}

	public void fazerCheckout() throws HospedagemException {

		if (checkout != null) {
			throw new HospedagemException("Checkout já foi realizado para a Hospedagem de id: " + id);
		}

		this.checkout = new Date();
		acomodacao.setEstadoOcupacao(EEstadoOcupacao.MANUTENCAO);

		return;

	}

	private double calcularDiariaTotal() {

		double valorDiaria = acomodacao.getTarifaDiaria()
				+ acomodacao.getAdicionalAcompanhante() * this.acompanhantes.size();
		int tempoEstadia;

		if (checkout != null) {
			tempoEstadia = UtilDate.calculoDeDias(checkin, checkout, inicioCheckin, limiteCheckout);
		} else {
			tempoEstadia = UtilDate.calculoDeDias(checkin, null, inicioCheckin, limiteCheckout);
		}

		return new BigDecimal(valorDiaria * tempoEstadia).setScale(2, RoundingMode.HALF_UP).doubleValue(); 
	}

	private double getValorFinal() {

		double valorFinal = calcularDiariaTotal() + conta.getTotal();

		for (Pagamento i : pagamento) {
			valorFinal -= i.getValor();
		}
		return new BigDecimal(valorFinal).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public void addAcompanhantes(List<IHospede> acompanhanteList) throws HospedagemException {

		if (acompanhanteList.size() + acompanhantes.size() + 1 > acomodacao.getOcupacaoMax()) {
			throw new HospedagemException(
					"Acomodação com ID " + acomodacao.getNumero() + " ultrapassa sua ocupação máxima");
		}

		for (IHospede pessoa : acompanhanteList) {

			if (acompanhantes.contains(pessoa)) {
				throw new HospedagemException("Acompanhante de CPF " + pessoa.getCpf() + " já adicionado.");

			} else if (hospede.getCpf() == pessoa.getCpf()) {
				throw new HospedagemException("Não é possível adicionar o titular da reserva como acompanhante.");

			}

			acompanhantes.add(pessoa);
		}

		return;
	}

	public void addItem(Item item, int qtde) throws HospedagemException {

		if (checkout != null) {
			throw new HospedagemException("Impossível adicionar itens em uma hospedagem finalizada");
		}

		conta.addItem(item, qtde);
	}

	public void removeItem(int index) throws HospedagemException {

		if (checkout != null) {
			throw new HospedagemException("Impossível remover itens em uma hospedagem finalizada");
		}

		conta.removeItem(index);
	}

	public StringBuilder listar() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nDADOS DA HOSPEDAGEM Nº " + id + "\n\n");
		sb.append(
				"Hospede: " + hospede.getNome() + "\tNumero de Hospedes: " + (getAcompanhantes().size() + 1) + "\n\n");
		sb.append("Acomodacao: " + acomodacao.getNumero() + "\tTipo: " + acomodacao.getTipo() + "\tDiaria: "
				+ acomodacao.getTarifaDiaria() + " R$ \n\n");
		sb.append("Data CheckIn: " + checkin);

		if (checkout != null) {

			sb.append("\tData CheckOut: " + checkout);
			sb.append("\n\n");
			sb.append("Tempo de Estadia: " + UtilDate.calculoDeDias(checkin, checkout, inicioCheckin, limiteCheckout)
					+ " dias\n\n");

			sb.append("Valor Diarias: " + this.calcularDiariaTotal() + "\n");
		} else {
			sb.append("\n\nTempo de Estadia: " + UtilDate.calculoDeDias(checkin, null, inicioCheckin, limiteCheckout)
					+ " dias\n");
		}

		sb.append("\n\n------------------------\n\n");
		sb.append(conta.listar());
		sb.append("\n------------------------\n\n");

		if (pagamento.size() != 0) {
			sb.append("Pagamentos Realizados:\n\n");

			for (Pagamento p : pagamento) {
				sb.append("Data: " + p.getData() + "\t Valor: " + p.getValor() + "\t Método: " + p.getTipo() + "\n");
			}
			sb.append("\n\n");

		}

		sb.append("Valor Final: " + this.getValorFinal() + "R$\n\n");
		return sb;
	}
}

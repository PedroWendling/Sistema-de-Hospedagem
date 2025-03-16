package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Hospedagem;
import domain.Hospede;
import domain.IAcomodacao;
import domain.IHospede;
import domain.Item;
import dtos.HospedagemDto;
import enums.EEstadoOcupacao;
import enums.ETipoPagamento;
import exceptions.AcomodacaoException;
import exceptions.HospedagemException;
import exceptions.HospedeException;
import exceptions.ItemException;
import exceptions.PagamentoException;

public class HospedagemController implements Serializable {

	private static final long serialVersionUID = 8572018683613881038L;

	private Map<String, Hospedagem> ids;

	protected HospedagemController() {
		ids = new TreeMap<>();
	}

	public void createHospedagem(HospedagemDto h) throws HospedagemException, AcomodacaoException, HospedeException {

		IAcomodacao acomodacao = MainController.getAcomodacaoController().getAcomodacao(h.getAcomodacao());
		IHospede hospede = MainController.getHospedeController().getHospede(h.getHospede());

		if (acomodacao == null) {
			throw new AcomodacaoException("Acomodacao de ID " + h.getAcomodacao() + " não encontrada!");
		}

		if (hospede == null) {
			throw new HospedeException("Hospede de CPF " + h.getHospede() + " não encontrado!");
		}

		if (ids == null) {
			ids = new TreeMap<>();
		}

		Hospedagem hospedagem = new Hospedagem(hospede, acomodacao);
		acomodacao.setEstadoOcupacao(EEstadoOcupacao.OCUPADO);

		ids.put(hospedagem.getId(), hospedagem);

		MainController.save();
	}

	protected Hospedagem getHospedagem(String id) throws HospedagemException {
		Hospedagem h = ids.get(id);
		if (h == null) {
			throw new HospedagemException("Hospedagem com ID " + id + " não encontrada.");
		}
		return h;
	}

	public List<HospedagemDto> getHospedagens() {

		List<HospedagemDto> lista = new ArrayList<>();

		Set<Map.Entry<String, Hospedagem>> entries = ids.entrySet();

		Hospedagem h;

		for (Map.Entry<String, Hospedagem> e : entries) {

			h = e.getValue();

			String id = h.getId();
			String hospedeCpf = h.getHospede().getCpf();
			Integer acomodacaoCod = h.getAcomodacao().getNumero();
			Date checkin = h.getCheckin();

			lista.add(new HospedagemDto(id, hospedeCpf, acomodacaoCod, checkin));
		}

		return lista;
	}

	public Set<String> getKeysHospedagem() {
		return ids.keySet();
	}

	public void fazerPagamento(String hospedagemID, ETipoPagamento tipo, double valor)
			throws HospedagemException, PagamentoException {

		Hospedagem hospedagem = getHospedagem(hospedagemID);

		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID" + hospedagemID + " não encontrada.");
		}

		hospedagem.fazerPagamento(tipo, valor);
		MainController.save();
	}

	public void fazerCheckout(String hospedagemID) throws HospedagemException {

		Hospedagem hospedagem = getHospedagem(hospedagemID);

		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + hospedagemID + " não encontrada.");
		}

		hospedagem.fazerCheckout();
		MainController.save();

	}

	public void addAcompanhantes(String hospedagemID, List<String> acompanhanteCpf)
			throws HospedagemException, HospedeException {

		Hospedagem hospedagem = getHospedagem(hospedagemID);
		Hospede hospede;
		List<IHospede> acompanhantes = new ArrayList<>();

		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + hospedagemID + " não encontrada.");
		}

		for (String cpf : acompanhanteCpf) {

			hospede = MainController.getHospedeController().getHospede(cpf);

			if (hospede == null) {
				throw new HospedeException("Hospede de CPF " + acompanhanteCpf + " não encontrado.");
			}

			acompanhantes.add(hospede);
		}

		hospedagem.addAcompanhantes(acompanhantes);
		MainController.save();
	}

	public void addItem(String hospedagemID, long itemCod, int qtde) throws HospedagemException, ItemException {

		Item item = MainController.getCatalogoController().getItem(itemCod);

		if (item == null) {
			throw new ItemException("Item com código " + itemCod + " não encontrado.");
		}

		Hospedagem hospedagem = getHospedagem(hospedagemID);

		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + hospedagemID + " não encontrada.");
		}

		hospedagem.addItem(item, qtde);
		MainController.save();
	}

	public void removeItem(String hospedagemID, int index) throws HospedagemException {

		Hospedagem hospedagem = getHospedagem(hospedagemID);

		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + hospedagemID + " não encontrada.");
		}

		hospedagem.removeItem(index);
		MainController.save();
	}

	public StringBuilder listar(String hospedagemID) throws HospedagemException {

		Hospedagem hospedagem = getHospedagem(hospedagemID);

		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + hospedagemID + " não encontrada!");
		}

		return hospedagem.listar();
	}
}

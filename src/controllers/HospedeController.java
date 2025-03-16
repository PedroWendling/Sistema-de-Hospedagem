package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Hospede;
import dtos.HospedeDto;
import exceptions.HospedeException;

public class HospedeController implements Serializable {

	private static final long serialVersionUID = 4480250874919697161L;

	private Map<String, Hospede> cpfs;

	protected HospedeController() {
		cpfs = new TreeMap<>();
	}

	public void createHospede(HospedeDto h) throws HospedeException {

		if (cpfs.containsKey(h.getCpf())) {
			throw new HospedeException("já existe hospede com o cpf - " + h.getCpf());
		}

		Hospede hospede = new Hospede(h.getCpf(), h.getNome(), h.getTelefone(), h.getEmail());
		cpfs.put(hospede.getCpf(), hospede);

		MainController.save();
	}

	protected Hospede getHospede(String cpf) throws HospedeException {
		Hospede h = cpfs.get(cpf);
		if (h == null) {
			throw new HospedeException("Não existe hospede de cpf: " + cpf);
		}
		return h;
	}

	public List<HospedeDto> getHospedes() {

		List<HospedeDto> lista = new ArrayList<>();

		Set<Map.Entry<String, Hospede>> entries = cpfs.entrySet();

		Hospede h;

		for (Map.Entry<String, Hospede> e : entries) {
			h = e.getValue();
			lista.add(new HospedeDto(h.getCpf(), h.getNome(), h.getTelefone(), h.getEmail()));
		}

		return lista;
	}

	public Set<String> getKeysHospedes() {
		return cpfs.keySet();
	}

	public void setNome(String cpf, String nome) throws HospedeException {
		Hospede hospede = getHospede(cpf);

		if (hospede == null) {
			throw new HospedeException("Hospede de CPF " + cpf + " não foi encontrado!");
		}

		hospede.setNome(nome);
		MainController.save();
	}

	public void setEmail(String cpf, String email) throws HospedeException {
		Hospede hospede = getHospede(cpf);

		if (hospede == null) {
			throw new HospedeException("Hospede de CPF " + cpf + " não foi encontrado!");
		}

		hospede.setEmail(email);
		MainController.save();
	}

	public void setTelefone(String cpf, long telefone) throws HospedeException {
		Hospede hospede = getHospede(cpf);

		if (hospede == null) {
			throw new HospedeException("Hospede de CPF " + cpf + " não foi encontrado!");
		}

		hospede.setTelefone(telefone);
		MainController.save();
	}

}

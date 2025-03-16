package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Acomodacao;
import domain.IAcomodacao;
import domain.TipoAcomodacao;
import dtos.AcomodacaoDto;
import dtos.TipoAcomodacaoDto;
import enums.EEstadoOcupacao;
import exceptions.AcomodacaoException;
import exceptions.TipoAcomodacaoException;

public class AcomodacaoController implements Serializable {

	private static final long serialVersionUID = 7313971448482683170L;

	private Map<String, TipoAcomodacao> tipos;
	private Map<Integer, Acomodacao> acomodacoes;

	protected AcomodacaoController() {
		tipos = new TreeMap<>();
		acomodacoes = new TreeMap<>();
	}

	public void createTipoAcomodacao(TipoAcomodacaoDto t) throws TipoAcomodacaoException {

		if (tipos.containsKey(t.getNome())) {
			throw new TipoAcomodacaoException("já existe tipo com o nome - " + t.getNome());
		}

		TipoAcomodacao tipo = new TipoAcomodacao(t.getNome(), t.getTarifaDiaria(), t.getAdicionalAcompanhante());
		tipos.put(tipo.getNome(), tipo);

		MainController.save();
	}

	protected TipoAcomodacao getTipoAcomodacao(String nome) throws TipoAcomodacaoException {
		TipoAcomodacao t = tipos.get(nome);
		if (t == null) {
			throw new TipoAcomodacaoException("Não existe tipo de acomodacao de nome: " + nome);
		}
		return t;
	}

	public List<TipoAcomodacaoDto> getTiposAcomodacao() {

		List<TipoAcomodacaoDto> lista = new ArrayList<>();

		Set<Map.Entry<String, TipoAcomodacao>> entries = tipos.entrySet();

		TipoAcomodacao t;

		for (Map.Entry<String, TipoAcomodacao> e : entries) {
			t = e.getValue();
			lista.add(new TipoAcomodacaoDto(t.getNome(), t.getTarifaDiaria(), t.getAdicionalAcompanhante()));
		}

		return lista;
	}

	public Set<String> getKeysTiposAcomodacao() {
		return tipos.keySet();
	}

	public void createAcomodacao(AcomodacaoDto acomodacaoDto) throws AcomodacaoException, TipoAcomodacaoException {

		TipoAcomodacao tipoAcomodacao = this.getTipoAcomodacao(acomodacaoDto.getTipo());

		if (acomodacoes.containsKey(acomodacaoDto.getNumero())) {
			throw new AcomodacaoException("já existe acomodação com o número - " + acomodacaoDto.getNumero());
		}

		Acomodacao acomodacao = new Acomodacao(acomodacaoDto.getNumero(), acomodacaoDto.getOcupacaoMax(),
				tipoAcomodacao);
		acomodacao.setEstadoOcupacao(EEstadoOcupacao.DISPONIVEL);
		acomodacoes.put(acomodacao.getNumero(), acomodacao);

		MainController.save();
	}

	protected Acomodacao getAcomodacao(int numero) throws AcomodacaoException {
		Acomodacao a = acomodacoes.get(numero);
		if (a == null) {
			throw new AcomodacaoException("Não existe acomodacao de numero: " + numero);
		}
		return a;
	}

	public List<AcomodacaoDto> getAcomodacoes() {

		List<AcomodacaoDto> lista = new ArrayList<>();

		Set<Map.Entry<Integer, Acomodacao>> entries = acomodacoes.entrySet();

		Acomodacao a;

		for (Map.Entry<Integer, Acomodacao> e : entries) {
			a = e.getValue();
			lista.add(new AcomodacaoDto(a.getNumero(), a.getOcupacaoMax(), a.getEstadoOcupacao(), a.getTipo(),
					a.getTarifaDiaria(), a.getAdicionalAcompanhante()));
		}

		return lista;
	}

	public Set<Integer> getKeysAcomodacoes() {
		return acomodacoes.keySet();
	}

	public void setEEstadoOcupacao(int codigo, EEstadoOcupacao estadoOcupacao) throws AcomodacaoException {

		IAcomodacao acomodacao = getAcomodacao(codigo);

		if (acomodacao == null) {
			throw new AcomodacaoException("Acomodacao de ID " + codigo + " não encontrada!");
		}

		acomodacao.setEstadoOcupacao(estadoOcupacao);
		MainController.save();
	}

	public void setTarifaDiaria(String tipo, double tarifaDiaria) throws TipoAcomodacaoException {

		TipoAcomodacao tipoAcomodacao = getTipoAcomodacao(tipo);

		if (tipoAcomodacao == null) {
			throw new TipoAcomodacaoException("TipoAcomodacao de nome " + tipo + " não encontrado!");
		}

		tipoAcomodacao.setTarifaDiaria(tarifaDiaria);
		MainController.save();
	}

	public void setAdicionalAcompanhante(String tipo, double adicionalAcompanhante) throws TipoAcomodacaoException {

		TipoAcomodacao tipoAcomodacao = getTipoAcomodacao(tipo);

		if (tipoAcomodacao == null) {
			throw new TipoAcomodacaoException("TipoAcomodacao de nome " + tipo + " não encontrado!");
		}

		tipoAcomodacao.setAdicionalAcompanhante(adicionalAcompanhante);
		MainController.save();
	}

}

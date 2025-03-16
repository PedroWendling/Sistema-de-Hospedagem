package domain;

import java.io.Serializable;

import exceptions.HospedeException;
import util.CpfValidate;

public class Hospede implements IHospede, Serializable {

	private static final long serialVersionUID = 4754642902705177936L;

	private final String cpf;
	private String nome;
	private String email;
	private long telefone;

	public Hospede(String cpf, String nome, long telefone) throws HospedeException {

		if (!CpfValidate.validate(cpf)) {
			throw new HospedeException("O CPF digitado é inválido, tente novamente.");
		}

		if (nome == null || nome.isBlank() || nome.isEmpty()) {
			throw new HospedeException("Nome informado não é válido!");
		}

		String telefoneString = String.valueOf(telefone);

		if (telefoneString.length() < 9 || telefoneString.length() > 11) {
			throw new HospedeException("O telefone deve ter entre 9 e 11 dígitos.");
		}

		if (telefoneString.chars().distinct().count() == 1) {
			throw new HospedeException("O telefone não pode conter todos os números iguais.");
		}

		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Hospede(String cpf, String nome, long telefone, String email) throws HospedeException {

		if (CpfValidate.validate(cpf) == false) {
			throw new HospedeException("O CPF digitado é invalido, tente novamente.");
		}

		if (nome == null || nome.isBlank() || nome.isEmpty()) {
			throw new HospedeException("Nome informado não é válido!");
		}

		if (telefone < 0) {
			throw new HospedeException("O telefone precisa ser um valor maior que zero!.");
		}

		String telefoneString = String.valueOf(telefone);

		if (telefoneString.length() < 9 || telefoneString.length() > 11) {
			throw new HospedeException("O telefone deve ter entre 9 e 11 dígitos.");
		}

		if (telefoneString.chars().distinct().count() == 1) {
			throw new HospedeException("O telefone não pode conter todos os números iguais.");
		}

		if(email != null) {
			if (email.isBlank() || email.isEmpty()) {
				throw new HospedeException("email informado não é válido!");
			}
	
			if (!email.contains("@") || !email.endsWith(".com")) {
				throw new HospedeException("O e-mail deve conter '@' e terminar com '.com'.");
			}
		}

		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	@Override
	public String getCpf() {
		return cpf;
	}

	@Override
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws HospedeException {

		if (nome == null || nome.isBlank()) {
			throw new HospedeException("Nome informado não é válido!");
		}

		this.nome = nome;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws HospedeException {

		if (email == null || email.isBlank() || email.isEmpty()) {
			throw new HospedeException("email informado não é válido!");
		}

		if (!email.contains("@") || !email.endsWith(".com")) {
			throw new HospedeException("O e-mail deve conter '@' e terminar com '.com'.");
		}

		this.email = email;
	}

	@Override
	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) throws HospedeException {

		String telefoneString = String.valueOf(telefone);

		if (telefoneString.length() < 8 || telefoneString.length() > 11) {
			throw new HospedeException("O telefone deve ter entre 8 e 11 dígitos.");
		}

		if (telefoneString.chars().distinct().count() == 1) {
			throw new HospedeException("O telefone não pode conter todos os números iguais.");
		}
		this.telefone = telefone;
	}

}

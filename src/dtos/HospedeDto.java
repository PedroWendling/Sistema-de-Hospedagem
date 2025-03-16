package dtos;

public class HospedeDto {

	private final String cpf;
	private String nome;
	private String email;
	private long telefone;

	public HospedeDto(String cpf, String nome, long telefone) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
	}

	public HospedeDto(String cpf, String nome, long telefone, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public long getTelefone() {
		return telefone;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", email=" + email + "]\n";
	}

}

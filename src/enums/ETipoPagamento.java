package enums;

public enum ETipoPagamento {

	PIX("PIX"), 
	DEBITO("Débito"), 
	CREDITO("Crédito");

	private String descricao;

	private ETipoPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}

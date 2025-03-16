package dtos;

public class ItemDto {

	private final long codigo;
	private final String descricao;
	private double preco;

	public ItemDto(long codigo, String descricao, double preco) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return "[codigo=" + codigo + ", preco=" + preco + ", descricao=" + descricao + "]\n";
	}
}

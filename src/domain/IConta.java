package domain;

public interface IConta {
	
	public void addItem(Item item, int qtd);
	
	public void removeItem(int index);
	
	public double getTotal();
	
	public StringBuilder listar();

}

package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Categoria;
import domain.Item;
import dtos.CategoriaDto;
import dtos.ItemDto;
import exceptions.CategoriaException;
import exceptions.ItemException;

public class CatalogoController implements Serializable {

	private static final long serialVersionUID = -5777315367006290419L;

	private Map<String, Categoria> categorias;
	private Map<Long, Item> codigos;

	protected CatalogoController() {
		categorias = new TreeMap<>();
		codigos = new TreeMap<>();
	}

	public void createCategoria(CategoriaDto c) throws CategoriaException {

		if (categorias.containsKey(c.getNome())) {
			throw new CategoriaException("já existe categoria com o nome - " + c.getNome());
		}

		Categoria categoria = new Categoria(c.getNome());
		categorias.put(categoria.getNome(), categoria);

		MainController.save();
	}

	protected Categoria getCategoria(String nome) throws CategoriaException {
		Categoria c = categorias.get(nome);
		if (c == null) {
			throw new CategoriaException("Não existe categoria de nome: " + nome);
		}
		return c;
	}

	public List<CategoriaDto> getCategorias() {

		List<CategoriaDto> lista = new ArrayList<>();

		Set<Map.Entry<String, Categoria>> entries = categorias.entrySet();

		Categoria c;

		for (Map.Entry<String, Categoria> e : entries) {
			c = e.getValue();
			lista.add(new CategoriaDto(c.getNome()));
		}

		return lista;
	}

	public Set<String> getKeysCategorias() {
		return categorias.keySet();
	}

	public void createItem(ItemDto itemDto) throws ItemException {

		if (codigos.containsKey(itemDto.getCodigo())) {
			throw new ItemException("já existe item de código - " + itemDto.getCodigo());
		}

		Item item = new Item(itemDto.getCodigo(), itemDto.getDescricao(), itemDto.getPreco());
		codigos.put(item.getCodigo(), item);

		MainController.save();
	}

	protected Item getItem(long codigo) throws ItemException {
		Item i = codigos.get(codigo);
		if (i == null) {
			throw new ItemException("Não existe item de codigo: " + codigo);
		}
		return i;
	}

	public List<ItemDto> getItens() {

		List<ItemDto> lista = new ArrayList<>();

		Set<Map.Entry<Long, Item>> entries = codigos.entrySet();

		Item i;

		for (Map.Entry<Long, Item> e : entries) {
			i = e.getValue();
			lista.add(new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco()));
		}

		return lista;
	}

	public Set<Long> getKeysItens() {
		return codigos.keySet();
	}

	public void addItemToCategoria(String categoriaNome, long itemCodigo) throws CategoriaException, ItemException {
		Categoria categoria = getCategoria(categoriaNome);

		if (categoria == null) {
			throw new CategoriaException("Categoria de nome " + categoriaNome + " não foi encontrada!");
		}

		Item item = getItem(itemCodigo);

		if (item == null) {
			throw new ItemException("Item de Código " + itemCodigo + " não foi encontrado!");
		}

		categoria.addItem(item);
		MainController.save();
	}

	public void removeItemFromCategoria(String categoriaNome, long itemCodigo)
			throws CategoriaException, ItemException {
		Categoria categoria = getCategoria(categoriaNome);

		if (categoria == null) {
			throw new CategoriaException("Categoria de nome " + categoriaNome + " não foi encontrada!");
		}

		Item item = getItem(itemCodigo);

		if (item == null) {
			throw new ItemException("Item de Código " + itemCodigo + " não foi encontrado!");
		}

		categoria.removeItem(item);
		MainController.save();
	}

	public void setPreco(long itemCodigo, double preco) throws ItemException {

		Item item = getItem(itemCodigo);

		if (item == null) {
			throw new ItemException("Item de Código " + itemCodigo + " não foi encontrado!");
		}

		item.setPreco(preco);
		MainController.save();
	}

	public ArrayList<ItemDto> getItensDeCategoria(String categoriaNome) throws CategoriaException {

		Categoria categoria = getCategoria(categoriaNome);

		ArrayList<ItemDto> itensDto = new ArrayList<>();
		for (Item item : categoria.getItens()) {
			itensDto.add(new ItemDto(item.getCodigo(), item.getDescricao(), item.getPreco()));
		}

		return itensDto;
	}
}

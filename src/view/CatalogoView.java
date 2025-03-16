package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.CatalogoController;
import controllers.MainController;
import dtos.CategoriaDto;
import dtos.ItemDto;
import exceptions.CategoriaException;
import exceptions.ItemException;

public class CatalogoView extends JFrame {

	private static final long serialVersionUID = 8961973140328447592L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel listPane;
	private JPanel formPane;
	private JPanel modPane;
	private JPanel contentListPane;
	private JTabbedPane tabbedListPane;
	private JPanel listCat;
	private JPanel listItem;
	private JTextArea textAreaListCat;
	private JTextArea textAreaListItem;
	private JComboBox<CategoriaDto> cbbCategoriaList;
	private JPanel listItemPCat;
	private JTextArea textAreaListItemPCat;
	private JTextField txtFormCatNome;
	private JTextField txtFormItemCod;
	private JTextField txtFormItemDesc;
	private JTextField txtFormItemPreco;
	private JTextField txtModItemCod;
	private JTextField txtModItemPreco;
	private JPanel catPane;
	private JTextField txtCatItemCod;
	private JTextField txtCatID;

	public CatalogoView() {
		initialize();
	}

	private void initialize() {

		setTitle("Menu de Catálogo");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 360);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		listPane = new JPanel();
		formPane = new JPanel();
		modPane = new JPanel();
		catPane = new JPanel();

		initListPane();
		initFormPane();
		initModPane();
		initCatPane();

		tabbedPane.add("Listagem", listPane);
		tabbedPane.add("Criação", formPane);
		tabbedPane.add("Alterações", modPane);
		tabbedPane.add("Categorias", catPane);

	}

	private void initListPane() {

		listPane.setLayout(new BorderLayout());

		contentListPane = new JPanel();
		contentListPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentListPane.setLayout(new BorderLayout(0, 0));

		tabbedListPane = new JTabbedPane(JTabbedPane.TOP);

		listCat = new JPanel();
		listItem = new JPanel();
		listItemPCat = new JPanel();

		tabbedListPane.add("Categorias", listCat);
		tabbedListPane.add("Itens", listItem);
		tabbedListPane.add("Item por Categoria", listItemPCat);

		contentListPane.add(tabbedListPane, BorderLayout.CENTER);

		initListCat();
		initListItem();
		initListItemPCat();

		listPane.add(contentListPane);

	}

	private void initListCat() {

		listCat.setLayout(null);

		JLabel lblListCat = new JLabel("Listagem de Categorias:");
		lblListCat.setBounds(12, 26, 262, 16);

		JButton btnListCat = new JButton("Listar");
		btnListCat.setBounds(327, 20, 109, 29);

		btnListCat.addActionListener(e -> actionListarCat());

		textAreaListCat = new JTextArea();
		textAreaListCat.setBounds(12, 60, 440, 193);

		JScrollPane scrollPaneListCat = new JScrollPane(textAreaListCat);
		scrollPaneListCat.setLocation(12, 53);
		scrollPaneListCat.setSize(426, 186);
		scrollPaneListCat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneListCat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listCat.add(lblListCat);
		listCat.add(scrollPaneListCat);
		listCat.add(btnListCat);
	}

	private void initListItem() {

		listItem.setLayout(null);

		JLabel lblListItem = new JLabel("Listagem de Itens:");
		lblListItem.setBounds(12, 26, 262, 16);

		JButton btnListItem = new JButton("Listar");
		btnListItem.setBounds(327, 20, 109, 29);

		btnListItem.addActionListener(e -> actionListarItens());

		textAreaListItem = new JTextArea();
		textAreaListItem.setBounds(12, 60, 440, 193);

		JScrollPane scrollPaneListItem = new JScrollPane(textAreaListItem);
		scrollPaneListItem.setLocation(12, 53);
		scrollPaneListItem.setSize(426, 186);
		scrollPaneListItem.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneListItem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listItem.add(lblListItem);
		listItem.add(scrollPaneListItem);
		listItem.add(btnListItem);
	}

	private void initListItemPCat() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		listItemPCat.setLayout(null);

		cbbCategoriaList = new JComboBox<>(new Vector<>(catalogoController.getCategorias()));
		cbbCategoriaList.setBounds(12, 22, 303, 27);

		JButton btnListItemPCat = new JButton("Listar");
		btnListItemPCat.setBounds(327, 20, 109, 29);

		btnListItemPCat.addActionListener(e -> actionListarItensPorCategoria());

		textAreaListItemPCat = new JTextArea();
		textAreaListItemPCat.setBounds(12, 60, 440, 193);

		JScrollPane scrollPaneListItemPCat = new JScrollPane(textAreaListItemPCat);
		scrollPaneListItemPCat.setLocation(12, 60);
		scrollPaneListItemPCat.setSize(426, 179);
		scrollPaneListItemPCat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneListItemPCat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listItemPCat.add(cbbCategoriaList);
		listItemPCat.add(scrollPaneListItemPCat);
		listItemPCat.add(btnListItemPCat);

	}

	private void actionListarCat() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		textAreaListCat.setText(null);

		for (CategoriaDto categoria : catalogoController.getCategorias()) {
			textAreaListCat.append(categoria.toString());
		}

	}

	private void actionListarItens() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		textAreaListItem.setText(null);

		for (ItemDto item : catalogoController.getItens()) {
			textAreaListItem.append(item.toString());
		}
	}

	private void actionListarItensPorCategoria() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		CategoriaDto categoria = (CategoriaDto) cbbCategoriaList.getSelectedItem();

		textAreaListItemPCat.setText(null);

		try {
			ArrayList<ItemDto> itens = catalogoController.getItensDeCategoria(categoria.getNome());

			for (ItemDto item : itens) {
				textAreaListItemPCat.append(item.toString());
			}
		} catch (CategoriaException e) {

			JOptionPane.showMessageDialog(this, "Erro ao recuperar Itens");
			return;
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void initFormPane() {

		formPane.setLayout(null);

		JLabel lblFormCat = new JLabel("Criação de Categoria");
		lblFormCat.setBounds(17, 11, 170, 16);

		JLabel lblFormCatNome = new JLabel("Nome:");
		lblFormCatNome.setBounds(17, 40, 61, 16);

		txtFormCatNome = new JTextField();
		txtFormCatNome.setBounds(234, 35, 170, 26);
		txtFormCatNome.setColumns(10);

		JButton btnFormCatSalvar = new JButton("Salvar");
		btnFormCatSalvar.setBounds(170, 72, 117, 29);

		btnFormCatSalvar.addActionListener(e -> actionCriarCategoria());

		JLabel lblFormItem = new JLabel("Criação de Item");
		lblFormItem.setBounds(17, 103, 201, 16);

		JLabel lblFormItemCod = new JLabel("Código:");
		lblFormItemCod.setBounds(17, 130, 117, 16);

		txtFormItemCod = new JTextField();
		txtFormItemCod.setBounds(234, 125, 170, 26);
		txtFormItemCod.setColumns(10);

		JLabel lblFormItemDesc = new JLabel("Descrição:");
		lblFormItemDesc.setBounds(17, 167, 170, 16);

		txtFormItemDesc = new JTextField();
		txtFormItemDesc.setBounds(234, 162, 170, 26);
		txtFormItemDesc.setColumns(10);

		JLabel lblFormItemPreco = new JLabel("Preço:");
		lblFormItemPreco.setBounds(17, 206, 170, 16);

		txtFormItemPreco = new JTextField();
		txtFormItemPreco.setBounds(234, 201, 170, 26);
		txtFormItemPreco.setColumns(10);

		JButton btnFormItemSalvar = new JButton("Salvar");
		btnFormItemSalvar.setBounds(170, 243, 117, 29);

		btnFormItemSalvar.addActionListener(e -> actionCriarItem());

		formPane.add(lblFormCat);
		formPane.add(lblFormCatNome);
		formPane.add(txtFormCatNome);
		formPane.add(btnFormCatSalvar);

		formPane.add(lblFormItem);
		formPane.add(lblFormItemCod);
		formPane.add(txtFormItemCod);
		formPane.add(lblFormItemDesc);
		formPane.add(txtFormItemDesc);
		formPane.add(lblFormItemPreco);
		formPane.add(txtFormItemPreco);
		formPane.add(btnFormItemSalvar);
	}

	private void actionCriarCategoria() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		String nome = txtFormCatNome.getText();

		try {
			catalogoController.createCategoria(new CategoriaDto(nome));
		} catch (CategoriaException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Categoria");
			return;
		} catch (Exception e) {

			e.printStackTrace();

		}
		txtFormCatNome.setText(null);
	}

	private void actionCriarItem() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		long codigo = Long.parseLong(txtFormItemCod.getText());
		String descricao = txtFormItemDesc.getText();
		double preco = Double.parseDouble(txtFormItemPreco.getText());

		try {
			catalogoController.createItem(new ItemDto(codigo, descricao, preco));
		} catch (ItemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao criar Item");
			return;
		} catch (Exception e) {

			e.printStackTrace();
		}
		txtFormItemCod.setText(null);
		txtFormItemDesc.setText(null);
		txtFormItemPreco.setText(null);
	}

	private void initModPane() {

		modPane.setLayout(null);

		JLabel lblMod = new JLabel("Alteração de Item");
		lblMod.setBounds(17, 11, 170, 16);

		JLabel lblModItemCod = new JLabel("Código:");
		lblModItemCod.setBounds(17, 45, 138, 16);

		txtModItemCod = new JTextField();
		txtModItemCod.setBounds(230, 40, 170, 26);
		txtModItemCod.setColumns(10);

		JLabel lblModItemPreco = new JLabel("Preço:");
		lblModItemPreco.setBounds(17, 86, 138, 16);

		txtModItemPreco = new JTextField();
		txtModItemPreco.setBounds(230, 81, 170, 26);
		txtModItemPreco.setColumns(10);

		JButton btnModPrecoSalvar = new JButton("Salvar");
		btnModPrecoSalvar.setBounds(173, 243, 117, 29);

		btnModPrecoSalvar.addActionListener(e -> actionAlterarPreco());

		modPane.add(lblMod);
		modPane.add(lblModItemCod);
		modPane.add(txtModItemCod);
		modPane.add(lblModItemPreco);
		modPane.add(txtModItemPreco);
		modPane.add(btnModPrecoSalvar);
	}

	private void actionAlterarPreco() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		long codigo = Long.parseLong(txtModItemCod.getText());
		double preco = Double.parseDouble(txtModItemPreco.getText());

		try {
			catalogoController.setPreco(codigo, preco);
		} catch (ItemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao alterar Item");
			return;
		} catch (Exception e) {

			e.printStackTrace();
		}

		txtModItemPreco.setText(null);
		txtModItemCod.setText(null);

	}

	private void initCatPane() {

		catPane.setLayout(null);

		JLabel lblCat = new JLabel("Adicionar ou Remover Item em Categoria");
		lblCat.setBounds(17, 11, 321, 16);

		JLabel lblCatItemCod = new JLabel("Código do Item:");
		lblCatItemCod.setBounds(17, 98, 149, 16);

		txtCatItemCod = new JTextField();
		txtCatItemCod.setBounds(209, 93, 170, 26);
		txtCatItemCod.setColumns(10);

		JLabel lblCatID = new JLabel("Nome da Categoria:");
		lblCatID.setBounds(17, 45, 202, 16);

		txtCatID = new JTextField();
		txtCatID.setBounds(209, 40, 170, 26);
		txtCatID.setColumns(10);

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(102, 243, 117, 29);

		btnAdd.addActionListener(e -> actionAdicionarItemNaCategoria());

		JButton btnRmv = new JButton("Remover");
		btnRmv.setBounds(221, 243, 117, 29);

		btnRmv.addActionListener(e -> actionRemoverItemDaCategoria());

		catPane.add(lblCat);
		catPane.add(lblCatItemCod);
		catPane.add(txtCatItemCod);
		catPane.add(lblCatID);
		catPane.add(txtCatID);
		catPane.add(btnAdd);
		catPane.add(btnRmv);
	}

	private void actionAdicionarItemNaCategoria() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		String nome = txtCatID.getText();
		long codigo = Long.parseLong(txtCatItemCod.getText());

		try {
			catalogoController.addItemToCategoria(nome, codigo);
		} catch (ItemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao adicionar Item");
			return;
		} catch (CategoriaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao adicionar Item");
			return;
		} catch (Exception e) {

			e.printStackTrace();
		}
		txtCatID.setText(null);
		txtCatItemCod.setText(null);
	}

	private void actionRemoverItemDaCategoria() {

		CatalogoController catalogoController = MainController.getCatalogoController();

		String nome = txtCatID.getText();
		long codigo = Long.parseLong(txtCatItemCod.getText());

		try {
			catalogoController.removeItemFromCategoria(nome, codigo);
		} catch (ItemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao remover Item");
			return;
		} catch (CategoriaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao remover Item");
			return;
		} catch (Exception e) {

			e.printStackTrace();
		}

		txtCatID.setText(null);
		txtCatItemCod.setText(null);
	}

}

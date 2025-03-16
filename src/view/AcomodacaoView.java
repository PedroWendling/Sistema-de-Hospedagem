package view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.AcomodacaoController;
import controllers.MainController;

import dtos.AcomodacaoDto;
import dtos.TipoAcomodacaoDto;
import enums.EEstadoOcupacao;
import exceptions.AcomodacaoException;
import exceptions.TipoAcomodacaoException;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class AcomodacaoView extends JFrame {

	private static final long serialVersionUID = -2138847852583567229L;

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JButton btnSalvar, btnCancelar;
	private JPanel formPane, listPane;
	private JTextArea textAreaList;
	private JPanel modPane;

	private JTextField txtNumero;
	private JTextField txtOcupMax;

	private JComboBox<TipoAcomodacaoDto> cbbTipoAcomodacaoForm;
	private JComboBox<TipoAcomodacaoDto> cbbTipoAcomodacaoList;
	private JComboBox<AcomodacaoDto> cbbAcomodacaoMod;

	private JButton btnModSalvar;
	private JButton btnModCancelar;

	private JRadioButton radioManu;
	private JRadioButton radioLivre;

	public AcomodacaoView() {
		initialize();
	}

	private void initialize() {

		setTitle("Menu de Acomodações");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		listPane = new JPanel();
		formPane = new JPanel();
		modPane = new JPanel();

		initListPane();
		initFormPane();
		initGerPane();

		tabbedPane.add("Listagem", listPane);
		tabbedPane.add("Criação", formPane);
		tabbedPane.add("Gerenciamento", modPane);
	}

	private void initListPane() {

		AcomodacaoController controle = MainController.getAcomodacaoController();

		listPane.setLayout(null);

		cbbTipoAcomodacaoList = new JComboBox<>(new Vector<>(controle.getTiposAcomodacao()));
		cbbTipoAcomodacaoList.setBounds(12, 22, 330, 27);

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(346, 20, 109, 29);
		btnListar.addActionListener(e -> actionListar());

		textAreaList = new JTextArea();
		textAreaList.setBounds(12, 60, 440, 193);
		
		JScrollPane scrollAreaList = new JScrollPane(textAreaList);
		scrollAreaList.setLocation(12, 53);
		scrollAreaList.setSize(426, 186);
		scrollAreaList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollAreaList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listPane.add(cbbTipoAcomodacaoList);
		listPane.add(scrollAreaList);
		listPane.add(btnListar);
	}

	private void initFormPane() {

		AcomodacaoController controle = MainController.getAcomodacaoController();

		formPane.setLayout(null);

		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(17, 27, 109, 16);

		txtNumero = new JTextField();
		txtNumero.setBounds(231, 22, 200, 26);
		txtNumero.setColumns(10);

		JLabel lblOcupMax = new JLabel("Ocupação Máxima");
		lblOcupMax.setBounds(17, 65, 147, 16);

		txtOcupMax = new JTextField();
		txtOcupMax.setBounds(231, 60, 200, 26);
		txtOcupMax.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(17, 107, 76, 16);

		cbbTipoAcomodacaoForm = new JComboBox<TipoAcomodacaoDto>(
				new Vector<TipoAcomodacaoDto>(controle.getTiposAcomodacao()));
		cbbTipoAcomodacaoForm.setBounds(124, 102, 307, 27);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(101, 233, 117, 29);
		btnSalvar.addActionListener(e -> {
			try {
				actionSalvar();
			} catch (AcomodacaoException | TipoAcomodacaoException e1) {
				e1.printStackTrace();
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(218, 233, 117, 29);
		btnCancelar.addActionListener(e -> actionCancelar());

		formPane.add(lblNumero);
		formPane.add(txtNumero);
		formPane.add(lblOcupMax);
		formPane.add(txtOcupMax);
		formPane.add(lblTipo);
		formPane.add(cbbTipoAcomodacaoForm);

		formPane.add(btnSalvar);
		formPane.add(btnCancelar);
	}

	private void initGerPane() {

		AcomodacaoController controle = MainController.getAcomodacaoController();

		modPane.setLayout(null);

		JLabel lblModEscolha = new JLabel("Escolha a Acomodação a alterar o estado:");
		lblModEscolha.setBounds(10, 23, 377, 16);

		cbbAcomodacaoMod = new JComboBox<AcomodacaoDto>(new Vector<AcomodacaoDto>(controle.getAcomodacoes()));
		cbbAcomodacaoMod.setBounds(80, 52, 307, 27);

		btnModSalvar = new JButton("Salvar");
		btnModSalvar.setBounds(101, 233, 117, 29);
		btnModSalvar.addActionListener(e -> {
			try {
				actionSalvarEstado();
			} catch (AcomodacaoException e1) {
				e1.printStackTrace();
			}
		});

		btnModCancelar = new JButton("Cancelar");
		btnModCancelar.setBounds(218, 233, 117, 29);
		btnModCancelar.addActionListener(e -> actionCancelar());

		radioLivre = new JRadioButton("Livre");
		radioLivre.setBounds(26, 102, 109, 23);

		radioManu = new JRadioButton("Manutenção");
		radioManu.setBounds(26, 129, 109, 23);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(radioManu);
		grupo.add(radioLivre);

		modPane.add(lblModEscolha);
		modPane.add(cbbAcomodacaoMod);
		modPane.add(radioManu);
		modPane.add(radioLivre);
		modPane.add(btnModSalvar);
		modPane.add(btnModCancelar);

	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void actionSalvar() throws AcomodacaoException, TipoAcomodacaoException {
		AcomodacaoController controle = MainController.getAcomodacaoController();

		int numero = Integer.parseInt(txtNumero.getText());
		int ocupacaoMaxima = Integer.parseInt(txtOcupMax.getText());

		TipoAcomodacaoDto TipoAcomodacao = (TipoAcomodacaoDto) cbbTipoAcomodacaoForm.getSelectedItem();

		try {
			controle.createAcomodacao(new AcomodacaoDto(numero, ocupacaoMaxima, TipoAcomodacao.getNome()));
		} catch (TipoAcomodacaoException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Tipo de Acomodação");
			return;

		} catch (AcomodacaoException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Acomodação");
			return;

		} catch (Exception e) {

			e.printStackTrace();

		}

		limparForm();
	}

	private void actionSalvarEstado() throws AcomodacaoException {

		AcomodacaoController controle = MainController.getAcomodacaoController();

		AcomodacaoDto acomodacao = (AcomodacaoDto) cbbAcomodacaoMod.getSelectedItem();

		try {

			if (radioLivre.isSelected()) {
				controle.setEEstadoOcupacao(acomodacao.getNumero(), EEstadoOcupacao.DISPONIVEL);
			} else {
				controle.setEEstadoOcupacao(acomodacao.getNumero(), EEstadoOcupacao.MANUTENCAO);
			}

		} catch (AcomodacaoException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Acomodação");
			return;

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void actionListar() {
		AcomodacaoController controller = MainController.getAcomodacaoController();

		TipoAcomodacaoDto nomeTipoAcomodacao = (TipoAcomodacaoDto) cbbTipoAcomodacaoList.getSelectedItem();

		textAreaList.setText(null);
		for (AcomodacaoDto obj : controller.getAcomodacoes()) {
			if (obj.getTipo() == nomeTipoAcomodacao.getNome()) {
				textAreaList.append(obj.toString() + "\n");
			}
		}
	}

	private void limparForm() {
		txtNumero.setText("");
		txtOcupMax.setText("");
	}
}

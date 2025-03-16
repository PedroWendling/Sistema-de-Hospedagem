package view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
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

import controllers.AcomodacaoController;
import controllers.MainController;
import dtos.TipoAcomodacaoDto;
import exceptions.TipoAcomodacaoException;

public class TipoAcomodacaoView extends JFrame {

	private static final long serialVersionUID = -5659880888694503154L;

	private JTabbedPane tabbedPane;

	private JButton btnSalvar;
	private JButton btnCancelar;

	private JPanel contentPane;
	private JPanel formPane;
	private JPanel listPane;
	private JPanel modPane;

	private JComboBox<TipoAcomodacaoDto> cbbTipoAcomodacaoList;

	private JTextArea textAreaList;

	private JTextField txtNome;
	private JTextField txtValorDiaria;
	private JTextField txtAdicionalAcompanhante;
	private JTextField txtModValorDiaria;
	private JTextField txtModAdicionalAcompanhante;

	private JButton btnModSalvar;

	private JButton btnModCancelar;

	public TipoAcomodacaoView() {
		initialize();
	}

	private void initialize() {

		setTitle("Menu de Tipos de Acomodação");

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

		initListPane();
		initFormPane();
		initModPane();

		tabbedPane.add("Listagem", listPane);
		tabbedPane.add("Criação", formPane);
		tabbedPane.add("Gerenciamento", modPane);

	}

	private void initListPane() {

		listPane.setLayout(null);

		JLabel lblListar = new JLabel("Informações dos Tipos de Acomodação:");
		lblListar.setBounds(12, 27, 275, 16);

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(343, 21, 109, 29);

		btnListar.addActionListener(e -> actionListar());

		textAreaList = new JTextArea();
		textAreaList.setBounds(12, 60, 440, 193);
		
		JScrollPane scrollAreaList = new JScrollPane(textAreaList);
		scrollAreaList.setLocation(12, 53);
		scrollAreaList.setSize(426, 186);
		scrollAreaList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollAreaList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listPane.add(lblListar);
		listPane.add(scrollAreaList);

		listPane.add(btnListar);

	}

	private void initFormPane() {

		formPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(17, 27, 61, 16);

		JLabel lblValorDiaria = new JLabel("Valor da Diária");
		lblValorDiaria.setBounds(17, 67, 117, 16);

		JLabel lblAdicionalAcompanhante = new JLabel("Adicional de Acompanhante");
		lblAdicionalAcompanhante.setBounds(17, 107, 170, 16);

		txtNome = new JTextField();
		txtNome.setBounds(233, 22, 148, 26);
		txtNome.setColumns(10);

		txtValorDiaria = new JTextField();
		txtValorDiaria.setBounds(233, 62, 148, 26);
		txtValorDiaria.setColumns(10);

		txtAdicionalAcompanhante = new JTextField();
		txtAdicionalAcompanhante.setBounds(233, 102, 148, 26);
		txtAdicionalAcompanhante.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(101, 233, 117, 29);

		btnSalvar.addActionListener(e -> {
			try {
				actionSalvar();
			} catch (TipoAcomodacaoException e1) {
				e1.printStackTrace();
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(218, 233, 117, 29);

		btnCancelar.addActionListener(e -> actionCancelar());

		formPane.add(lblNome);
		formPane.add(txtNome);

		formPane.add(lblValorDiaria);
		formPane.add(txtValorDiaria);

		formPane.add(lblAdicionalAcompanhante);
		formPane.add(txtAdicionalAcompanhante);

		formPane.add(btnSalvar);
		formPane.add(btnCancelar);

	}

	private void initModPane() {

		AcomodacaoController controle = MainController.getAcomodacaoController();

		modPane.setLayout(null);

		JLabel lblModSelect = new JLabel("Selecione o Tipo de Acomodação para realizar alterações:");
		lblModSelect.setBounds(17, 27, 368, 16);

		cbbTipoAcomodacaoList = new JComboBox<TipoAcomodacaoDto>(
				new Vector<TipoAcomodacaoDto>(controle.getTiposAcomodacao()));
		cbbTipoAcomodacaoList.setBounds(17, 53, 330, 27);

		JLabel lblModValorDiaria = new JLabel("Novo Valor da Diária");
		lblModValorDiaria.setBounds(17, 113, 201, 16);

		JLabel lblModAdicionalAcompanhante = new JLabel("Novo Adicional de Acompanhante");
		lblModAdicionalAcompanhante.setBounds(17, 140, 247, 16);

		txtModValorDiaria = new JTextField();
		txtModValorDiaria.setBounds(285, 108, 117, 26);
		txtModValorDiaria.setColumns(10);

		txtModAdicionalAcompanhante = new JTextField();
		txtModAdicionalAcompanhante.setBounds(285, 135, 117, 26);
		txtModAdicionalAcompanhante.setColumns(10);

		btnModSalvar = new JButton("Salvar");
		btnModSalvar.setBounds(101, 233, 117, 29);

		btnModSalvar.addActionListener(e -> {
			actionSalvarAlteracao();
		});

		btnModCancelar = new JButton("Cancelar");
		btnModCancelar.setBounds(218, 233, 117, 29);

		btnModCancelar.addActionListener(e -> actionCancelar());

		modPane.add(lblModSelect);
		modPane.add(cbbTipoAcomodacaoList);

		modPane.add(lblModValorDiaria);
		modPane.add(txtModValorDiaria);

		modPane.add(lblModAdicionalAcompanhante);
		modPane.add(txtModAdicionalAcompanhante);

		modPane.add(btnModSalvar);
		modPane.add(btnModCancelar);

	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void actionSalvar() throws TipoAcomodacaoException {

		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();

		String nome = txtNome.getText();
		double valorDiaria = Double.parseDouble(txtValorDiaria.getText());
		double adicionalAcompanhante = Double.parseDouble(txtAdicionalAcompanhante.getText());

		try {

			acomodacaoController.createTipoAcomodacao(new TipoAcomodacaoDto(nome, valorDiaria, adicionalAcompanhante));

		} catch (TipoAcomodacaoException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Tipo de Acomodação");
			return;

		} catch (Exception e) {

			e.printStackTrace();

		}

		limparForm();
	}

	private void actionSalvarAlteracao() {

		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();

		TipoAcomodacaoDto TipoAcomodacao = (TipoAcomodacaoDto) cbbTipoAcomodacaoList.getSelectedItem();

		if (!(txtModValorDiaria.getText().isEmpty())) {
			double valorDiaria = Double.parseDouble(txtModValorDiaria.getText());

			try {
				acomodacaoController.setTarifaDiaria(TipoAcomodacao.getNome(), valorDiaria);
			} catch (TipoAcomodacaoException e) {
				JOptionPane.showMessageDialog(this, "Erro ao alterar o Tipo de Acomodação!");
				return;
			}

		}

		if (!(txtModAdicionalAcompanhante.getText().isEmpty())) {
			double adicionalAcompanhante = Double.parseDouble(txtModAdicionalAcompanhante.getText());

			try {
				acomodacaoController.setAdicionalAcompanhante(TipoAcomodacao.getNome(), adicionalAcompanhante);
			} catch (TipoAcomodacaoException e) {
				JOptionPane.showMessageDialog(this, "Erro ao alterar o Tipo de Acomodação!");
				return;
			}
		}

		txtModValorDiaria.setText("");
		txtModAdicionalAcompanhante.setText("");

	}

	private void actionListar() {

		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();

		textAreaList.setText(null);

		for (TipoAcomodacaoDto nomeTipoAcomodacao : acomodacaoController.getTiposAcomodacao()) {
			textAreaList.append(nomeTipoAcomodacao.toString());
		}
	}

	private void limparForm() {

		txtNome.setText("");
		txtValorDiaria.setText("");
		txtAdicionalAcompanhante.setText("");
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.HospedagemController;
import controllers.MainController;
import dtos.HospedagemDto;
import enums.ETipoPagamento;
import exceptions.HospedagemException;
import exceptions.HospedeException;
import exceptions.ItemException;

public class HospedagemView extends JFrame {

	private static final long serialVersionUID = 1751405108975532000L;

	private JPanel contentPane;

	private JTabbedPane tabbedPane;

	private JPanel listPane;

	private JPanel formPane;

	private JPanel contentListPane;

	private JTabbedPane tabbedListPane;

	private JPanel listGeral;

	private JPanel listEsp;

	private JTextArea textAreaListGeral;

	private JTextArea textAreaListEsp;

	private JTextField txtListEsp;

	private JTextField txtFormCpf;

	private JTextField txtFormCod;

	private JPanel checkoutPane;

	private JTextField txtCheckoutID;

	private JPanel pagamentoPane;

	private JTextField txtPagamentoID;

	private JTextField txtPagamentoValor;

	private JRadioButton radioPIX;

	private JRadioButton radioCredito;

	private JRadioButton radioDebito;

	private JPanel acompPane;

	private JTextField txtAcompCpf1;

	private JTextField txtAcompCpf2;

	private JTextField txtAcompCpf3;

	private JTextField txtAcompID;

	private JPanel itemPane;

	private JTextField txtItemID;

	private JTextField txtItemAdd;

	private JTextField txtItemRemove;

	private JTextField txtItemQtd;

	public HospedagemView() {
		initialize();
	}

	private void initialize() {

		setTitle("Menu de Hospedagem");

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
		checkoutPane = new JPanel();
		pagamentoPane = new JPanel();
		acompPane = new JPanel();
		itemPane = new JPanel();

		initListPane();
		initFormPane();
		initCheckoutPane();
		initPagamentoPane();
		initAcompPane();
		initItemPane();

		tabbedPane.add("Listagem", listPane);
		tabbedPane.add("Criação", formPane);
		tabbedPane.add("Checkout", checkoutPane);
		tabbedPane.add("Pagamento", pagamentoPane);
		tabbedPane.add("Acompanhantes", acompPane);
		tabbedPane.add("Itens", itemPane);

	}

	private void initListPane() {

		listPane.setLayout(new BorderLayout());

		contentListPane = new JPanel();
		contentListPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentListPane.setLayout(new BorderLayout(0, 0));

		tabbedListPane = new JTabbedPane(JTabbedPane.TOP);

		listGeral = new JPanel();
		listEsp = new JPanel();

		tabbedListPane.add("Geral", listGeral);
		tabbedListPane.add("Específica", listEsp);

		contentListPane.add(tabbedListPane, BorderLayout.CENTER);

		initListGeral();
		initListEsp();

		listPane.add(contentListPane);

	}

	private void initListGeral() {

		listGeral.setLayout(null);

		JLabel lblListGeral = new JLabel("Listagem Geral de Hospedagens:");
		lblListGeral.setBounds(12, 26, 262, 16);

		JButton btnListGeral = new JButton("Listar");
		btnListGeral.setBounds(327, 20, 109, 29);

		btnListGeral.addActionListener(e -> actionListarGeral());

		textAreaListGeral = new JTextArea();
		textAreaListGeral.setBounds(12, 60, 440, 193);

		JScrollPane scrollPaneListGeral = new JScrollPane(textAreaListGeral);
		scrollPaneListGeral.setLocation(12, 53);
		scrollPaneListGeral.setSize(426, 186);
		scrollPaneListGeral.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneListGeral.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listGeral.add(lblListGeral);
		listGeral.add(scrollPaneListGeral);
		listGeral.add(btnListGeral);
	}

	private void initListEsp() {

		listEsp.setLayout(null);

		JLabel lblListEsp = new JLabel("Digite o Identificador da Hospedagem:");
		lblListEsp.setBounds(10, 26, 312, 16);

		txtListEsp = new JTextField();
		txtListEsp.setBounds(20, 50, 257, 26);
		txtListEsp.setColumns(10);

		JButton btnListEsp = new JButton("Listar");
		btnListEsp.setBounds(327, 20, 109, 29);

		btnListEsp.addActionListener(e -> actionListarEspecifica());

		textAreaListEsp = new JTextArea();
		textAreaListEsp.setBounds(12, 60, 440, 193);

		JScrollPane scrollPaneListEsp = new JScrollPane(textAreaListEsp);
		scrollPaneListEsp.setLocation(12, 89);
		scrollPaneListEsp.setSize(426, 150);
		scrollPaneListEsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneListEsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listEsp.add(txtListEsp);
		listEsp.add(lblListEsp);
		listEsp.add(scrollPaneListEsp);
		listEsp.add(btnListEsp);

	}

	private void actionListarGeral() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		textAreaListGeral.setText(null);

		for (HospedagemDto hospedagem : hospedagemController.getHospedagens()) {
			textAreaListGeral.append(hospedagem.toString());
		}
	}

	private void actionListarEspecifica() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		textAreaListEsp.setText(null);

		String id = txtListEsp.getText();

		try {
			textAreaListEsp.append(hospedagemController.listar(id).toString());
		} catch (HospedagemException e) {

			JOptionPane.showMessageDialog(this, "Erro ao recuperar Hospedagem");
			return;
		} catch (Exception e) {

			e.printStackTrace();

		}

		txtListEsp.setText(null);
	}

	private void initFormPane() {

		formPane.setLayout(null);

		JLabel lblFormCpf = new JLabel("CPF do Hospede:");
		lblFormCpf.setBounds(17, 27, 147, 16);

		JLabel lblFormCod = new JLabel("Código da Acomodação:");
		lblFormCod.setBounds(17, 67, 163, 16);

		txtFormCpf = new JTextField();
		txtFormCpf.setBounds(203, 22, 132, 26);
		txtFormCpf.setColumns(10);

		txtFormCod = new JTextField();
		txtFormCod.setBounds(203, 62, 132, 26);
		txtFormCod.setColumns(10);

		JButton btnFormSalvar = new JButton("Salvar");
		btnFormSalvar.setBounds(101, 233, 117, 29);

		btnFormSalvar.addActionListener(e -> actionCriarHospedagem());

		JButton btnFormCancelar = new JButton("Cancelar");
		btnFormCancelar.setBounds(218, 233, 117, 29);

		btnFormCancelar.addActionListener(e -> actionCancelar());

		formPane.add(lblFormCpf);
		formPane.add(txtFormCpf);

		formPane.add(lblFormCod);
		formPane.add(txtFormCod);

		formPane.add(btnFormSalvar);
		formPane.add(btnFormCancelar);

	}

	private void actionCriarHospedagem() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		String cpf = txtFormCpf.getText();
		int codigo = Integer.parseInt(txtFormCod.getText());

		try {

			hospedagemController.createHospedagem(new HospedagemDto(cpf, codigo));

		} catch (HospedagemException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Hospedagem");
			return;

		} catch (Exception e) {

			e.printStackTrace();

		}

		txtFormCpf.setText(null);
		txtFormCod.setText(null);
	}

	private void initCheckoutPane() {

		checkoutPane.setLayout(null);

		JLabel lblCheckoutID = new JLabel("Digite o ID da Hospedagem:");
		lblCheckoutID.setBounds(17, 27, 244, 16);

		txtCheckoutID = new JTextField();
		txtCheckoutID.setBounds(257, 22, 132, 26);
		txtCheckoutID.setColumns(10);

		JButton btnCheckoutSalvar = new JButton("Salvar");
		btnCheckoutSalvar.setBounds(101, 233, 117, 29);

		btnCheckoutSalvar.addActionListener(e -> actionFazerCheckout());

		JButton btnCheckoutCancelar = new JButton("Cancelar");
		btnCheckoutCancelar.setBounds(218, 233, 117, 29);

		btnCheckoutCancelar.addActionListener(e -> actionCancelar());

		checkoutPane.add(lblCheckoutID);
		checkoutPane.add(txtCheckoutID);

		checkoutPane.add(btnCheckoutSalvar);
		checkoutPane.add(btnCheckoutCancelar);

	}

	private void actionFazerCheckout() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		String id = txtCheckoutID.getText();

		try {
			hospedagemController.fazerCheckout(id);
		} catch (HospedagemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar Hospedagem");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtCheckoutID.setText(null);
	}

	private void initPagamentoPane() {

		pagamentoPane.setLayout(null);

		JLabel lblPagamentoID = new JLabel("Digite o ID da Hospedagem:");
		lblPagamentoID.setBounds(27, 27, 214, 16);

		txtPagamentoID = new JTextField();
		txtPagamentoID.setBounds(237, 22, 132, 26);
		txtPagamentoID.setColumns(10);

		JLabel lblPagamentoValor = new JLabel("Digite o valor:");
		lblPagamentoValor.setBounds(27, 64, 147, 16);

		txtPagamentoValor = new JTextField();
		txtPagamentoValor.setBounds(237, 54, 132, 26);
		txtPagamentoValor.setColumns(10);

		JLabel lblPagamentoTipo = new JLabel("Escolha a forma de Pagamento:");
		lblPagamentoTipo.setBounds(27, 106, 290, 16);

		radioPIX = new JRadioButton("PIX");
		radioPIX.setBounds(36, 129, 109, 23);

		radioCredito = new JRadioButton("Crédito");
		radioCredito.setBounds(333, 129, 109, 23);

		radioDebito = new JRadioButton("Débito");
		radioDebito.setBounds(185, 129, 109, 23);

		ButtonGroup grupo = new ButtonGroup();

		grupo.add(radioPIX);
		grupo.add(radioCredito);
		grupo.add(radioDebito);

		JButton btnPagamentoSalvar = new JButton("Salvar");
		btnPagamentoSalvar.setBounds(101, 233, 117, 29);

		btnPagamentoSalvar.addActionListener(e -> actionFazerPagamento());

		JButton btnPagamentoCancelar = new JButton("Cancelar");
		btnPagamentoCancelar.setBounds(218, 233, 117, 29);

		btnPagamentoCancelar.addActionListener(e -> actionCancelar());

		pagamentoPane.add(lblPagamentoID);
		pagamentoPane.add(txtPagamentoID);
		pagamentoPane.add(lblPagamentoValor);
		pagamentoPane.add(txtPagamentoValor);
		pagamentoPane.add(lblPagamentoTipo);
		pagamentoPane.add(radioPIX);
		pagamentoPane.add(radioCredito);
		pagamentoPane.add(radioDebito);
		pagamentoPane.add(btnPagamentoSalvar);
		pagamentoPane.add(btnPagamentoCancelar);

	}

	private void actionFazerPagamento() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		String id = txtPagamentoID.getText();
		int valor = Integer.parseInt(txtPagamentoValor.getText());

		try {

			if (radioPIX.isSelected()) {
				hospedagemController.fazerPagamento(id, ETipoPagamento.PIX, valor);
			} else if (radioDebito.isSelected()) {
				hospedagemController.fazerPagamento(id, ETipoPagamento.DEBITO, valor);
			} else if (radioCredito.isSelected()) {
				hospedagemController.fazerPagamento(id, ETipoPagamento.CREDITO, valor);
			}

		} catch (HospedagemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar Hospedagem");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtPagamentoID.setText(null);
		txtPagamentoValor.setText(null);
	}

	private void initAcompPane() {

		acompPane.setLayout(null);

		JLabel lblAcompID = new JLabel("Digite o ID da Hospedagem:");
		lblAcompID.setBounds(17, 25, 214, 16);

		txtAcompID = new JTextField();
		txtAcompID.setBounds(235, 20, 142, 26);
		txtAcompID.setColumns(10);

		JLabel lblAcompCpf1 = new JLabel("Digite o CPF da primeira pessoa:");
		lblAcompCpf1.setBounds(17, 62, 208, 16);

		JLabel lblAcompCpf2 = new JLabel("Digite o CPF da segunda pessoa:");
		lblAcompCpf2.setBounds(17, 99, 208, 16);

		JLabel lblAcompCpf3 = new JLabel("Digite o CPF da terceira pessoa:");
		lblAcompCpf3.setBounds(17, 139, 221, 16);

		txtAcompCpf1 = new JTextField();
		txtAcompCpf1.setBounds(235, 57, 142, 26);
		txtAcompCpf1.setColumns(10);

		txtAcompCpf2 = new JTextField();
		txtAcompCpf2.setBounds(235, 94, 142, 26);
		txtAcompCpf2.setColumns(10);

		txtAcompCpf3 = new JTextField();
		txtAcompCpf3.setBounds(235, 134, 142, 26);
		txtAcompCpf3.setColumns(10);

		JButton btnAcompSalvar = new JButton("Salvar");
		btnAcompSalvar.setBounds(101, 233, 117, 29);

		btnAcompSalvar.addActionListener(e -> actionAdicionarAcompanhante());

		JButton btnAcompCancelar = new JButton("Cancelar");
		btnAcompCancelar.setBounds(218, 233, 117, 29);

		btnAcompCancelar.addActionListener(e -> actionCancelar());

		acompPane.add(lblAcompID);
		acompPane.add(txtAcompID);

		acompPane.add(lblAcompCpf1);
		acompPane.add(txtAcompCpf1);

		acompPane.add(lblAcompCpf2);
		acompPane.add(txtAcompCpf2);

		acompPane.add(lblAcompCpf3);
		acompPane.add(txtAcompCpf3);

		acompPane.add(btnAcompSalvar);
		acompPane.add(btnAcompCancelar);

	}

	private void actionAdicionarAcompanhante() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		String id = txtAcompID.getText();

		String cpf1 = txtAcompCpf1.getText();
		String cpf2 = txtAcompCpf2.getText();
		String cpf3 = txtAcompCpf3.getText();

		List<String> cpfs = new ArrayList<>();

		if (!(cpf1.isEmpty())) {
			cpfs.add(cpf1);
		}
		if (!(cpf2.isEmpty())) {
			cpfs.add(cpf2);
		}
		if (!(cpf3.isEmpty())) {
			cpfs.add(cpf3);
		}

		try {
			hospedagemController.addAcompanhantes(id, cpfs);
		} catch (HospedeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao obter Hospede");
			return;
		} catch (HospedagemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar Hospedagem");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtAcompID.setText(null);
		txtAcompCpf1.setText(null);
		txtAcompCpf2.setText(null);
		txtAcompCpf3.setText(null);
	}

	private void initItemPane() {

		itemPane.setLayout(null);

		JLabel lblItemID = new JLabel("Digite o ID da Hospedagem:");
		lblItemID.setBounds(17, 24, 255, 16);

		txtItemID = new JTextField();
		txtItemID.setBounds(289, 19, 142, 26);
		txtItemID.setColumns(10);

		JLabel lblItemAdd = new JLabel("Digite o Código do Item a Adicionar:");
		lblItemAdd.setBounds(17, 76, 249, 16);

		JLabel lblItemRemove = new JLabel("Digite o Index do Item a Remover:");
		lblItemRemove.setBounds(17, 206, 262, 16);

		txtItemAdd = new JTextField();
		txtItemAdd.setBounds(289, 71, 142, 26);
		txtItemAdd.setColumns(10);

		JLabel lblItemQtd = new JLabel("Digite a Quantidade:");
		lblItemQtd.setBounds(17, 125, 249, 16);

		txtItemQtd = new JTextField();
		txtItemQtd.setBounds(289, 120, 142, 26);
		txtItemQtd.setColumns(10);

		txtItemRemove = new JTextField();
		txtItemRemove.setBounds(289, 201, 142, 26);
		txtItemRemove.setColumns(10);

		JButton btnItemAdd = new JButton("Adicionar");
		btnItemAdd.setBounds(165, 166, 117, 29);

		btnItemAdd.addActionListener(e -> actionAdicionarItem());

		JButton btnItemRemove = new JButton("Remover");
		btnItemRemove.setBounds(165, 243, 117, 29);

		btnItemRemove.addActionListener(e -> actionRemoverItem());

		itemPane.add(lblItemRemove);
		itemPane.add(txtItemRemove);

		itemPane.add(lblItemQtd);
		itemPane.add(txtItemQtd);

		itemPane.add(lblItemAdd);
		itemPane.add(txtItemAdd);

		itemPane.add(lblItemID);
		itemPane.add(txtItemID);

		itemPane.add(btnItemAdd);
		itemPane.add(btnItemRemove);
	}

	private void actionAdicionarItem() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		String id = txtItemID.getText();
		int codigo = Integer.parseInt(txtItemAdd.getText());
		int qtde = Integer.parseInt(txtItemQtd.getText());

		try {
			hospedagemController.addItem(id, codigo, qtde);
		} catch (ItemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar Item");
			return;
		} catch (HospedagemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar Hospedagem");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtItemID.setText(null);
		txtItemAdd.setText(null);
		txtItemQtd.setText(null);
	}

	private void actionRemoverItem() {

		HospedagemController hospedagemController = MainController.getHospedagemController();

		String id = txtItemID.getText();
		int index = Integer.parseInt(txtItemRemove.getText());

		try {
			hospedagemController.removeItem(id, index);
		} catch (HospedagemException e) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar Hospedagem");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtItemID.setText(null);
		txtItemRemove.setText(null);
	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}

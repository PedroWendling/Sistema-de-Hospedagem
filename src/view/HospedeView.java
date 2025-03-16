package view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.HospedeController;
import controllers.MainController;
import dtos.HospedeDto;
import exceptions.HospedeException;

public class HospedeView extends JFrame {

	private static final long serialVersionUID = -1014540195974681524L;

	private JTabbedPane tabbedPane;

	private JButton btnSalvar;
	private JButton btnCancelar;

	private JPanel contentPane;
	private JPanel formPane;
	private JPanel listPane;
	private JPanel modPane;

	private JTextArea textAreaList;

	private JTextField txtNome;

	private JTextField txtCpf;

	private JTextField txtTelefone;

	private JTextField txtEmail;

	private JTextField txtModCpf;

	private JTextField txtModNome;

	private JTextField txtModTelefone;

	private JTextField txtModEmail;

	public HospedeView() {
		initialize();
	}

	private void initialize() {

		setTitle("Menu de Hospedes");

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
		tabbedPane.add("Alterações", modPane);

	}

	private void initListPane() {

		listPane.setLayout(null);

		JLabel lblListar = new JLabel("Informações dos Hospedes:");
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

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(17, 25, 117, 16);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(17, 67, 143, 16);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(17, 107, 170, 16);

		JLabel lblEmail = new JLabel("Email (Opcional):");
		lblEmail.setBounds(17, 149, 170, 16);

		txtCpf = new JTextField();
		txtCpf.setBounds(212, 20, 214, 26);
		txtCpf.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(212, 62, 214, 26);
		txtNome.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(212, 102, 214, 26);
		txtTelefone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(212, 144, 214, 26);
		txtEmail.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(101, 233, 117, 29);

		btnSalvar.addActionListener(e -> actionSalvar());

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(218, 233, 117, 29);

		btnCancelar.addActionListener(e -> actionCancelar());

		formPane.add(lblCpf);
		formPane.add(txtCpf);

		formPane.add(lblNome);
		formPane.add(txtNome);

		formPane.add(lblTelefone);
		formPane.add(txtTelefone);

		formPane.add(lblEmail);
		formPane.add(txtEmail);

		formPane.add(btnSalvar);
		formPane.add(btnCancelar);

	}

	private void initModPane() {

		modPane.setLayout(null);

		JLabel lblModCpf = new JLabel("CPF da pessoa a realizar alterações:");
		lblModCpf.setBounds(17, 27, 368, 16);

		JLabel lblModNome = new JLabel("Nome Atualizado:");
		lblModNome.setBounds(17, 67, 201, 16);

		JLabel lblModTelefone = new JLabel("Telefone Atualizado:");
		lblModTelefone.setBounds(17, 107, 247, 16);

		JLabel lblModEmail = new JLabel("Email Atualizado:");
		lblModEmail.setBounds(17, 149, 247, 16);

		txtModCpf = new JTextField();
		txtModCpf.setBounds(245, 22, 187, 26);
		txtModCpf.setColumns(10);

		txtModNome = new JTextField();
		txtModNome.setBounds(245, 62, 187, 26);
		txtModNome.setColumns(10);

		txtModTelefone = new JTextField();
		txtModTelefone.setBounds(245, 102, 187, 26);
		txtModTelefone.setColumns(10);

		txtModEmail = new JTextField();
		txtModEmail.setBounds(245, 144, 187, 26);
		txtModEmail.setColumns(10);

		JButton btnModSalvar = new JButton("Salvar");
		btnModSalvar.setBounds(101, 233, 117, 29);

		btnModSalvar.addActionListener(e -> actionSalvarAlteracao());

		JButton btnModCancelar = new JButton("Cancelar");
		btnModCancelar.setBounds(218, 233, 117, 29);

		btnModCancelar.addActionListener(e -> actionCancelar());

		modPane.add(lblModCpf);
		modPane.add(txtModCpf);

		modPane.add(lblModNome);
		modPane.add(txtModNome);

		modPane.add(lblModTelefone);
		modPane.add(txtModTelefone);

		modPane.add(lblModEmail);
		modPane.add(txtModEmail);

		modPane.add(btnModSalvar);
		modPane.add(btnModCancelar);

	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void actionSalvar() {

		HospedeController hospedeController = MainController.getHospedeController();
		
		String cpf = txtCpf.getText();
		String nome = txtNome.getText();
		if ((txtTelefone.getText().isEmpty())) {
			return;
		}
		long telefone = Long.parseLong(txtTelefone.getText());
		String email = txtEmail.getText();

		try {
			if (!(email.isEmpty())) {
				hospedeController.createHospede(new HospedeDto(cpf, nome, telefone, email));
			} else {
				hospedeController.createHospede(new HospedeDto(cpf, nome, telefone));
			}

		} catch (HospedeException e) {

			JOptionPane.showMessageDialog(this, "Erro ao criar Hospede" + e.getMessage());
			return;

		} catch (Exception e) {

			e.printStackTrace();

		}

		txtCpf.setText(null);
		txtNome.setText(null);
		txtTelefone.setText(null);
		txtEmail.setText(null);
	}

	private void actionSalvarAlteracao() {

		HospedeController hospedeController = MainController.getHospedeController();

		String cpf = txtModCpf.getText();
		String nome = txtModNome.getText();
		String email = txtModEmail.getText();

		try {
			if (!(nome.isEmpty())) {
				hospedeController.setNome(cpf, nome);
			}
			if (!(txtModTelefone.getText().isEmpty())) {
				long telefone = Long.parseLong(txtModTelefone.getText());
				hospedeController.setTelefone(cpf, telefone);
			}
			if (!(email.isEmpty())) {
				hospedeController.setEmail(cpf, email);
			}
		} catch (HospedeException e) {
			JOptionPane.showMessageDialog(this, "Erro ao alterar Hospede");
			return;

		} catch (Exception e) {

			e.printStackTrace();

		}

		txtModCpf.setText("");
		txtModNome.setText("");
		txtModTelefone.setText("");
		txtModEmail.setText("");
	}

	private void actionListar() {

		HospedeController hospedeController = MainController.getHospedeController();

		textAreaList.setText(null);

		for (HospedeDto hospede : hospedeController.getHospedes()) {
			textAreaList.append(hospede.toString());
		}
	}

}

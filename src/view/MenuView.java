package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 901058143271126100L;

	private JPanel contentPane;

	private JButton btnHospedagemView;
	private JButton btnHospedeView;
	private JButton btnCatalogoView;
	private JButton btnTipoAcomodacaoView;
	private JButton btnAcomodacaoView;

	public MenuView() {
		initialize();
	}

	private void initialize() {

		setTitle("App Hospedagem");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		this.setContentPane(contentPane);

		btnHospedagemView = new JButton("Menu de Hospedagem");
		btnHospedagemView.setBounds(6, 18, 228, 29);

		btnHospedagemView.addActionListener(e -> actionHospedagemView());

		btnHospedeView = new JButton("Menu de Hospede");
		btnHospedeView.setBounds(6, 52, 228, 29);

		btnHospedeView.addActionListener(e -> actionHospedeView());

		btnCatalogoView = new JButton("Menu de Catálogo");
		btnCatalogoView.setBounds(6, 86, 228, 29);

		btnCatalogoView.addActionListener(e -> actionCatalogoView());

		btnAcomodacaoView = new JButton("Menu de Acomodacao");
		btnAcomodacaoView.setBounds(6, 120, 228, 29);

		btnAcomodacaoView.addActionListener(e -> actionAcomodacaoView());

		btnTipoAcomodacaoView = new JButton("Menu de Tipo Acomodacao");
		btnTipoAcomodacaoView.setBounds(6, 154, 228, 29);

		btnTipoAcomodacaoView.addActionListener(e -> actionTipoAcomodacaoView());

		contentPane.add(btnHospedagemView);
		contentPane.add(btnHospedeView);
		contentPane.add(btnCatalogoView);
		contentPane.add(btnAcomodacaoView);
		contentPane.add(btnTipoAcomodacaoView);

	}

	private void actionHospedagemView() {
		HospedagemView hospedagemView = new HospedagemView();
		hospedagemView.setVisible(true);
	}

	private void actionHospedeView() {
		HospedeView hospedeView = new HospedeView();
		hospedeView.setVisible(true);
	}

	private void actionCatalogoView() {
		CatalogoView catalogoView = new CatalogoView();
		catalogoView.setVisible(true);
	}

	private void actionAcomodacaoView() {

		AcomodacaoView acomodacaoView = new AcomodacaoView();
		acomodacaoView.setVisible(true);
	}

	private void actionTipoAcomodacaoView() {

		TipoAcomodacaoView tipoAcomodacaoView = new TipoAcomodacaoView();
		tipoAcomodacaoView.setVisible(true);
	}
}

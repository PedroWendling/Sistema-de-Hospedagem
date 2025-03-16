package controllers;

import java.io.Serializable;

import persistence.Serializer;

public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private AcomodacaoController acomodacaoController;
	private HospedagemController hospedagemController;
	private HospedeController hospedeController;
	private CatalogoController catalogoController;

	private MainController() {

		acomodacaoController = new AcomodacaoController();
		hospedagemController = new HospedagemController();
		hospedeController = new HospedeController();
		catalogoController = new CatalogoController();
	}

	public static MainController getInstance() {
		return instance;
	}

	public static AcomodacaoController getAcomodacaoController() {
		return instance.acomodacaoController;
	}

	public static HospedagemController getHospedagemController() {
		return instance.hospedagemController;
	}

	public static HospedeController getHospedeController() {
		return instance.hospedeController;
	}

	public static CatalogoController getCatalogoController() {
		return instance.catalogoController;
	}

	public static void load() {

		instance = Serializer.readFile();

		if (instance == null) {
			instance = new MainController();
		}
	}

	public static void save() {
		Serializer.writeFile(instance);
	}
}

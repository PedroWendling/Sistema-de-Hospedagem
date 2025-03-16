package exceptions;

public class HospedeException extends Exception {

	private static final long serialVersionUID = 6929996351685834665L;

	public HospedeException(String msg) {
		super("Exceção de Hospede: " + msg);
	}

}

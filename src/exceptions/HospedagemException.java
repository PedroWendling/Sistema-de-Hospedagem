package exceptions;

public class HospedagemException extends Exception {

	private static final long serialVersionUID = 6893108237946904566L;

	public HospedagemException(String msg) {
		super("Exceção de Hospedagem: " + msg);
	}
	
}

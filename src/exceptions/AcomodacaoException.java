package exceptions;

public class AcomodacaoException extends Exception {

	private static final long serialVersionUID = -5916170725425242093L;

	public AcomodacaoException(String msg) {
		super("Exceção de Acomocacao: " + msg);
	}
	
}

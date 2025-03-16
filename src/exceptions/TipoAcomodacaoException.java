package exceptions;

public class TipoAcomodacaoException extends Exception {

	private static final long serialVersionUID = -1639135447939333156L;

	public TipoAcomodacaoException(String msg) {
		super("Exceção de Tipo de Acomocacao: " + msg);
	}
	
}

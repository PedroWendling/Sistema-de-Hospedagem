package exceptions;

public class CategoriaException extends Exception{

	private static final long serialVersionUID = -2259206790460952284L;
	
	public CategoriaException(String msg) {
		super("Exceção de Categoria: " + msg);
	}

}

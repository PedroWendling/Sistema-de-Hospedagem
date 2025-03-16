package exceptions;

public class ItemException extends Exception{

	private static final long serialVersionUID = -2528467059906045576L;

	public ItemException(String msg) {
		super("Exceção de Item: " + msg);
	}
}

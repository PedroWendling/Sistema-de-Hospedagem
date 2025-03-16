package util;

public class CpfValidate {

	public static boolean validate(String cpf) {

		if (cpf.length() != 11) {
			return false;
		}

		cpf = cpf.replaceAll("[^0-9]", "");

		if (cpf.length() != 11)
			return false;

		if (cpf.matches("(\\d)\\1{10}"))
			return false;

		return verificarDigitos(cpf);
	}

	private static boolean verificarDigitos(String cpf) {
		int soma = 0, peso = 10;

		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * peso--;
		}

		int primeiroDigito = (soma * 10) % 11;

		if (primeiroDigito == 10) {
			primeiroDigito = 0;
		}

		if (primeiroDigito != (cpf.charAt(9) - '0')) {
			return false;
		}

		soma = 0;
		peso = 11;

		for (int i = 0; i < 10; i++) {
			soma += (cpf.charAt(i) - '0') * peso--;
		}

		int segundoDigito = (soma * 10) % 11;

		if (segundoDigito == 10) {
			segundoDigito = 0;
		}

		return segundoDigito == (cpf.charAt(10) - '0');
	}

}

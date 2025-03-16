package util;

import java.util.Calendar;
import java.util.Date;

public abstract class UtilDate {

	public static int calculoDeDias(Date dataInicio, Date dataTermino, int inicioCheckin, int limiteCheckout) {

		if (dataTermino == null) {
			dataTermino = new Date();
		}

		long tempoLong = dataTermino.getTime() - dataInicio.getTime();
		tempoLong = (long) (tempoLong / 8.64e+7);

		int tempoInt = (int) (tempoLong) + 1;

		Calendar calCheckin = Calendar.getInstance();
		Calendar calDataAtual = Calendar.getInstance();

		calCheckin.setTime(dataInicio);
		calDataAtual.setTime(dataTermino);

		long horaCheckin = calCheckin.get(Calendar.HOUR_OF_DAY);
		long horaDataAtual = calDataAtual.get(Calendar.HOUR_OF_DAY);

		long diaCheckin = calCheckin.get(Calendar.DAY_OF_MONTH);
		long diaDataAtual = calDataAtual.get(Calendar.DAY_OF_MONTH);

		if ((horaDataAtual >= limiteCheckout) && (diaDataAtual - diaCheckin != 0)) {
			tempoInt++;
		}

		if ((horaCheckin < inicioCheckin)) {
			tempoInt++;
		}

		return tempoInt;

	}

}

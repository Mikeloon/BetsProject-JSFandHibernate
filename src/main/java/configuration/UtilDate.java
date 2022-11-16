package configuration;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UtilDate {

	public static Date trim(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	public static Date newDate(int year,int month,int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		calendar.set(year, month, day,0,0,0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println("newDate: "+calendar.getTime());
		return calendar.getTime();
	}

	public static Date firstDayMonth(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}


	public static Date lastDayMonth(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		//int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();

	}

	public static Date nuevaFechaHMS(int ano,int mes,int dia, int hora, int minuto, int segundo) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		calendar.set(ano, mes, dia, hora, minuto, segundo);
		calendar.set(Calendar.MILLISECOND, 0);
		//System.out.println("newDate: "+calendar.getTime());
		return calendar.getTime();
	}

	public static Date nuevaFecha(int ano,int mes,int dia) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		calendar.set(ano, mes, dia, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//System.out.println("newDate: "+calendar.getTime());
		return calendar.getTime();
	}

	public static Date currentDate() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		return calendar.getTime();
	}

	public static Date fechaMayorEdad() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("CET"));
		
		int ano = calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.YEAR, (ano - 18));
		return calendar.getTime();
		
	}

}
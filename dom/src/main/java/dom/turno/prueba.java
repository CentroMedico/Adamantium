package dom.turno;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class prueba {

	public static void main(String[] args) 
	{
		Date fecha=new Date();
		//System.out.println("Hoy es: "+fecha);
		
		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");
		fecha=SumarFecha(fecha, 0);
		
		System.out.println("Fecha a 7 dias: "+ fecha);


		
	
		
		//System.out.println(	fecha.getHourOfDay()+":"+fecha.getMinuteOfHour());
		
		///
	}
		public static Date SumarFecha(Date fecha,int dias)
		{
					
			      Calendar calendar = Calendar.getInstance();
		
			      calendar.setTime(fecha); // Configuramos la fecha que se recibe
		
			      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
				
			      
			      return calendar.getTime();
			
			 
			
			
			
		
		
		}
}

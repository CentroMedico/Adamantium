package dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class IndicacionesDataSource implements JRDataSource
{
		private List<ReporteIndicaciones> listaIndicaciones = new ArrayList<ReporteIndicaciones>();
		private int indiceIndicacionesActual = -1;
		public Object getFieldValue(JRField jrf) throws JRException {
			Object valor = null;
			if ("paciente".equals(jrf.getName())) {
				valor = listaIndicaciones.get(indiceIndicacionesActual).getPaciente();
			} else if ("medicamento".equals(jrf.getName())) {
				valor = listaIndicaciones.get(indiceIndicacionesActual).getMedicamento();		
			} else if ("comotomarlo".equals(jrf.getName())) {
				valor = listaIndicaciones.get(indiceIndicacionesActual).getComotomarlo();
			} else if ("doctor".equals(jrf.getName())) {
				valor = listaIndicaciones.get(indiceIndicacionesActual).getDoctor();
			}else if ("matricula".equals(jrf.getName())) {
				valor = listaIndicaciones.get(indiceIndicacionesActual).getDoctor();
			}
			return valor;
		}
	
		public boolean next() throws JRException {
			return ++indiceIndicacionesActual< listaIndicaciones.size();
		}
	
		public void addParticipante(ReporteIndicaciones indicaciones) {
			this.listaIndicaciones.add(indicaciones);
		}
		
}
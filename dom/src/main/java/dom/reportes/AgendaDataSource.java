package dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class AgendaDataSource implements JRDataSource 
{
	
	
	private List<ReporteAgenda> listaAgenda = new ArrayList<ReporteAgenda>();
	private int indiceAgendaActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("dia".equals(jrf.getName())) {
			valor = listaAgenda.get(indiceAgendaActual).getDia();
		} else if ("Doctor".equals(jrf.getName())) {
			valor = listaAgenda.get(indiceAgendaActual).getDoctor();		
		} else if ("Estado".equals(jrf.getName())) {
			valor = listaAgenda.get(indiceAgendaActual).getEstado();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAgendaActual< listaAgenda.size();
	}

	public void addParticipante(ReporteAgenda agenda) {
		this.listaAgenda.add(agenda);
	}
	
	

}

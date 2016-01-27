package dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class RecetaDataSource implements JRDataSource 
{
	private List<ReporteReceta> listaReceta = new ArrayList<ReporteReceta>();
	private int indiceRecetaActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("paciente".equals(jrf.getName())) {
			valor = listaReceta.get(indiceRecetaActual).getPaciente();
		} else if ("obraSocial".equals(jrf.getName())) {
			valor = listaReceta.get(indiceRecetaActual).getObraSocial();		
		} else if ("medicamento".equals(jrf.getName())) {
			valor = listaReceta.get(indiceRecetaActual).getMedicamento();
		} else if ("medicamento1".equals(jrf.getName())) {
			valor = listaReceta.get(indiceRecetaActual).getMedicamento1();
		} else if ("doctor".equals(jrf.getName())) {
			valor = listaReceta.get(indiceRecetaActual).getDoctor();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceRecetaActual< listaReceta.size();
	}

	public void addParticipante(ReporteReceta receta) {
		this.listaReceta.add(receta);
	}
	

}

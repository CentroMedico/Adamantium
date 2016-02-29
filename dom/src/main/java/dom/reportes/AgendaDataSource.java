/*
 Copyright 2015 Adamantium

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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

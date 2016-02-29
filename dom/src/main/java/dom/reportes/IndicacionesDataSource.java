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
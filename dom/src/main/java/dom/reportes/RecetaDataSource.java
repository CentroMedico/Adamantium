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

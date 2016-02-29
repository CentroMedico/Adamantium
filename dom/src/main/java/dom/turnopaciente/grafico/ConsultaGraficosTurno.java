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
package dom.turnopaciente.grafico;

import java.util.List;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.isisaddons.wicket.wickedcharts.cpt.applib.WickedChart;

import com.google.common.collect.Maps;

import dom.turnopaciente.TurnoPaciente;

@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "50")
@DomainService(repositoryFor = TurnoPaciente.class)
public class ConsultaGraficosTurno {

	public WickedChart graficoBarrasTurnoPacienteEstado() {
		Map<EstadoTurnoEnum, AtomicInteger> mapeo = Maps.newTreeMap();
		List<TurnoPaciente> lista = container.allInstances(TurnoPaciente.class);
		for (TurnoPaciente t : lista) {
			AtomicInteger integer = mapeo.get(t.getEstadoGrafico());
			if (integer == null) {
				integer = new AtomicInteger();
				mapeo.put(t.getEstadoGrafico(), integer);
			}
			integer.incrementAndGet();
		}
		return new WickedChart(new GraficoBarrasTurnoPacienteEstado(mapeo));

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
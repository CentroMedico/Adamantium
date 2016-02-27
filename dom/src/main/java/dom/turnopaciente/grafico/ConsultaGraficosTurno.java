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
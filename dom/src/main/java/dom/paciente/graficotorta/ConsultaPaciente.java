package dom.paciente.graficotorta;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.isisaddons.wicket.wickedcharts.cpt.applib.WickedChart;

import com.google.common.collect.Maps;

import dom.estado.EstadoEnum;
import dom.paciente.Paciente;

@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "50")
@DomainService(repositoryFor = Paciente.class)
public class ConsultaPaciente {

	public WickedChart graficoTortaPacientesActivos() {
		Map<EstadoEnum, AtomicInteger> mapeo = Maps.newTreeMap();
		List<Paciente> lista = container.allInstances(Paciente.class);
		for (Paciente p : lista) {
			AtomicInteger integer = mapeo.get(p.getEstado());
			if (integer == null) {
				integer = new AtomicInteger();
				mapeo.put(p.getEstado(), integer);
			}
			integer.incrementAndGet();
		}
		return new WickedChart(new GraficoTortaPacientesActivos(mapeo));

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
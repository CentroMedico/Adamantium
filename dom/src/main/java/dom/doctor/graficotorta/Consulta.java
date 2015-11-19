package dom.doctor.graficotorta;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.isisaddons.wicket.wickedcharts.cpt.applib.WickedChart;

import com.google.common.collect.Maps;

import dom.doctor.Doctor;
import dom.estado.EstadoEnum;

@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "50")
@DomainService(repositoryFor = Doctor.class)
public class Consulta {

	public WickedChart graficoTortaActivos() {
		Map<EstadoEnum, AtomicInteger> mapeo = Maps.newTreeMap();
		List<Doctor> lista = container.allInstances(Doctor.class);
		for (Doctor a : lista) {
			AtomicInteger integer = mapeo.get(a.getEstado());
			if (integer == null) {
				integer = new AtomicInteger();
				mapeo.put(a.getEstado(), integer);
			}
			integer.incrementAndGet();
		}
		return new WickedChart(new GraficoTortaActivos(mapeo));

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
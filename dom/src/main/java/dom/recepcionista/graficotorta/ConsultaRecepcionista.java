package dom.recepcionista.graficotorta;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.isisaddons.wicket.wickedcharts.cpt.applib.WickedChart;

import com.google.common.collect.Maps;

import dom.estado.EstadoEnum;
import dom.recepcionista.Recepcionista;

@DomainServiceLayout(named = "Recepcionista", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "50")
@DomainService(repositoryFor = Recepcionista.class)
public class ConsultaRecepcionista {

	public WickedChart graficoTortaRecepcionistasActivos() {
		Map<EstadoEnum, AtomicInteger> mapeo = Maps.newTreeMap();
		List<Recepcionista> lista = container.allInstances(Recepcionista.class);
		for (Recepcionista r : lista) {
			AtomicInteger integer = mapeo.get(r.getEstado());
			if (integer == null) {
				integer = new AtomicInteger();
				mapeo.put(r.getEstado(), integer);
			}
			integer.incrementAndGet();
		}
		return new WickedChart(new GraficoTortaRecepcionistasActivos(mapeo));

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
package dom.recepcionista.graficotorta;

import java.io.Serializable;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;

import com.googlecode.wickedcharts.highcharts.options.Options;

@Value(semanticsProviderClass = GraficoRecepcionistaSemantica.class)
public class GraficoRecepcionista implements Serializable {

	private static final long serialVersionUID = 1L;

	private Options options;

	public GraficoRecepcionista(Options o) {
		this.options = o;
	}

	public String title() {
		return "Recepcionistas Activos / Inactivos";
	}

	@Programmatic
	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

}
package dom.doctor.graficotorta;

import java.io.Serializable;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;

import com.googlecode.wickedcharts.highcharts.options.Options;

@Value(semanticsProviderClass = GraficoDoctorSemantica.class)
public class GraficoDoctor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Options options;

	public GraficoDoctor(Options o) {
		this.options = o;
	}

	public String title() {
		return "Doctores Activos / Inactivos";
	}

	@Programmatic
	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

}
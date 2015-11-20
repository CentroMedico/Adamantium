package dom.paciente.graficotorta;

import java.io.Serializable;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;

import com.googlecode.wickedcharts.highcharts.options.Options;

@Value(semanticsProviderClass = GraficoPacienteSemantica.class)
public class GraficoPaciente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Options options;

	public GraficoPaciente(Options o) {
		this.options = o;
	}

	public String title() {
		return "Grafico Pacientes";
	}

	@Programmatic
	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

}
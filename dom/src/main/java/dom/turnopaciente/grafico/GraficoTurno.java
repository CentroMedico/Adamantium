package dom.turnopaciente.grafico;

import java.io.Serializable;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;

import com.googlecode.wickedcharts.highcharts.options.Options;

import dom.paciente.grafico.GraficoPacienteSemantica;

@Value(semanticsProviderClass = GraficoTurnoPacienteSemantica.class)
public class GraficoTurno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Options options;

	public GraficoTurno(Options o) {
		this.options = o;
	}

	public String title() {
		return "Grafico Turnos";
	}

	@Programmatic
	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

}
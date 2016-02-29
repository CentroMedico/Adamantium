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
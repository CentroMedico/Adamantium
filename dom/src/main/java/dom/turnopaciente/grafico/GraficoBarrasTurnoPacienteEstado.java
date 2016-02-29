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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.CssStyle;
import com.googlecode.wickedcharts.highcharts.options.Cursor;
import com.googlecode.wickedcharts.highcharts.options.DataLabels;
import com.googlecode.wickedcharts.highcharts.options.HorizontalAlignment;
import com.googlecode.wickedcharts.highcharts.options.Labels;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotOptions;
import com.googlecode.wickedcharts.highcharts.options.PlotOptionsChoice;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.color.HighchartsColor;
import com.googlecode.wickedcharts.highcharts.options.color.NullColor;
import com.googlecode.wickedcharts.highcharts.options.color.RadialGradient;
import com.googlecode.wickedcharts.highcharts.options.functions.PercentageFormatter;
import com.googlecode.wickedcharts.highcharts.options.series.Point;
import com.googlecode.wickedcharts.highcharts.options.series.PointSeries;
import com.googlecode.wickedcharts.highcharts.options.series.Series;

public class GraficoBarrasTurnoPacienteEstado extends Options {

	private static final long serialVersionUID = 1L;

	public GraficoBarrasTurnoPacienteEstado(
			Map<EstadoTurnoEnum, AtomicInteger> a) {
		setChartOptions(new ChartOptions()
				.setPlotBackgroundColor(new NullColor())
				.setPlotBorderWidth(null).setPlotShadow(Boolean.FALSE)
				.setType(SeriesType.COLUMN).setMarginTop(50).setMarginRight(50)
				.setMarginBottom(100).setMarginLeft(80));

		setTitle(new Title(
				"Gráfico de porcentaje por estado de turnos de pacientes"));

		PercentageFormatter formato = new PercentageFormatter();
		setTooltip(new Tooltip().setFormatter(formato).setPercentageDecimals(1));

		setPlotOptions(new PlotOptionsChoice().setColumn(new PlotOptions()
				.setAllowPointSelect(Boolean.TRUE)
				.setCursor(Cursor.POINTER)
				.setDataLabels(
						new DataLabels().setEnabled(Boolean.FALSE)
								.setColor(new HexColor("#000000"))
								.setConnectorColor(new HexColor("#000000"))
								.setFormatter(formato))));

		setLegend(new Legend(Boolean.FALSE));

		List<String> labels = new ArrayList<String>();
		setxAxis(new Axis().setCategories(labels).setLabels(
				new Labels()
						.setRotation(-45)
						.setAlign(HorizontalAlignment.RIGHT)
						.setStyle(
								new CssStyle().setProperty("font-size", "13px")
										.setProperty("font-family",
												"Verdana, sans-serif"))));

		Series<Point> series = new PointSeries().setType(SeriesType.COLUMN);
		int i = 1;
		for (Map.Entry<EstadoTurnoEnum, AtomicInteger> entry : a.entrySet()) {
			series.addPoint(new Point(nombre(entry.getKey()), entry.getValue()
					.get()).setColor(new RadialGradient().setCx(0.5).setCy(0.3)
					.setR(0.7).addStop(0, new HighchartsColor(i))
					.addStop(1, new HighchartsColor(i).brighten(-0.3f))));
			i++;
		}
		addSeries(series);

	}

	private String nombre(EstadoTurnoEnum estado) {
		String salida = "";
		if (estado == EstadoTurnoEnum.Aceptado)
			salida = "Turnos Aceptados";
		else if (estado == EstadoTurnoEnum.Atendido)
			salida = "Turnos Atendidos";
		else if (estado == EstadoTurnoEnum.Cancelado)
			salida = "Turnos Cancelados";
		else if (estado == EstadoTurnoEnum.Disponible)
			salida = "Turnos Disponibles";
		else if (estado == EstadoTurnoEnum.Solicitado)
			salida = "Turnos Solicitados";

		return salida;
	}

}
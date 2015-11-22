package dom.paciente.graficotorta;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.Cursor;
import com.googlecode.wickedcharts.highcharts.options.DataLabels;
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

public class GraficoTortaPacientesMayoriaEdad extends Options {

	private static final long serialVersionUID = 1L;

	public GraficoTortaPacientesMayoriaEdad(Map<MayoriaEdadEnum, AtomicInteger> a) {
		setChartOptions(new ChartOptions()
				.setPlotBackgroundColor(new NullColor())
				.setPlotBorderWidth(null).setPlotShadow(Boolean.FALSE));

		setTitle(new Title("Grafico Pacientes Mayoria Edad"));

		PercentageFormatter formato = new PercentageFormatter();
		setTooltip(new Tooltip().setFormatter(formato).setPercentageDecimals(1));

		setPlotOptions(new PlotOptionsChoice().setPie(new PlotOptions()
				.setAllowPointSelect(Boolean.TRUE)
				.setCursor(Cursor.POINTER)
				.setDataLabels(
						new DataLabels().setEnabled(Boolean.TRUE)
								.setColor(new HexColor("#000000"))
								.setConnectorColor(new HexColor("#000000"))
								.setFormatter(formato))));

		Series<Point> series = new PointSeries().setType(SeriesType.PIE);
		int i = 8;
		for (Map.Entry<MayoriaEdadEnum, AtomicInteger> entry : a.entrySet()) {
			series.addPoint(new Point(nombre(entry.getKey()), entry.getValue()
					.get()).setColor(new RadialGradient().setCx(0.5).setCy(0.3)
					.setR(0.7).addStop(0, new HighchartsColor(i))
					.addStop(1, new HighchartsColor(i).brighten(-0.3f))));
			i++;
		}
		addSeries(series);
	}

	private String nombre(MayoriaEdadEnum edad) {
		String salida = "";
		if (edad == MayoriaEdadEnum.Mayor)
			salida = "Mayores de edad";
		else
			salida = "Menores de edad";
		return salida;
	}
}
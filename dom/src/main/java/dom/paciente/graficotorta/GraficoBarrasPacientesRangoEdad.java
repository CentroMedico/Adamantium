package dom.paciente.graficotorta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.EntityCollectionModel;
import org.apache.isis.viewer.wicket.ui.components.collectioncontents.summary.CollectionContentsAsSummary.Summary;

import com.google.common.collect.Lists;
import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.Cursor;
import com.googlecode.wickedcharts.highcharts.options.DataLabels;
import com.googlecode.wickedcharts.highcharts.options.HorizontalAlignment;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.LegendLayout;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotOptions;
import com.googlecode.wickedcharts.highcharts.options.PlotOptionsChoice;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.VerticalAlignment;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.color.HighchartsColor;
import com.googlecode.wickedcharts.highcharts.options.color.NullColor;
import com.googlecode.wickedcharts.highcharts.options.color.RadialGradient;
import com.googlecode.wickedcharts.highcharts.options.functions.PercentageFormatter;
import com.googlecode.wickedcharts.highcharts.options.series.Point;
import com.googlecode.wickedcharts.highcharts.options.series.PointSeries;
import com.googlecode.wickedcharts.highcharts.options.series.Series;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;

public class GraficoBarrasPacientesRangoEdad extends Options {

	private static final long serialVersionUID = 1L;

	public GraficoBarrasPacientesRangoEdad(Map<RangoEdadEnum, AtomicInteger> a) {
		setChartOptions(new ChartOptions()
				.setPlotBackgroundColor(new NullColor())
				.setPlotBorderWidth(null).setPlotShadow(Boolean.FALSE)
				.setMarginTop(50).setMarginRight(50).setMarginBottom(100)
				.setMarginLeft(80));

		setTitle(new Title(
				"Gráfico de porcentaje por rango de edades de pacientes"));

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

		Series<Point> series = new PointSeries().setType(SeriesType.COLUMN);
		int i = 1;
		for (Map.Entry<RangoEdadEnum, AtomicInteger> entry : a.entrySet()) {
			series.addPoint(new Point(nombre(entry.getKey()), entry.getValue()
					.get()).setColor(new RadialGradient().setCx(0.5).setCy(0.3)
					.setR(0.7).addStop(0, new HighchartsColor(i))
					.addStop(1, new HighchartsColor(i).brighten(-0.3f))));
			i++;
		}
		addSeries(series);
	}

	private String nombre(RangoEdadEnum edad) {
		String salida = "";
		if (edad == RangoEdadEnum.MenorCinco)
			salida = "Menores a 5 Años";
		else if (edad == RangoEdadEnum.MenorDiez)
			salida = "Menores a 10 años";
		else if (edad == RangoEdadEnum.MenorVeinte)
			salida = "Menores a 20 años";
		else if (edad == RangoEdadEnum.MenorTreinta)
			salida = "Menores a 30 años";
		else if (edad == RangoEdadEnum.MenorCuarenta)
			salida = "Menores a 40 años";
		else if (edad == RangoEdadEnum.MenorCincuenta)
			salida = "Menores a 50 años";
		else if (edad == RangoEdadEnum.MenorSesenta)
			salida = "Menores a 60 años";
		else if (edad == RangoEdadEnum.MenorSetenta)
			salida = "Menores a 70 años";
		else if (edad == RangoEdadEnum.MenorOchenta)
			salida = "Menores a 80 años";
		else
			salida = "Mayores a 80 años";

		return salida;
	}

}
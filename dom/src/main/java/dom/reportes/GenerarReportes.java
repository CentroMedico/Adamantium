package dom.reportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class GenerarReportes 
{
	public static void generarReporte(String jrxml, List<Object> parametros, String nombreArchivo)
	 			throws JRException, IOException {
	 		HashMap<String, Object> map = new HashMap<String, Object>();
	 
	 		File file = new File(jrxml);
	 		JRBeanArrayDataSource jArray = new JRBeanArrayDataSource(parametros.toArray());
	 		FileInputStream input = null;
	 		try {
	 			input = new FileInputStream(file);
	 
	 		} catch (Exception e) {
	 			System.out.println(e.getMessage());
	 		}
	 		
	 		JasperDesign jd = JRXmlLoader.load(input);
	 		JasperReport reporte = JasperCompileManager.compileReport(jd);
	 		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
	 
	 		// Lo muestra con el jasperviewer
	 		// JasperViewer.viewReport(print, false);
	 
	 		JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf");
	 
	 		// Muestra el reporte en otra ventana
	 		// JasperExportManager.exportReportToHtmlFile(print, "nuevo.html");
	 
	 	}
	 
	 	@javax.inject.Inject
	 	public ReportContext reportContext;
	 	 	@javax.inject.Inject
	 	DomainObjectContainer container;

}

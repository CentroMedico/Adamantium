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

package dom.agendadoctor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Named;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.value.Blob;

import dom.doctor.Doctor;
import dom.historiaclinica.IndicacionesMedicas;
import dom.paciente.Paciente;
import dom.reportes.AgendaDataSource;
import dom.reportes.ReporteAgenda;
import dom.turnopaciente.TurnoPaciente;

@DomainService(repositoryFor = AgendaDoctor.class)
@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "50")
@Named("Turno")
public class AgendaDoctorServicio extends AbstractFactoryAndRepository {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Turno: ");
	}

	public String iconName() {
		return "calendario";
	}

	TurnoPaciente turno = new TurnoPaciente();

	@SuppressWarnings("deprecation")
	@MemberOrder(name = "Doctor", sequence = "50")
	@ActionLayout(cssClass = "boton")
	public String crearAgendaQuincenal(
			@ParameterLayout(named = "Doctor") final Doctor doctor) {

		Date fecha = new Date();
		fecha.setHours(07);
		fecha.setMinutes(00);
		fecha.setSeconds(00);

		for (int x = 0; x < 15; x++) {
			fecha = SumarFecha(fecha, 1);

			fecha.setHours(07);
			fecha.setMinutes(00);
			fecha.setSeconds(00);

			for (int i = 0; i < 27; i++) {

				final AgendaDoctor agenda = newTransientInstance(AgendaDoctor.class);
				Calendar c1 = GregorianCalendar.getInstance();

				agenda.setDia(fecha);
				agenda.setDoctor(doctor);
				agenda.setEstado(turno.getEstadoTurno());

				doctor.getListaAgenda().add(agenda);
				c1 = agregarMinutos(fecha, 30);
				fecha = c1.getTime();
				persistIfNotAlready(agenda);
				container.flush();
			}
		}

		return "Agenda quincenal del doctor " + doctor.getApellido() + ", "
				+ doctor.getNombre() + " creada correctamente";

	}

	public List<Doctor> choices0CrearAgendaQuincenal() {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));
	}

	@SuppressWarnings("deprecation")
	@MemberOrder(name = "Doctor", sequence = "51")
	@ActionLayout(cssClass = "boton")
	public String crearAgendaMensual(
			@ParameterLayout(named = "Doctor") final Doctor doctor) {

		Date fecha = new Date();
		fecha.setHours(07);
		fecha.setMinutes(00);
		fecha.setSeconds(00);

		for (int x = 0; x < 30; x++) {
			fecha = SumarFecha(fecha, 1);

			fecha.setHours(07);
			fecha.setMinutes(00);
			fecha.setSeconds(00);

			for (int i = 0; i < 27; i++) {

				final AgendaDoctor agenda = newTransientInstance(AgendaDoctor.class);
				Calendar c1 = GregorianCalendar.getInstance();

				agenda.setDia(fecha);
				agenda.setDoctor(doctor);
				agenda.setEstado(turno.getEstadoTurno());
				doctor.getListaAgenda().add(agenda);
				c1 = agregarMinutos(fecha, 30);
				fecha = c1.getTime();
				persistIfNotAlready(agenda);
				container.flush();
			}
		}

		return "Agenda mensual del doctor " + doctor.getApellido() + ", "
				+ doctor.getNombre() + " creada correctamente";
	}

	public List<Doctor> choices0CrearAgendaMensual() {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public Calendar agregarMinutos(Date date, int minutes) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.MINUTE, minutes);
		return calendarDate;
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public Date SumarFecha(Date fecha, int dias) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha); // Configuramos la fecha que se recibe

		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de días a añadir, o
													// restar en caso de días<0

		return calendar.getTime();

	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<AgendaDoctor> buscarTurno(String Turno) {
		return allMatches(QueryDefault.create(AgendaDoctor.class,
				"traerTurnos", Turno));
	}

	@MemberOrder(name = "Doctor", sequence = "105")
	public List<AgendaDoctor> listarAgenda() {
		return container.allInstances(AgendaDoctor.class);
	}

	@MemberOrder(name = "Doctor", sequence = "106")
	@ActionLayout(named = "Listar Agenda por Doctor")
	public List<AgendaDoctor> listaDoctor(final Doctor doc) {
		return allMatches(QueryDefault.create(AgendaDoctor.class,
				"traerPorDoctor", "doctor", doc));
	}

	@MemberOrder(name = "Doctor", sequence = "107")
	@ActionLayout(named = "Exportar Agenda")
	public Blob downloadAll(final Doctor doc) throws JRException, IOException {
		AgendaDataSource datasource = new AgendaDataSource();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		for (AgendaDoctor a : listaDoctor(doc)) {

			ReporteAgenda agenda = new ReporteAgenda();
			agenda.setDia(df.format(a.getDia()));
			agenda.setDoctor(a.getDoctor().getApellido() + "-"
					+ a.getDoctor().getNombre());
			agenda.setEstado(a.getEstado());

			datasource.addParticipante(agenda);
		}
		File file = new File("Agenda.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
				datasource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/tmp/salida.pdf");
		File archivo = new File("/tmp/salida.pdf");

		byte[] fileContent = new byte[(int) archivo.length()];

		if (!(archivo.exists())) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		try {
			FileInputStream fileInputStream = new FileInputStream(archivo);

			fileInputStream.read(fileContent);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return new Blob(doc.getApellido() + " - " + doc.getNombre()
					+ ".pdf", "application/pdf", fileContent);
		} catch (Exception e) {
			byte[] result = new String("error en crear archivo").getBytes();
			return new Blob("error.txt", "text/plain", result);
		}

	}

	public List<Doctor> choices0DownloadAll() {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));
	}

	@MemberOrder(name = "Doctor", sequence = "107")
	@ActionLayout(named = "Exportar Mi Agenda")
	public Blob downloadAll1() throws JRException, IOException {
		Doctor doc = container.firstMatch(QueryDefault.create(Doctor.class,
				"traerDoctorPorUsuario", "usuariovinculado", container
						.getUser().getName()));
		AgendaDataSource datasource = new AgendaDataSource();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		for (AgendaDoctor a : listaDoctor(doc)) {

			ReporteAgenda agenda = new ReporteAgenda();
			agenda.setDia(df.format(a.getDia()));
			agenda.setDoctor(a.getDoctor().getApellido() + "-"
					+ a.getDoctor().getNombre());
			agenda.setEstado(a.getEstado());

			datasource.addParticipante(agenda);
		}
		File file = new File("Agenda.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
				datasource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/tmp/salida.pdf");
		File archivo = new File("/tmp/salida.pdf");

		byte[] fileContent = new byte[(int) archivo.length()];

		if (!(archivo.exists())) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		try {
			FileInputStream fileInputStream = new FileInputStream(archivo);

			fileInputStream.read(fileContent);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return new Blob(doc.getApellido() + " - " + doc.getNombre()
					+ ".pdf", "application/pdf", fileContent);
		} catch (Exception e) {
			byte[] result = new String("error en crear archivo").getBytes();
			return new Blob("error.txt", "text/plain", result);
		}

	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
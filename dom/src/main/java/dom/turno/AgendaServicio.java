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
package dom.turno;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Named;

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

import dom.doctor.Doctor;
import dom.especialidad.EspecialidadEnum;
import dom.paciente.Paciente;

@DomainService(repositoryFor = Agenda.class)
@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "50")
@Named("Turno")
public class AgendaServicio extends AbstractFactoryAndRepository {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Turno: ");
	}

	public String iconName() {
		return "turnos";
	}

	@SuppressWarnings("deprecation")
	@MemberOrder(name = "Doctor", sequence = "50")
	public String crearAgenda(
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

				final Agenda agenda = newTransientInstance(Agenda.class);
				Calendar c1 = GregorianCalendar.getInstance();

				agenda.setDia(fecha);
				agenda.setDoctor(doctor);
				doctor.getListaAgenda().add(agenda);
				c1 = agregarMinutos(fecha, 30);
				fecha = c1.getTime();
				persistIfNotAlready(agenda);
				container.flush();
			}
		}
		return "Agenda de doctor creada correctamente";
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
	public List<Agenda> buscarAgenda(String Turno) {
		return allMatches(QueryDefault
				.create(Agenda.class, "traerTodos", Turno));
	}

	@MemberOrder(name = "Doctor", sequence = "50")
	public List<Agenda> listarAgenda() {
		return container.allInstances(Agenda.class);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
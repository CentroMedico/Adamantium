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
package dom.turnopaciente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.email.EmailService;

import dom.agendadoctor.AgendaDoctor;
import dom.doctor.Doctor;
import dom.especialidad.EspecialidadEnum;
import dom.paciente.Paciente;
import dom.turnopaciente.grafico.EstadoTurnoEnum;

@DomainService(repositoryFor = TurnoPaciente.class)
@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
public class TurnoPacienteServicio extends AbstractFactoryAndRepository {
	@Inject
	private EmailService email;
	String mensajeDia = "Su turno es el: ";
	String mensajeDoctor = ", con el doctor: ";
	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy HH:mm");

	public String iconName() {
		return "calendario";
	}

	@ActionLayout(cssClass = "boton")
	public TurnoPaciente asignarTurno(
			@ParameterLayout(named = "Especialidad") final EspecialidadEnum especialidad,
			@ParameterLayout(named = "Doctor") final Doctor doctor,
			@ParameterLayout(named = "Horario") final AgendaDoctor agendaDoctor,
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Motivo de Consulta") final String motivoConsulta) {

		final TurnoPaciente turno = newTransientInstance(TurnoPaciente.class);

		turno.getEstado().solicitarTurno(doctor, paciente);
		turno.setEstadoGrafico(EstadoTurnoEnum.Solicitado);
		turno.setHorarioTurno(agendaDoctor);
		agendaDoctor.setEstado(turno.getEstadoTurno());
		turno.setMensajeAPaciente(mensajeDia
				+ fecha.format(agendaDoctor.getDia()) + mensajeDoctor
				+ doctor.getApellido() + " " + doctor.getNombre());
		turno.setEstado2(EstadoTurnoEnum.Solicitado.getClass().getSimpleName());
		EnviarEmail(paciente, turno);
		turno.setMotivoConsulta(motivoConsulta);
		paciente.getListaTurnos().add(turno);
		persistIfNotAlready(turno);
		container.flush();
		return turno;
	}

	public EspecialidadEnum default0AsignarTurno() {

		return EspecialidadEnum.Clinica_General;

	}

	public List<Doctor> choices1AsignarTurno(final EspecialidadEnum especialidad) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivosPorEspecialidad", "especialidad", especialidad));

	}

	public List<AgendaDoctor> choices2AsignarTurno(
			final EspecialidadEnum especialidad, Doctor doctor) {
		return container.allMatches(QueryDefault.create(AgendaDoctor.class,
				"traerTurnosDisponiblesDoctor", "doctor", doctor));
	}

	public List<Paciente> choices3AsignarTurno(
			final EspecialidadEnum especialidad, final Doctor doctor,
			final AgendaDoctor agendaDoctor, final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@ActionLayout(cssClass = "boton")
	public TurnoPaciente sacarTurno(
			@ParameterLayout(named = "Especialidad") final EspecialidadEnum especialidad,
			@ParameterLayout(named = "Doctor") final Doctor doctor,
			@ParameterLayout(named = "Horario") final AgendaDoctor agendaDoctor,
			@ParameterLayout(named = "Motivo de Consulta") final String motivoConsulta) {

		final TurnoPaciente turno = newTransientInstance(TurnoPaciente.class);

		Paciente paciente = container.firstMatch(QueryDefault.create(
				Paciente.class, "traerPacientePorUsuario", "usuariovinculado",
				container.getUser().getName()));

		turno.getEstado().solicitarTurno(doctor, paciente);
		turno.setEstadoGrafico(EstadoTurnoEnum.Solicitado);
		turno.setHorarioTurno(agendaDoctor);
		agendaDoctor.setEstado(turno.getEstadoTurno());
		turno.setMensajeAPaciente(mensajeDia
				+ fecha.format(agendaDoctor.getDia()) + mensajeDoctor
				+ doctor.getApellido() + " " + doctor.getNombre());
		turno.setEstado2(EstadoTurnoEnum.Solicitado.getClass().getSimpleName());
		EnviarEmail(paciente, turno);
		turno.setMotivoConsulta(motivoConsulta);
		paciente.getListaTurnos().add(turno);
		persistIfNotAlready(turno);
		container.flush();
		return turno;
	}

	public List<EspecialidadEnum> choices0SacarTurno() {

		List<EspecialidadEnum> especialidades = new ArrayList<EspecialidadEnum>();

		for (EspecialidadEnum aux : EspecialidadEnum.values()) {
			especialidades.add(aux);
		}
		return especialidades;

	}

	public List<Doctor> choices1SacarTurno(final EspecialidadEnum especialidad) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivosPorEspecialidad", "especialidad", especialidad));

	}

	public List<AgendaDoctor> choices2SacarTurno(
			final EspecialidadEnum especialidad, Doctor doctor) {
		return container.allMatches(QueryDefault.create(AgendaDoctor.class,
				"traerTurnosDisponiblesDoctor", "doctor", doctor));
	}

	public String cancelarTurno(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Turno Paciente") final TurnoPaciente turnoPaciente) {

		turnoPaciente.cancelarTurno();
		turnoPaciente.setEstado2("Cancelado");
		turnoPaciente.getHorarioTurno().setEstado("Cancelado");
		EnviarEmailCancelado(paciente, turnoPaciente);
		container.flush();
		return "Turno Cancelado";
	}

	public List<TurnoPaciente> choices1CancelarTurno(final Paciente paciente) {
		return container.allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerParaCancelarPorPaciente", "paciente", paciente));
	}

	public String cancelarMiTurno(
			@ParameterLayout(named = "Turno Paciente") final TurnoPaciente turnoPaciente) {

		Paciente paciente = container.firstMatch(QueryDefault.create(
				Paciente.class, "traerPacientePorUsuario", "usuariovinculado",
				container.getUser().getName()));

		turnoPaciente.cancelarTurno();
		turnoPaciente.setEstado2("Cancelado");
		turnoPaciente.getHorarioTurno().setEstado("Cancelado");
		EnviarEmailCancelado(paciente, turnoPaciente);
		container.flush();
		return "Turno Cancelado";
	}

	public List<TurnoPaciente> choices0CancelarMiTurno() {
		Paciente paciente = container.firstMatch(QueryDefault.create(
				Paciente.class, "traerPacientePorUsuario", "usuariovinculado",
				container.getUser().getName()));
		return container.allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerParaCancelarPorPaciente", "paciente", paciente));
	}

	@ActionLayout(hidden = Where.EVERYWHERE, cssClass = "boton")
	public List<TurnoPaciente> buscarTurno(String turno) {
		return allMatches(QueryDefault
				.create(TurnoPaciente.class, "traerTodos"));
	}

	@ActionLayout(hidden = Where.EVERYWHERE, cssClass = "boton")
	public List<Paciente> buscarPaciente(String paciente) {
		return allMatches(QueryDefault.create(Paciente.class,
				"buscarNombre,Apellido,Id", "parametro", paciente));
	}

	@ActionLayout(hidden = Where.EVERYWHERE, cssClass = "boton")
	public List<TurnoPaciente> listarTurnos(String turno) {
		return allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerTodos", "parametro", turno));
	}

	@MemberOrder(name = "Paciente", sequence = "5.2")
	public List<TurnoPaciente> listarTurnosPaciente() {
		return container.allInstances(TurnoPaciente.class);
	}

	@MemberOrder(name = "Paciente", sequence = "5.3")
	public List<TurnoPaciente> listarMisTurnos() {
		// return container.allInstances(TurnoPaciente.class);
		return allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerTurnosPacientePorUsuario", "usuariovinculado", container
						.getUser().getName()));
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public void EnviarEmail(Paciente paciente, TurnoPaciente turno) {
		if (paciente.getCorreo().contains("@")) {
			ArrayList<String> to = new ArrayList<String>();
			to.add(paciente.getCorreo());

			email.send(to, null, null, "Adamantium le Recuenda su Turno",
					turno.getMensajeAPaciente(),
					(javax.activation.DataSource[]) null);
			container.informUser("Se ha enviado un correo");
		}
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public void EnviarEmailCancelado(Paciente paciente, TurnoPaciente turno) {
		// SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy HH:mm");
		String mensajeCancelado = "Su turno del dia "
				// + fecha.format(turno.getHorarioTurno()) + " con el doctor "
				+ fecha.format(turno.getHorarioTurno().getDia())
				+ " con el Doctor " + turno.getDoctor().getApellido() + " "
				+ turno.getDoctor().getNombre() + " ha sido cancelado.";

		if (paciente.getCorreo().contains("@")) {
			ArrayList<String> to = new ArrayList<String>();
			to.add(paciente.getCorreo());

			email.send(to, null, null, "Su turno ha sido cancelado",
					mensajeCancelado, (javax.activation.DataSource[]) null);
			container.informUser("Se ha enviado un correo");
		}
	}

	// @ActionLayout(hidden = Where.EVERYWHERE)
	// public TurnoPacienteServicio EnviarSMS(Paciente paciente, TurnoPaciente
	// turno)
	// {
	//
	// String url =
	// "http://servicio.smsmasivos.com.ar/enviar_sms.asp?api=1&relogin=1&usuario=SMSDEMO77832&clave=SMSDEMO77832666&tos="
	// + paciente.getTelefono() + "" + turno.getMensajeAPaciente();
	//
	// HttpClient client = HttpClientBuilder.create().build();
	// HttpPost post = new HttpPost(url);
	//
	// container.informUser("El SMS a sido enviado correctamente al cliente");
	// try {
	// HttpResponse response = client.execute(post);
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	//
	// return this;
	// }

	/*
	 * EnviarMensajes Enviamos un solo mensaje
	 */

	@javax.inject.Inject
	DomainObjectContainer container;
}
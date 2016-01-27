package dom.turnopaciente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
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
		turno.setHorarioTurno(agendaDoctor);
		agendaDoctor.setEstado(turno.getEstadoTurno());
		turno.setMensajeAPaciente(mensajeDia
				+ fecha.format(agendaDoctor.getDia()) + mensajeDoctor
				+ doctor.getApellido() + " " + doctor.getNombre());
		turno.setEstado2(turno.getEstadoTurno());
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
				"traerPorEspecialidad", "especialidad", especialidad));

	}
	public List<AgendaDoctor> choices2AsignarTurno(
			final EspecialidadEnum especialidad, Doctor doctor) {
		return container.allMatches(QueryDefault.create(AgendaDoctor.class,
				"traerTurnosDisponiblesDoctor", "doctor", doctor));
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

	@ActionLayout(hidden = Where.EVERYWHERE)
	public void EnviarEmail(Paciente paciente, TurnoPaciente turno) {
		if (paciente.getCorreo().contains("@")) {
			ArrayList<String> to = new ArrayList<String>();
			to.add(paciente.getCorreo());

			email.send(to, null, null, "Adamantium le Recuenda su Turno",
					turno.getMensajeAPaciente(),
					(javax.activation.DataSource[]) null);
			container.informUser("se ha enviado un correo");
		}
	}

//	@ActionLayout(hidden = Where.EVERYWHERE)
//	public TurnoPacienteServicio EnviarSMS(Paciente paciente, TurnoPaciente turno)
//	{
//			
//	String url = "http://servicio.smsmasivos.com.ar/enviar_sms.asp?api=1&relogin=1&usuario=SMSDEMO77832&clave=SMSDEMO77832666&tos=" 
//+ paciente.getTelefono() + "" + turno.getMensajeAPaciente();
//
//	HttpClient client = HttpClientBuilder.create().build();
//	HttpPost post = new HttpPost(url);
//	
//	container.informUser("El SMS a sido enviado correctamente al cliente");
//	try {
//		HttpResponse response = client.execute(post);
//	} catch (ClientProtocolException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	
//				
//return this;
//}	
	
	/*
	 * EnviarMensajes
	 * Enviamos un solo mensaje
	 */   
	

	@javax.inject.Inject
	DomainObjectContainer container;
}
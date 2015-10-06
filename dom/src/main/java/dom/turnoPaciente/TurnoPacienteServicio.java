package dom.turnoPaciente;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;

import com.google.inject.name.Named;

import dom.agendaDoctor.AgendaDoctor;
import dom.doctor.Doctor;
import dom.especialidad.EspecialidadEnum;
import dom.paciente.Paciente;

@DomainService(repositoryFor = TurnoPaciente.class)
@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
public class TurnoPacienteServicio extends AbstractFactoryAndRepository {

	public TurnoPaciente asignarTurno(
			final @Named("Especialidad") EspecialidadEnum especialidad,
			final @Named("Doctor") Doctor doctor,
			final @Named("Agenda Doctor") AgendaDoctor agendaDoctor,
			final @Named("Paciente") Paciente paciente) {

		final TurnoPaciente turno = newTransientInstance(TurnoPaciente.class);
		turno.getEstado().solicitarTurno(doctor, paciente);
		turno.setPaciente(paciente);
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
				"traerTurnos"));
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<Paciente> buscarPaciente(String paciente) {
		return allMatches(QueryDefault.create(Paciente.class,
				"buscarNombre,Apellido,Id", "parametro", paciente));
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<TurnoPaciente> listarTurnos(String turno) {
		return allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerTodos", "parametro", turno));
	}

	@MemberOrder(name = "Paciente", sequence = "5.2")
	public List<TurnoPaciente> listarTurnosPaciente() {
		return container.allInstances(TurnoPaciente.class);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
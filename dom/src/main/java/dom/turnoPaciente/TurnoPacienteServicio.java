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

import dom.agendaDoctor.AgendaDoctor;
import dom.doctor.Doctor;
import dom.especialidad.EspecialidadEnum;
import dom.paciente.Paciente;

@DomainService(repositoryFor = TurnoPaciente.class)
@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
public class TurnoPacienteServicio extends AbstractFactoryAndRepository {

	@MemberOrder(name = "Paciente", sequence = "75")
	public String reservarTurno(final EspecialidadEnum especialidad,
			Doctor doctor, AgendaDoctor agenda, Paciente paciente) {

		final TurnoPaciente turno = newTransientInstance(TurnoPaciente.class);

		turno.setDisponible(false);
		turno.setPaciente(paciente);
		persist(turno);
		return "Turno de Paciente agregado correctamente.";

	}

	public EspecialidadEnum default0ReservarTurno() {

		return EspecialidadEnum.Clinica_General;

	}

	public List<Doctor> choices1ReservarTurno(
			final EspecialidadEnum especialidad) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerPorEspecialidad", "especialidad", especialidad));

	}

	public List<AgendaDoctor> choices2ReservarTurno(
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

	@javax.inject.Inject
	DomainObjectContainer container;
}
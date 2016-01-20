package dom.turnopaciente;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.agendadoctor.AgendaDoctor;
import dom.doctor.Doctor;
import dom.paciente.Paciente;

@javax.jdo.annotations.Queries({

		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente "),
		@javax.jdo.annotations.Query(name = "traerAtendidosPorPaciente", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE estado2 == 'Atendido' && paciente== :paciente"),
		@javax.jdo.annotations.Query(name = "traerAtendidos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE estado2 == 'Atendido'") })
@PersistenceCapable
public class TurnoPaciente {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Turno de: " + this.paciente.getApellido() + ", "
						+ this.paciente.getNombre());
	}

	public TurnoPaciente() {
		this.disponible = new Disponible(this);
		this.solicitado = new Solicitado(this);
		this.aceptado = new Aceptado(this);
		this.atendido = new Atendido(this);
		this.cancelado = new Cancelado(this);

		this.setEstado(this.getDisponible());
	}

	public String iconName() {
		return "calendario";
	}

	// {{ Paciente (property)
	private Paciente paciente;

	@MemberOrder(sequence = "1")
	@Persistent(table = "lista_turnos", mappedBy = "listaTurnos")
	@Join(column = "turno_id")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	/**
	 * Pemite obtener un paciente 
	 * 
	 * @return paciente Paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	// }}

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}

	// {{ Estado (property)
	private IEstadoTurno estado;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	@Persistent(extensions = {
			@Extension(vendorName = "datanucleous", key = "mapping-strategy", value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-clases", value = "dom.turnoPaciente.Disponible"
					+ ",dom.turnoPaciente.Solicitado"
					+ ",dom.turnoPaciente.Aceptado"
					+ ",dom.turnoPaciente.Atendido"
					+ ",dom.turnoPaciente.Cancelado") }, columns = {
			@Column(name = "idTurnoDisponible"),
			@Column(name = "idTurnoSolicitado"),
			@Column(name = "idTurnoAceptado"),
			@Column(name = "idTurnoAtendido"),
			@Column(name = "idTurnoCancelado") })
	IEstadoTurno getEstado() {
		return estado;
	}

	protected void setEstado(final IEstadoTurno estado) {
		this.estado = estado;
	}

	// {{ HorarioTurno (property)
	private AgendaDoctor horarioTurno;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public AgendaDoctor getHorarioTurno() {
		return horarioTurno;
	}

	public void setHorarioTurno(final AgendaDoctor horarioTurno) {
		this.horarioTurno = horarioTurno;
	}

	// }}

	// {{ MensajeAPaciente (property)
	private String mensajeAPaciente;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	@PropertyLayout(multiLine = 2)
	@Property(editing = Editing.DISABLED)
	public String getMensajeAPaciente() {
		return mensajeAPaciente;
	}

	public void setMensajeAPaciente(final String mensajeAPaciente) {
		this.mensajeAPaciente = mensajeAPaciente;
	}

	// }}

	public TurnoPaciente disponerTurno() {
		getEstado().disponerTurno();
		horarioTurno.setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente solicitarTurno(Doctor doctor, Paciente paciente) {
		getEstado().solicitarTurno(doctor, paciente);
		horarioTurno.setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente aceptarTurno() {
		getEstado().aceptarTurno();
		horarioTurno.setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente atenderTurno() {
		getEstado().atenderTurno();
		horarioTurno.setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente cancelarTurno() {
		getEstado().cancelarTurno();
		horarioTurno.setEstado(getEstadoTurno());
		return this;

	}

	// {{ Aceptado (property)
	private Aceptado aceptado;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.EVERYWHERE)
	public Aceptado getAceptado() {
		return aceptado;
	}

	public void setAceptado(final Aceptado aceptado) {
		this.aceptado = aceptado;
	}

	// }}

	// {{ Atendido (property)
	private Atendido atendido;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.EVERYWHERE)
	public Atendido getAtendido() {
		return atendido;
	}

	public void setAtendido(final Atendido atendido) {
		this.atendido = atendido;
	}

	// }}

	// {{ Cancelado (property)
	private Cancelado cancelado;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.EVERYWHERE)
	public Cancelado getCancelado() {
		return cancelado;
	}

	public void setCancelado(final Cancelado cancelado) {
		this.cancelado = cancelado;
	}

	// }}

	// {{ Disponible (property)
	private Disponible disponible;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.EVERYWHERE)
	Disponible getDisponible() {
		return disponible;
	}

	public void setDisponible(final Disponible disponible) {
		this.disponible = disponible;
	}

	// }}

	// {{ Solicitado (property)
	private Solicitado solicitado;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.EVERYWHERE)
	public Solicitado getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(final Solicitado solicitado) {
		this.solicitado = solicitado;
	}

	// }}

	// {{ MotivoConsulta (property)
	private String motivoConsulta;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(final String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getEstadoTurno() {
		return this.getEstado().getClass().getSimpleName();
	}

	// MENSAJE DE ERROR EN CAMBIO DE ESTADOS

	void mostrarMensajeError(final String msg) {
		container.informUser(msg);
	}

	// {{ Estado2 (property)
	private String estado2;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.EVERYWHERE)
	public String getEstado2() {
		return estado2;
	}

	public void setEstado2(final String estado2) {
		this.estado2 = estado2;
	}

	// }}

	@javax.inject.Inject
	private DomainObjectContainer container;

}
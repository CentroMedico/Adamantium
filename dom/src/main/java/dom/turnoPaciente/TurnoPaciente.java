package dom.turnoPaciente;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;

import dom.doctor.Doctor;
import dom.paciente.Paciente;

@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
		+ "FROM dom.turnoPaciente.TurnoPaciente "), })
@PersistenceCapable
public class TurnoPaciente {

	public TurnoPaciente() {
		this.disponible = new Disponible(this);
		this.solicitado = new Solicitado(this);
		this.aceptado = new Aceptado(this);
		this.atendido = new Atendido(this);
		this.cancelado = new Cancelado(this);

		this.setEstado(this.getDisponible());
	}

	// {{ Paciente (property)
	private Paciente paciente;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
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
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}

	// {{ IEstadoTurno (property)
	private IEstadoTurno estado;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
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
	public IEstadoTurno getEstado() {
		return estado;
	}

	protected void setEstado(final IEstadoTurno estado) {
		this.estado = estado;
	}

	public TurnoPaciente disponerTurno() {
		getEstado().disponerTurno();
		return this;

	}

	public TurnoPaciente solicitarTurno(Doctor doctor, Paciente paciente) {
		getEstado().solicitarTurno(doctor, paciente);
		return this;

	}

	public TurnoPaciente aceptarTurno() {
		getEstado().aceptarTurno();
		return this;

	}

	public TurnoPaciente atenderTurno() {
		getEstado().atenderTurno();
		return this;

	}

	public TurnoPaciente cancelarTurno() {
		getEstado().cancelarTurno();
		return this;

	}

	public String getNombreDeEstado() {
		return this.getEstado().getClass().getSimpleName();
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
	public Disponible getDisponible() {
		return disponible;
	}

	public void setDisponible(final Disponible disponible) {
		this.disponible = disponible;
	}

	// }}

	// {{ DisponerOcultado (property)
	private boolean disponerOcultado = false;

	@NotPersistent
	@MemberOrder(sequence = "1")
	@Property(hidden = Where.EVERYWHERE)
	public boolean getDisponerOcultado() {
		return disponerOcultado;
	}

	public void setDisponerOcultado(final boolean disponerOcultado) {
		this.disponerOcultado = disponerOcultado;
	}

	// }}

	// {{ SolicitarOcultado (property)
	private boolean solicitarOcultado = false;

	@NotPersistent
	@MemberOrder(sequence = "1")
	@Property(hidden = Where.EVERYWHERE)
	public boolean getSolicitarOcultado() {
		return solicitarOcultado;
	}

	public void setSolicitarOcultado(final boolean solicitarOcultado) {
		this.solicitarOcultado = solicitarOcultado;
	}

	// }}

	// {{ AceptarOcultado (property)
	private boolean aceptarOcultado = false;

	@NotPersistent
	@MemberOrder(sequence = "1")
	@Property(hidden = Where.EVERYWHERE)
	public boolean getAceptarOcultado() {
		return aceptarOcultado;
	}

	public void setAceptarOcultado(final boolean aceptarOcultado) {
		this.aceptarOcultado = aceptarOcultado;
	}

	// }}

	// {{ AtenderOcultado (property)
	private boolean atenderOcultado = false;

	@NotPersistent
	@MemberOrder(sequence = "1")
	@Property(hidden = Where.EVERYWHERE)
	public boolean getAtenderOcultado() {
		return atenderOcultado;
	}

	public void setAtenderOcultado(final boolean atenderOcultado) {
		this.atenderOcultado = atenderOcultado;
	}

	// }}

	// {{ CancelarOcultado (property)
	private boolean cancelarOcultado;

	@NotPersistent
	@MemberOrder(sequence = "1")
	@Property(hidden = Where.EVERYWHERE)
	public boolean getCancelarOcultado() {
		return cancelarOcultado;
	}

	public void setCancelarOcultado(final boolean cancelarOcultado) {
		this.cancelarOcultado = cancelarOcultado;

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

	// METODOS PARA OCULTAR BOTONES DE CAMBIAR ESTADO DE TURNOS

	public boolean hideDisponerTurno() {
		return this.disponerOcultado;
	}

	public boolean hideSolicitarTurno() {
		return this.solicitarOcultado;
	}

	public boolean hideAceptarTurno() {
		return this.aceptarOcultado;
	}

	public boolean hideCancelarTurno() {
		return this.cancelarOcultado;
	}

	public boolean hideAtenderTurno() {
		return this.atenderOcultado;
	}

}
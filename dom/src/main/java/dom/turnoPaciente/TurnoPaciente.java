package dom.turnoPaciente;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;

import dom.paciente.Paciente;

@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
		+ "FROM dom.turnoPaciente.TurnoPaciente "), })
@PersistenceCapable
public class TurnoPaciente {

	public TurnoPaciente() {
		this.turnoDisponible = new TurnoDisponible(this);
		this.turnoSolicitado = new TurnoSolicitado(this);
		this.turnoAceptado = new TurnoAceptado(this);
		this.turnoAtendido = new TurnoAtendido(this);
		this.turnoCancelado = new TurnoCancelado(this);

		this.setIEstadoTurno(this.getTurnoDisponible());
	}

	public String getSituacionDeTurno() {
		return this.iEstadoTurno.nombreEstado();
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

	public TurnoPaciente solicitarTurno() {
		this.iEstadoTurno.solicitarTurno();
		return this;
	}

	// {{ Disponible (property)
	private Boolean disponible;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(final Boolean disponible) {
		this.disponible = disponible;
	}

	// }}

	// {{ IEstadoTurno (property)
	private IEstadoTurno iEstadoTurno;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	@Persistent(extensions = {
			@Extension(vendorName = "datanucleous", key = "mapping-strategy", value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-clases", value = "dom.turnoPaciente.TurnoDisponible"
					+ ",dom.turnoPaciente.TurnoSolicitado"
					+ ",dom.turnoPaciente.TurnoAceptado"
					+ ",dom.turnoPaciente.TurnoAtendido"
					+ ",dom.turnoPaciente.TurnoCancelado") }, columns = {
			@Column(name = "idTurnoDisponible"),
			@Column(name = "idTurnoSolicitado"),
			@Column(name = "idTurnoAceptado"),
			@Column(name = "idTurnoAtendido"),
			@Column(name = "idTurnoCancelado") })
	public IEstadoTurno getIEstadoTurno() {
		return iEstadoTurno;
	}

	void setIEstadoTurno(final IEstadoTurno iEstadoTurno) {
		this.iEstadoTurno = iEstadoTurno;
	}

	// }}

	// {{ TurnoDisponible (property)
	private TurnoDisponible turnoDisponible;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public TurnoDisponible getTurnoDisponible() {
		return turnoDisponible;
	}

	public void setTurnoDisponible(final TurnoDisponible turnoDisponible) {
		this.turnoDisponible = turnoDisponible;
	}

	// }}

	// {{ TurnoSolicitado (property)
	private TurnoSolicitado turnoSolicitado;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "7")
	@Column(allowsNull = "false")
	public TurnoSolicitado getTurnoSolicitado() {
		return turnoSolicitado;
	}

	public void setTurnoSolicitado(final TurnoSolicitado turnoSolicitado) {
		this.turnoSolicitado = turnoSolicitado;
	}

	// }}

	// {{ TurnoAceptado (property)
	private TurnoAceptado turnoAceptado;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	public TurnoAceptado getTurnoAceptado() {
		return turnoAceptado;
	}

	public void setTurnoAceptado(final TurnoAceptado turnoAceptado) {
		this.turnoAceptado = turnoAceptado;
	}

	// }}

	// {{ TurnoAtendido (property)
	private TurnoAtendido turnoAtendido;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "false")
	public TurnoAtendido getTurnoAtendido() {
		return turnoAtendido;
	}

	public void setTurnoAtendido(final TurnoAtendido turnoAtendido) {
		this.turnoAtendido = turnoAtendido;
	}

	// }}

	// {{ TurnoCancelado (property)
	private TurnoCancelado turnoCancelado;

	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public TurnoCancelado getTurnoCancelado() {
		return turnoCancelado;
	}

	public void setTurnoCancelado(final TurnoCancelado turnoCancelado) {
		this.turnoCancelado = turnoCancelado;
	}

	// }}
}
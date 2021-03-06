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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.agendadoctor.AgendaDoctor;
import dom.doctor.Doctor;
import dom.paciente.Paciente;
import dom.turnopaciente.grafico.EstadoTurnoEnum;

@javax.jdo.annotations.Queries({

		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente "),
		@javax.jdo.annotations.Query(name = "traerAtendidosPorPaciente", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE estado2 == 'Atendido' && paciente== :paciente"),
		@javax.jdo.annotations.Query(name = "traerAtendidos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE estado2 == 'Atendido'"),
		@javax.jdo.annotations.Query(name = "traerPorPaciente", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE paciente== :paciente"),
		@javax.jdo.annotations.Query(name = "traerParaCancelar", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE estado2 != 'Cancelado' && estado2 != 'Disponible'"),
		@javax.jdo.annotations.Query(name = "traerParaCancelarPorPaciente", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turnopaciente.TurnoPaciente WHERE estado2 != 'Cancelado' && estado2 != 'Disponible' &&  paciente== :paciente"),
		@javax.jdo.annotations.Query(name = "traerTurnosPacientePorUsuario", language = "JDOQL", value = "SELECT "
				+ " FROM dom.turnopaciente.TurnoPaciente WHERE usuariovinculado == :usuariovinculado"),
		@javax.jdo.annotations.Query(name = "traerPorDoctor", language = "JDOQL", value = "SELECT "
				+ " FROM dom.turnopaciente.TurnoPaciente WHERE doctor == :doctor") })
@DomainObject(autoCompleteRepository = TurnoPacienteServicio.class, autoCompleteAction = "buscarTurno")
@PersistenceCapable
public class TurnoPaciente {

	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy HH:mm");

	public TranslatableString title() {
		return TranslatableString.tr(
				"{nombre}",
				"nombre",
				"Turno de: " + getPaciente().getApellido() + ", "
						+ getPaciente().getNombre() + ". Doctor: "
						+ getDoctor().getApellido() + ". Horario: "
						+ fecha.format(getHorarioTurno().getDia()) + ".");
	}

	public TurnoPaciente() {
		this.disponible = new Disponible(this);
		this.solicitado = new Solicitado(this);
		this.aceptado = new Aceptado(this);
		this.atendido = new Atendido(this);
		this.cancelado = new Cancelado(this);

		this.setEstado(this.getDisponible());
		this.setEstadoGrafico(EstadoTurnoEnum.Disponible);
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
		getHorarioTurno().setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente solicitarTurno(Doctor doctor, Paciente paciente) {
		getEstado().solicitarTurno(doctor, paciente);
		getHorarioTurno().setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente aceptarTurno() {
		getEstado().aceptarTurno();
		getHorarioTurno().setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente atenderTurno() {
		getEstado().atenderTurno();
		getHorarioTurno().setEstado(getEstadoTurno());
		return this;

	}

	public TurnoPaciente cancelarTurno() {
		getEstado().cancelarTurno();
		getHorarioTurno().setEstado(getEstadoTurno());
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

	// {{ EstadoGrafico (property)
	private EstadoTurnoEnum estadoGrafico;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(hidden = Where.ANYWHERE)
	public EstadoTurnoEnum getEstadoGrafico() {
		return estadoGrafico;
	}

	public void setEstadoGrafico(final EstadoTurnoEnum estadoGrafico) {
		this.estadoGrafico = estadoGrafico;
	}

	// }}

	@javax.inject.Inject
	private DomainObjectContainer container;

}
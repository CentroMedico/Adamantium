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

/**
 * Entidad Turno la cual representa a los turnos que va tener disponible el Doctor.
 * 
 * 
 * @author Adamantium
 * @since 01/08/2015
 * @version 1.0.0
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.Doctor;

@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turno.Turno"),

		@javax.jdo.annotations.Query(name = "traerTurnos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turno.Turno"),

		@javax.jdo.annotations.Query(name = "buscarNombre,Nombre", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turno.Turno"
				+ "nombre.indexOf(:parametro) == 0"
				+ " && nombre.indexOf(:parametro) >= 0") })
@PersistenceCapable
public class Agenda {

	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	public TranslatableString title() {
		final SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"dd MMMM YYYY HH:mm");
		// return TranslatableString.tr("{nombre}", "nombre", "Turno.",
		// formatoFecha.format(this.dia));
		return TranslatableString.tr("{nombre}", "nombre", "Turno "
				+ formatoFecha.format(this.getDia()));
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	public String iconName() {
		return "turnos";
	}

	/**
	 * 
	 */

	public Agenda() {
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

	// {{ Dia (property)
	private Date dia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener un dia 
	 * 
	 * @return dia Date
	 */
	public Date getDia() {
		return dia;
	}

	/**
	 * Setea el dia que se va a crear.
	 * 
	 * @param dia
	 *            dia
	 */
	public void setDia(final Date dia) {
		this.dia = dia;
	}

	// }}

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "2")
	@Persistent(mappedBy = "listaTurnos")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener un doctor 
	 * 
	 * @return doctor Doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Setea el Doctor al que se le va asignar los turnos.
	 * 
	 * @param doctor
	 *            doctor
	 */
	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}
	/**
	 * 
	 * @return
	 */
	public Agenda solicitarTurno() {
		this.iEstadoTurno.solicitarTurno();
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hideSolicitarTurno() {
		return true;
		// return this.getIEstadoTurno().ocultarSolicitarTurno();
	}

	// {{ Paciente (property)
	// private Paciente paciente;
	//
	// @MemberOrder(sequence = "3")
	// @Column(allowsNull = "true")
	// public Paciente getPaciente() {
	// return paciente;
	// }
	//
	// public void setPaciente(final Paciente paciente) {
	// this.paciente = paciente;
	// }

	// }}

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

	// @ActionLayout(hidden=Where.EVERYWHERE)
	@PropertyLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	@Persistent(extensions = {
			@Extension(vendorName = "datanucleous", key = "mapping-strategy", value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-clases", value = "dom.turno.TurnoDisponible"
					+ ",dom.turno.TurnoSolicitado"
					+ ",dom.turno.TurnoAceptado"
					+ ",dom.turno.TurnoAtendido" + ",dom.turno.TurnoCancelado") }, columns = {
			@Column(name = "idTurnoDisponible"),
			@Column(name = "idTurnoSolicitado"),
			@Column(name = "idTurnoAceptado"),
			@Column(name = "idTurnoAtendido"),
			@Column(name = "idTurnoCancelado") })
	public IEstadoTurno getIEstadoTurno() {
		return iEstadoTurno;
	}

	public void setIEstadoTurno(final IEstadoTurno iEstadoTurno) {
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
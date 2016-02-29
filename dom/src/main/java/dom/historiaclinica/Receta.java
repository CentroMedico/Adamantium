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

package dom.historiaclinica;

import java.text.SimpleDateFormat;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.Doctor;
import dom.obrasocial.ObraSocial;
import dom.paciente.Paciente;
import dom.turnopaciente.TurnoPaciente;
import dom.vademecum.Vademecum;

@javax.jdo.annotations.Queries({

@javax.jdo.annotations.Query(name = "traerRecetaPorPaciente", language = "JDOQL", value = "SELECT "
		+ "FROM dom.historiaclinica.Receta WHERE paciente == :paciente "), })
@PersistenceCapable
public class Receta {
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */

	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy HH:mm");

	public TranslatableString title() {
		return TranslatableString.tr(
				"{nombre}",
				"nombre",
				"Receta de: " + getPaciente().getApellido() + ", "
						+ getPaciente().getNombre() + ". Del día "
						+ fecha.format(getTurno().getHorarioTurno().getDia()));
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "historia";
	}

	// {{ Paciente (property)
	private Paciente paciente;

	@MemberOrder(sequence = "0")
	@Column(allowsNull = "true")
	@Property(editing = Editing.DISABLED)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	// }}

	// {{ ObraSocial (property)
	private ObraSocial obraSocial;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(final ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	// }}

	// {{ Medicamento (property)
	private Vademecum medicamento;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Vademecum getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(final Vademecum medicamento) {
		this.medicamento = medicamento;
	}

	// }}

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}

	// {{ Turno (property)
	private TurnoPaciente turnoPaciente;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public TurnoPaciente getTurno() {
		return turnoPaciente;
	}

	public void setTurno(final TurnoPaciente turnoPaciente) {
		this.turnoPaciente = turnoPaciente;
	}
	// }}

}
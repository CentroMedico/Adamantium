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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.Doctor;
import dom.obrasocial.ObraSocial;
import dom.paciente.Paciente;
import dom.vademecum.Vademecum;

@PersistenceCapable
public class Receta {
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Receta de: " + this.paciente.getApellido() + ", "
						+ this.paciente.getNombre());
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

	// {{ Medicamento2 (property)
	private Vademecum medicamento2;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Vademecum getMedicamento2() {
		return medicamento2;
	}

	public void setMedicamento2(final Vademecum medicamento2) {
		this.medicamento2 = medicamento2;
	}

	// }}

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}
	// }}

}

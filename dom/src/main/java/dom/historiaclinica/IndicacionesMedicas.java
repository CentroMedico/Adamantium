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
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.Doctor;
import dom.paciente.Paciente;
import dom.vademecum.Vademecum;

@PersistenceCapable
public class IndicacionesMedicas {
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Indicaciones Medicas de: " + this.paciente.getApellido()
						+ ", " + this.paciente.getNombre());
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

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	@Join(column = "indicacionesMedicas_id")
	@Property(editing = Editing.DISABLED)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
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

	// {{ Comotomarlo (property)
	private String comoTomarlo;

	@MemberOrder(sequence = "3")
	@PropertyLayout(multiLine = 5)
	@Column(allowsNull = "true")
	public String getComotomarlo() {
		return comoTomarlo;
	}

	public void setComotomarlo(final String comoTomarlo) {
		this.comoTomarlo = comoTomarlo;
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

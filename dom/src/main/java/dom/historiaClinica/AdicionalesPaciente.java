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
package dom.historiaClinica;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.paciente.Paciente;
@PersistenceCapable
public class AdicionalesPaciente 
{
	
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Adicionales de Paciente: " + this.paciente.getApellido() + ", " + this.paciente.getNombre());
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
	@Property(editing=Editing.DISABLED)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}
	// }}

	
	// {{ EstadoCivil (property)
	private EstadoCivilEnum estadoCivil;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(final EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	// }}

	// {{ Trabajo (property)
	private Boolean trabajo;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public Boolean getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(final Boolean trabajo) {
		this.trabajo = trabajo;
	}

	// }}

	// {{ ObraSocial (property)
	private Boolean obraSocial;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public Boolean getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(final Boolean obraSocial) {
		this.obraSocial = obraSocial;
	}
	// }}
	
	// {{ Educacion (property)
	private EducacionEnum educacion;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public EducacionEnum getEducacion() {
		return educacion;
	}

	public void setEducacion(final EducacionEnum educacion) {
		this.educacion = educacion;
	}
	// }}



}

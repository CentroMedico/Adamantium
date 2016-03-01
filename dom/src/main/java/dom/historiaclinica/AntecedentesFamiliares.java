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

import dom.paciente.Paciente;

@javax.jdo.annotations.Queries({

@javax.jdo.annotations.Query(name = "traerFamiliaresPorPaciente", language = "JDOQL", value = "SELECT "
		+ " FROM dom.historiaclinica.AntecedentesFamiliares WHERE paciente == :paciente ") })
@PersistenceCapable
public class AntecedentesFamiliares {
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Antecedentes Familiares de: " + this.paciente.getApellido()
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

	// {{ Hta (property)
	private Boolean hta;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Boolean getHta() {
		return hta;
	}

	public void setHta(final Boolean hta) {
		this.hta = hta;
	}

	// }}

	// {{ Cardiopatias (property)
	private Boolean cardiopatias;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Boolean getCardiopatias() {
		return cardiopatias;
	}

	public void setCardiopatias(final Boolean cardiopatias) {
		this.cardiopatias = cardiopatias;
	}

	// }}

	// {{ Diabetes (property)
	private Boolean diabetes;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(final Boolean diabetes) {
		this.diabetes = diabetes;
	}

	// }}

	// {{ ACV (property)
	private Boolean acv;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public Boolean getACV() {
		return acv;
	}

	public void setACV(final Boolean acv) {
		this.acv = acv;
	}

	// }}

	// {{ CadeColon (property)
	private Boolean caddeColon;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public Boolean getCadeColon() {
		return caddeColon;
	}

	public void setCadeColon(final Boolean caddeColon) {
		this.caddeColon = caddeColon;
	}

	// }}

	// {{ CadePulmon (property)
	private Boolean cadePulmon;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public Boolean getCadePulmon() {
		return cadePulmon;
	}

	public void setCadePulmon(final Boolean cadePulmon) {
		this.cadePulmon = cadePulmon;
	}

	// }}

	// {{ CadeMama (property)
	private Boolean cadeMama;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public Boolean getCadeMama() {
		return cadeMama;
	}

	public void setCadeMama(final Boolean cadeMama) {
		this.cadeMama = cadeMama;
	}

	// }}

	// {{ ConsumoDrogas (property)
	private Boolean consumoDrogas;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "true")
	public Boolean getConsumoDrogas() {
		return consumoDrogas;
	}

	public void setConsumoDrogas(final Boolean consumoDrogas) {
		this.consumoDrogas = consumoDrogas;
	}

	// }}

	// {{ AbusoAlcohol (property)
	private Boolean abusoAlcohol;

	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public Boolean getAbusoAlcohol() {
		return abusoAlcohol;
	}

	public void setAbusoAlcohol(final Boolean abusoAlcohol) {
		this.abusoAlcohol = abusoAlcohol;
	}

	// }}

	// {{ Depresion (property)
	private Boolean depresion;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "true")
	public Boolean getDepresion() {
		return depresion;
	}

	public void setDepresion(final Boolean depresion) {
		this.depresion = depresion;
	}
	// }}

}
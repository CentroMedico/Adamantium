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
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.paciente.Paciente;

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
	// @Persistent(table = "lista_antecedentesFamiliares", mappedBy =
	// "listaAntecedentesFamiliares")
	// @Join(column = "antecedentesFamiliares_id")
	@Property(editing = Editing.DISABLED)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	// }}

	// {{ Hta (property)
	private boolean hta;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public boolean getHta() {
		return hta;
	}

	public void setHta(final boolean hta) {
		this.hta = hta;
	}

	// }}

	// {{ Cardiopatias (property)
	private boolean cardiopatias;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public boolean getCardiopatias() {
		return cardiopatias;
	}

	public void setCardiopatias(final boolean cardiopatias) {
		this.cardiopatias = cardiopatias;
	}

	// }}

	// {{ Diabetes (property)
	private boolean diabetes;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(final boolean diabetes) {
		this.diabetes = diabetes;
	}

	// }}

	// {{ ACV (property)
	private boolean acv;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public boolean getACV() {
		return acv;
	}

	public void setACV(final boolean acv) {
		this.acv = acv;
	}

	// }}

	// {{ CadeColon (property)
	private boolean caddeColon;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public boolean getCadeColon() {
		return caddeColon;
	}

	public void setCadeColon(final boolean caddeColon) {
		this.caddeColon = caddeColon;
	}

	// }}

	// {{ CadePulmon (property)
	private boolean cadePulmon;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public boolean getCadePulmon() {
		return cadePulmon;
	}

	public void setCadePulmon(final boolean cadePulmon) {
		this.cadePulmon = cadePulmon;
	}

	// }}

	// {{ CadeMama (property)
	private boolean cadeMama;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public boolean getCadeMama() {
		return cadeMama;
	}

	public void setCadeMama(final boolean cadeMama) {
		this.cadeMama = cadeMama;
	}

	// }}

	// {{ ConsumoDrogas (property)
	private boolean consumoDrogas;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "true")
	public boolean getConsumoDrogas() {
		return consumoDrogas;
	}

	public void setConsumoDrogas(final boolean consumoDrogas) {
		this.consumoDrogas = consumoDrogas;
	}

	// }}

	// {{ AbusoAlcohol (property)
	private boolean abusoAlcohol;

	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public boolean getAbusoAlcohol() {
		return abusoAlcohol;
	}

	public void setAbusoAlcohol(final boolean abusoAlcohol) {
		this.abusoAlcohol = abusoAlcohol;
	}

	// }}

	// {{ Depresion (property)
	private boolean depresion;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "true")
	public boolean getDepresion() {
		return depresion;
	}

	public void setDepresion(final boolean depresion) {
		this.depresion = depresion;
	}
	// }}

}

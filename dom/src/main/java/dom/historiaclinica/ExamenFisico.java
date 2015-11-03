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
public class ExamenFisico {
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Examen Fisico de: " + this.paciente.getApellido() + ", "
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
	// @Persistent(table = "lista_examenFisico", mappedBy = "listaExamenFisico")
	// @Join(column = "examenFisico_id")
	@Property(editing = Editing.DISABLED)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	// }}

	// {{ Piel (property)
	private String piel;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getPiel() {
		return piel;
	}

	public void setPiel(final String piel) {
		this.piel = piel;
	}

	// }}

	// {{ Lentes (property)
	private String lentes;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getLentes() {
		return lentes;
	}

	public void setLentes(final String lentes) {
		this.lentes = lentes;
	}

	// }}

	// {{ AgudezaVisual (property)
	private String agudezaVisual;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getAgudezaVisual() {
		return agudezaVisual;
	}

	public void setAgudezaVisual(final String agudezaVisual) {
		this.agudezaVisual = agudezaVisual;
	}

	// }}

	// {{ Oidos (property)
	private String oidos;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getOidos() {
		return oidos;
	}

	public void setOidos(final String oidos) {
		this.oidos = oidos;
	}

	// }}

	// {{ Dentadura (property)
	private String dentadura;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public String getDentadura() {
		return dentadura;
	}

	public void setDentadura(final String dentadura) {
		this.dentadura = dentadura;
	}

	// }}

	// {{ Pulmones (property)
	private String pulmones;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public String getPulmones() {
		return pulmones;
	}

	public void setPulmones(final String pulmones) {
		this.pulmones = pulmones;
	}

	// }}

	// {{ Corazon (property)
	private String corazon;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public String getCorazon() {
		return corazon;
	}

	public void setCorazon(final String corazon) {
		this.corazon = corazon;
	}

	// }}
	// {{ Abdomen (property)
	private String abdomen;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "true")
	public String getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(final String abdomen) {
		this.abdomen = abdomen;
	}

	// }}

	// {{ Genitales (property)
	private String genitales;

	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public String getGenitales() {
		return genitales;
	}

	public void setGenitales(final String genitales) {
		this.genitales = genitales;
	}

	// }}

	// {{ Mamas (property)
	private String mamas;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "true")
	public String getMamas() {
		return mamas;
	}

	public void setMamas(final String mamas) {
		this.mamas = mamas;
	}

	// }}

	// {{ Talla (property)
	private String talla;

	@MemberOrder(sequence = "11")
	@Column(allowsNull = "true")
	public String getTalla() {
		return talla;
	}

	public void setTalla(final String talla) {
		this.talla = talla;
	}

	// }}

	// {{ Peso (property)
	private String peso;

	@MemberOrder(sequence = "12")
	@Column(allowsNull = "true")
	public String getPeso() {
		return peso;
	}

	public void setPeso(final String peso) {
		this.peso = peso;
	}

	// }}

	// {{ Temperatura (property)
	private String temperatura;

	@MemberOrder(sequence = "13")
	@Column(allowsNull = "true")
	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(final String temperatura) {
		this.temperatura = temperatura;
	}

	// }}

	// {{ FrecuenciaCardiaca (property)
	private String frecuenciaCardiaca;

	@MemberOrder(sequence = "14")
	@Column(allowsNull = "true")
	public String getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	public void setFrecuenciaCardiaca(final String frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

	// }}

	// {{ FrecuenciaRespiratoria (property)
	private String frecuenciaRespiratoria;

	@MemberOrder(sequence = "15")
	@Column(allowsNull = "true")
	public String getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}

	public void setFrecuenciaRespiratoria(final String frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}

	// }}

	// {{ TensionArterial (property)
	private String tensionArterial;

	@MemberOrder(sequence = "16")
	@Column(allowsNull = "true")
	public String getTensionArterial() {
		return tensionArterial;
	}

	public void setTensionArterial(final String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}

	// }}

	// {{ EstadoGeneral (property)
	private String estadoGeneral;

	@MemberOrder(sequence = "17")
	@Column(allowsNull = "true")
	public String getEstadoGeneral() {
		return estadoGeneral;
	}

	public void setEstadoGeneral(final String estadoGeneral) {
		this.estadoGeneral = estadoGeneral;
	}
	// }}

}

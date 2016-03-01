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

		@javax.jdo.annotations.Query(name = "traerAdicionalesPorPaciente", language = "JDOQL", value = "SELECT "
				+ " FROM dom.historiaclinica.AntecedentesPersonales WHERE paciente == :paciente "),
		@javax.jdo.annotations.Query(name = "buscarRepetidos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.historiaclinica.AntecedentesPersonales "
				+ " WHERE paciente ==:paciente") })
@PersistenceCapable
public class AntecedentesPersonales {

	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Antecedentes Personales de: " + this.paciente.getApellido()
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

	// {{ Tabaquismo (property)
	private Boolean tabaquismo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public Boolean getTabaquismo() {
		return tabaquismo;
	}

	public void setTabaquismo(final Boolean tabaquismo) {
		this.tabaquismo = tabaquismo;
	}

	// }}

	// {{ DesdequeEdad (property)
	private String desdequeedad;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getDesdequeEdad() {
		return desdequeedad;
	}

	public void setDesdequeEdad(final String desdequeedad) {
		this.desdequeedad = desdequeedad;
	}

	// }}
	// {{ CantidadCigarrillos (property)
	private String cantidadCigarrillos;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public String getCantidadCigarrillos() {
		return cantidadCigarrillos;
	}

	public void setCantidadCigarrillos(final String cantidadCigarrillos) {
		this.cantidadCigarrillos = cantidadCigarrillos;
	}

	// }}

	// {{ Alcohol (property)
	private Boolean alcohol;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public Boolean getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(final Boolean alcohol) {
		this.alcohol = alcohol;
	}

	// }}

	// {{ CriticasporTomar (property)
	private Boolean criticasporTomar;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public Boolean getCriticasporTomar() {
		return criticasporTomar;
	}

	public void setCriticasporTomar(final Boolean criticasporTomar) {
		this.criticasporTomar = criticasporTomar;
	}

	// }}

	// {{ TomaporlaMañana (property)
	private Boolean tomaporlaMañana;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public Boolean getTomaporlaMañana() {
		return tomaporlaMañana;
	}

	public void setTomaporlaMañana(final Boolean tomaporlaMañana) {
		this.tomaporlaMañana = tomaporlaMañana;
	}

	// }}

	// {{ Drogas (property)
	private Boolean drogas;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public Boolean getDrogas() {
		return drogas;
	}

	public void setDrogas(final Boolean drogas) {
		this.drogas = drogas;
	}

	// }}

	// {{ TipoDroga (property)
	private String tipoDroga;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	public String getTipoDroga() {
		return tipoDroga;
	}

	public void setTipoDroga(final String tipoDroga) {
		this.tipoDroga = tipoDroga;
	}

	// }}

	// {{ ActividadFisica (property)
	private Boolean actividadFisica;

	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public Boolean getActividadFisica() {
		return actividadFisica;
	}

	public void setActividadFisica(final Boolean actividadFisica) {
		this.actividadFisica = actividadFisica;
	}

	// }}

	// {{ TipoActividad (property)
	private String tipoActivida;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getTipoActividad() {
		return tipoActivida;
	}

	public void setTipoActividad(final String tipoActivida) {
		this.tipoActivida = tipoActivida;
	}

	// }}

	// {{ HTA (property)
	private Boolean hta;

	@MemberOrder(sequence = "11")
	@Column(allowsNull = "true")
	public Boolean getHTA() {
		return hta;
	}

	public void setHTA(final Boolean hta) {
		this.hta = hta;
	}

	// }}

	// {{ Diabetes (property)
	private Boolean diabetes;

	@MemberOrder(sequence = "12")
	@Column(allowsNull = "true")
	public Boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(final Boolean diabetes) {
		this.diabetes = diabetes;
	}

	// }}

	// {{ EnfermedadCoronaria (property)
	private Boolean enfermedadCoronaria;

	@MemberOrder(sequence = "13")
	@Column(allowsNull = "true")
	public Boolean getEnfermedadCoronaria() {
		return enfermedadCoronaria;
	}

	public void setEnfermedadCoronaria(final Boolean enfermedadCoronaria) {
		this.enfermedadCoronaria = enfermedadCoronaria;
	}

	// }}

	// {{ ACV (property)
	private Boolean acv;

	@MemberOrder(sequence = "14")
	@Column(allowsNull = "true")
	public Boolean getACV() {
		return acv;
	}

	public void setACV(final Boolean acv) {
		this.acv = acv;
	}

	// }}

	// {{ EPOC (property)
	private Boolean epoc;

	@MemberOrder(sequence = "15")
	@Column(allowsNull = "true")
	public Boolean getEPOC() {
		return epoc;
	}

	public void setEPOC(final Boolean epoc) {
		this.epoc = epoc;
	}

	// }}

	// {{ Alergia (property)
	private Boolean alergia;

	@MemberOrder(sequence = "16")
	@Column(allowsNull = "true")
	public Boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(final Boolean alergia) {
		this.alergia = alergia;
	}

	// }}

	// {{ EnfermedadReumatica (property)
	private Boolean enfermedadReumatica;

	@MemberOrder(sequence = "17")
	@Column(allowsNull = "true")
	public Boolean getEnfermedadReumatica() {
		return enfermedadReumatica;
	}

	public void setEnfermedadReumatica(final Boolean enfermedadReumatica) {
		this.enfermedadReumatica = enfermedadReumatica;
	}

	// }}

	// {{ EnfermedadOncologica (property)
	private Boolean enfermedadOncologica;

	@MemberOrder(sequence = "18")
	@Column(allowsNull = "true")
	public Boolean getEnfermedadOncologica() {
		return enfermedadOncologica;
	}

	public void setEnfermedadOncologica(final Boolean enfermedadOncologica) {
		this.enfermedadOncologica = enfermedadOncologica;
	}

	// }}

	// {{ TBC (property)
	private Boolean tbc;

	@MemberOrder(sequence = "19")
	@Column(allowsNull = "false")
	public Boolean getTBc() {
		return tbc;
	}

	public void setTBc(final Boolean tbc) {
		this.tbc = tbc;
	}

	// }}

	// {{ VIH (property)
	private Boolean vih;

	@MemberOrder(sequence = "20")
	@Column(allowsNull = "true")
	public Boolean getVIH() {
		return vih;
	}

	public void setVIH(final Boolean vih) {
		this.vih = vih;
	}

	// }}

	// {{ Chagas (property)
	private Boolean chagas;

	@MemberOrder(sequence = "21")
	@Column(allowsNull = "true")
	public Boolean getChagas() {
		return chagas;
	}

	public void setChagas(final Boolean chagas) {
		this.chagas = chagas;
	}

	// }}

	// {{ ITS (property)
	private Boolean its;

	@MemberOrder(sequence = "22")
	@Column(allowsNull = "true")
	public Boolean getITS() {
		return its;
	}

	public void setITS(final Boolean its) {
		this.its = its;
	}

	// }}

	// {{ Neurologicos (property)
	private Boolean neurologicos;

	@MemberOrder(sequence = "23")
	@Column(allowsNull = "true")
	public Boolean getNeurologicos() {
		return neurologicos;
	}

	public void setNeurologicos(final Boolean neurologicos) {
		this.neurologicos = neurologicos;
	}

	// }}

	// {{ Tranfuciones (property)
	private Boolean transfuciones;

	@MemberOrder(sequence = "24")
	@Column(allowsNull = "true")
	public Boolean getTranfuciones() {
		return transfuciones;
	}

	public void setTranfuciones(final Boolean transfuciones) {
		this.transfuciones = transfuciones;
	}
	// }}

}
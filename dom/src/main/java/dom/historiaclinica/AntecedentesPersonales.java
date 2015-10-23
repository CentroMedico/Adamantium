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

@PersistenceCapable
public class AntecedentesPersonales {
	
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Antecedentes Personales de: " + this.paciente.getApellido() + ", " + this.paciente.getNombre());
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


	// {{ Tabaquismo (property)
	private boolean tabaquismo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public boolean getTabaquismo() {
		return tabaquismo;
	}

	public void setTabaquismo(final boolean tabaquismo) {
		this.tabaquismo = tabaquismo;
	}

	// }}

	// {{ DesdequeEdad (property)
	private int desdequeedad;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public int getDesdequeEdad() {
		return desdequeedad;
	}
	
	public void setDesdequeEdad(final int desdequeedad) {
		this.desdequeedad = desdequeedad;
	}

	// }}
	// {{ CantidadCigarrillos (property)
	private int cantidadCigarrillos;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public int getCantidadCigarrillos() {
		return cantidadCigarrillos;
	}

	public void setCantidadCigarrillos(final int cantidadCigarrillos) {
		this.cantidadCigarrillos = cantidadCigarrillos;
	}

	// }}

	// {{ Alcohol (property)
	private boolean alcohol;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public boolean getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(final boolean alcohol) {
		this.alcohol = alcohol;
	}

	// }}

	// {{ CriticasporTomar (property)
	private boolean criticasporTomar;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public boolean getCriticasporTomar() {
		return criticasporTomar;
	}

	public void setCriticasporTomar(final boolean criticasporTomar) {
		this.criticasporTomar = criticasporTomar;
	}

	// }}

	// {{ TomaporlaMañana (property)
	private boolean tomaporlaMañana;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public boolean getTomaporlaMañana() {
		return tomaporlaMañana;
	}

	public void setTomaporlaMañana(final boolean tomaporlaMañana) {
		this.tomaporlaMañana = tomaporlaMañana;
	}

	// }}

	// {{ Drogas (property)
	private boolean drogas;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public boolean getDrogas() {
		return drogas;
	}

	public void setDrogas(final boolean drogas) {
		this.drogas = drogas;
	}

	// }}

	// {{ TipoDroga (property)
	private String tipoDroga;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "true")
	public String getTipoDroga() {
		return tipoDroga;
	}

	public void setTipoDroga(final String tipoDroga) {
		this.tipoDroga = tipoDroga;
	}

	// }}

	// {{ ActividadFisica (property)
	private boolean actividadFisica;

	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public boolean getActividadFisica() {
		return actividadFisica;
	}

	public void setActividadFisica(final boolean actividadFisica) {
		this.actividadFisica = actividadFisica;
	}

	// }}

	// {{ TipoActividad (property)
	private String tipoActivida;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "true")
	public String getTipoActividad() {
		return tipoActivida;
	}

	public void setTipoActividad(final String tipoActivida) {
		this.tipoActivida = tipoActivida;
	}

	// }}

	// {{ HTA (property)
	private boolean hta;

	@MemberOrder(sequence = "11")
	@Column(allowsNull = "true")
	public boolean getHTA() {
		return hta;
	}

	public void setHTA(final boolean hta) {
		this.hta = hta;
	}

	// }}

	// {{ Diabetes (property)
	private boolean diabetes;

	@MemberOrder(sequence = "12")
	@Column(allowsNull = "true")
	public boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(final boolean diabetes) {
		this.diabetes = diabetes;
	}

	// }}

	// {{ EnfermedadCoronaria (property)
	private boolean enfermedadCoronaria;

	@MemberOrder(sequence = "13")
	@Column(allowsNull = "true")
	public boolean getEnfermedadCoronaria() {
		return enfermedadCoronaria;
	}

	public void setEnfermedadCoronaria(final boolean enfermedadCoronaria) {
		this.enfermedadCoronaria = enfermedadCoronaria;
	}

	// }}

	// {{ ACV (property)
	private boolean acv;

	@MemberOrder(sequence = "14")
	@Column(allowsNull = "true")
	public boolean getACV() {
		return acv;
	}

	public void setACV(final boolean acv) {
		this.acv = acv;
	}

	// }}

	// {{ EPOC (property)
	private boolean epoc;

	@MemberOrder(sequence = "15")
	@Column(allowsNull = "true")
	public boolean getEPOC() {
		return epoc;
	}

	public void setEPOC(final boolean epoc) {
		this.epoc = epoc;
	}

	// }}

	// {{ Alergia (property)
	private boolean alergia;

	@MemberOrder(sequence = "16")
	@Column(allowsNull = "true")
	public boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(final boolean alergia) {
		this.alergia = alergia;
	}

	// }}

	// {{ EnfermedadReumatica (property)
	private boolean enfermedadReumatica;

	@MemberOrder(sequence = "17")
	@Column(allowsNull = "true")
	public boolean getEnfermedadReumatica() {
		return enfermedadReumatica;
	}

	public void setEnfermedadReumatica(final boolean enfermedadReumatica) {
		this.enfermedadReumatica = enfermedadReumatica;
	}

	// }}

	// {{ EnfermedadOncologica (property)
	private boolean enfermedadOncologica;

	@MemberOrder(sequence = "18")
	@Column(allowsNull = "true")
	public boolean getEnfermedadOncologica() {
		return enfermedadOncologica;
	}

	public void setEnfermedadOncologica(final boolean enfermedadOncologica) {
		this.enfermedadOncologica = enfermedadOncologica;
	}

	// }}

	// {{ TBC (property)
	private boolean tbc;

	@MemberOrder(sequence = "19")
	@Column(allowsNull = "true")
	public boolean getTBC() {
		return tbc;
	}

	public void setTBC(final boolean tbc) {
		this.tbc = tbc;
	}

	// }}

	// {{ VIH (property)
	private boolean vih;

	@MemberOrder(sequence = "20")
	@Column(allowsNull = "true")
	public boolean getVIH() {
		return vih;
	}

	public void setVIH(final boolean vih) {
		this.vih = vih;
	}

	// }}

	// {{ Chagas (property)
	private boolean chagas;

	@MemberOrder(sequence = "21")
	@Column(allowsNull = "true")
	public boolean getChagas() {
		return chagas;
	}

	public void setChagas(final boolean chagas) {
		this.chagas = chagas;
	}

	// }}

	// {{ ITS (property)
	private boolean its;

	@MemberOrder(sequence = "22")
	@Column(allowsNull = "true")
	public boolean getITS() {
		return its;
	}

	public void setITS(final boolean its) {
		this.its = its;
	}

	// }}

	// {{ Neurologicos (property)
	private boolean neurologicos;

	@MemberOrder(sequence = "23")
	@Column(allowsNull = "true")
	public boolean getNeurologicos() {
		return neurologicos;
	}

	public void setNeurologicos(final boolean neurologicos) {
		this.neurologicos = neurologicos;
	}

	// }}

	// {{ Tranfuciones (property)
	private boolean transfuciones;

	@MemberOrder(sequence = "24")
	@Column(allowsNull = "true")
	public boolean getTranfuciones() {
		return transfuciones;
	}

	public void setTranfuciones(final boolean transfuciones) {
		this.transfuciones = transfuciones;
	}
	// }}

}
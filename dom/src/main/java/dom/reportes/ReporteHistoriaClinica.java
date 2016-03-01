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
package dom.reportes;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteHistoriaClinica {
	private String paciente;
	private String estadoCivil;
	private String trabajo;
	private String obraSocial;
	private String educacion;
	// Antecedentes Personales
	private String tabaquismo;
	private String edadqueempezo;
	private String cantidaddeCigarrillos;
	private String alchool;
	private String criticas;
	private String tomaporlaMañana;
	private String drogas;
	private String tipoDrogas;
	private String actividad;
	private String tipoActividad;
	private String hta;
	private String diabetes;
	private String coronaria;
	private String acv;
	private String epoc;
	private String alergias;
	private String reumatica;
	private String oncologicas;
	private String tbc;
	private String hiv;
	private String chagas;
	private String its;
	private String neurologicas;
	private String transfuciones;
	private String antecedentes;
	// Antecedentes Familiares

	private String hta1;
	private String cardiopatias;
	private String diabetes1;
	private String acv1;
	private String cancerdeColon;
	private String cancerdePulmon;
	private String cancerdeMama;
	private String consumodeDrogas;
	private String abusodeAlchool;
	private String depresion;
	// Examen Fisico.

	private String piel;
	private String utilizalentes;
	private String agudezaVisual;
	private String oidos;
	private String dentadura;
	private String pulmones;
	private String corazon;
	private String abdomen;
	private String genitales;
	private String mamas;
	private String altura;
	private String peso;
	private String temperaturaCorporal;
	private String frecuenciaCardiaca;
	private String frecuenciaRespiratoria;
	private String tensionArterial;
	private String estadoGeneral;

	private String dni;
	private String fechanac;
	private String numCarnet;
	private String Sexo;

	@MemberOrder(sequence = "1")
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@MemberOrder(sequence = "1")
	public String getFechanac() {
		return fechanac;
	}

	public void setFechanac(String fechanac) {
		this.fechanac = fechanac;
	}

	@MemberOrder(sequence = "1")
	public String getNumCarnet() {
		return numCarnet;
	}

	public void setNumCarnet(String numCarnet) {
		this.numCarnet = numCarnet;
	}

	@MemberOrder(sequence = "1")
	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	@MemberOrder(sequence = "1")
	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	@MemberOrder(sequence = "1")
	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@MemberOrder(sequence = "1")
	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	@MemberOrder(sequence = "1")
	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	@MemberOrder(sequence = "1")
	public String getEducacion() {
		return educacion;
	}

	public void setEducacion(String educacion) {
		this.educacion = educacion;
	}

	@MemberOrder(sequence = "1")
	public String getTabaquismo1() {
		return tabaquismo;
	}

	public void setTabaquismo1(String tabaquismo) {
		this.tabaquismo = tabaquismo;
	}

	@MemberOrder(sequence = "1")
	public String getEdadqueempezo() {
		return edadqueempezo;
	}

	public void setEdadqueempezo(String edadqueempezo) {
		this.edadqueempezo = edadqueempezo;
	}

	@MemberOrder(sequence = "1")
	public String getCantidaddeCigarrillos() {
		return cantidaddeCigarrillos;
	}

	public void setCantidaddeCigarrillos(String cantidaddeCigarrillos) {
		this.cantidaddeCigarrillos = cantidaddeCigarrillos;
	}

	@MemberOrder(sequence = "1")
	public String getAlchool() {
		return alchool;
	}

	public void setAlchool(String alchool) {
		this.alchool = alchool;
	}

	@MemberOrder(sequence = "1")
	public String getCriticas() {
		return criticas;
	}

	public void setCriticas(String criticas) {
		this.criticas = criticas;
	}

	@MemberOrder(sequence = "1")
	public String getTomaporlaMañana() {
		return tomaporlaMañana;
	}

	public void setTomaporlaMañana(String tomaporlaMañana) {
		this.tomaporlaMañana = tomaporlaMañana;
	}

	@MemberOrder(sequence = "1")
	public String getDrogas() {
		return drogas;
	}

	public void setDrogas(String drogas) {
		this.drogas = drogas;
	}

	@MemberOrder(sequence = "1")
	public String getTipoDrogas() {
		return tipoDrogas;
	}

	public void setTipoDrogas(String tipoDrogas) {
		this.tipoDrogas = tipoDrogas;
	}

	@MemberOrder(sequence = "1")
	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	@MemberOrder(sequence = "1")
	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	@MemberOrder(sequence = "1")
	public String getHta() {
		return hta;
	}

	public void setHta(String hta) {
		this.hta = hta;
	}

	@MemberOrder(sequence = "1")
	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	@MemberOrder(sequence = "1")
	public String getCoronaria() {
		return coronaria;
	}

	public void setCoronaria(String coronaria) {
		this.coronaria = coronaria;
	}

	@MemberOrder(sequence = "1")
	public String getAcv() {
		return acv;
	}

	public void setAcv(String acv) {
		this.acv = acv;
	}

	@MemberOrder(sequence = "1")
	public String getEpoc() {
		return epoc;
	}

	public void setEpoc(String epoc) {
		this.epoc = epoc;
	}

	@MemberOrder(sequence = "1")
	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	@MemberOrder(sequence = "1")
	public String getReumatica() {
		return reumatica;
	}

	public void setReumatica(String reumatica) {
		this.reumatica = reumatica;
	}

	@MemberOrder(sequence = "1")
	public String getOncologicas() {
		return oncologicas;
	}

	public void setOncologicas(String oncologicas) {
		this.oncologicas = oncologicas;
	}

	@MemberOrder(sequence = "1")
	public String getTbc() {
		return tbc;
	}

	public void setTbc(String tbc) {
		this.tbc = tbc;
	}

	@MemberOrder(sequence = "1")
	public String getHiv() {
		return hiv;
	}

	public void setHiv(String hiv) {
		this.hiv = hiv;
	}

	@MemberOrder(sequence = "1")
	public String getChagas() {
		return chagas;
	}

	public void setChagas(String chagas) {
		this.chagas = chagas;
	}

	@MemberOrder(sequence = "1")
	public String getIts() {
		return its;
	}

	public void setIts(String its) {
		this.its = its;
	}

	@MemberOrder(sequence = "1")
	public String getNeurologicas() {
		return neurologicas;
	}

	public void setNeurologicas(String neurologicas) {
		this.neurologicas = neurologicas;
	}

	@MemberOrder(sequence = "1")
	public String getTransfuciones() {
		return transfuciones;
	}

	public void setTransfuciones(String transfuciones) {
		this.transfuciones = transfuciones;
	}

	@MemberOrder(sequence = "1")
	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	@MemberOrder(sequence = "1")
	public String getHta1() {
		return hta1;
	}

	public void setHta1(String hta1) {
		this.hta1 = hta1;
	}

	@MemberOrder(sequence = "1")
	public String getCardiopatias() {
		return cardiopatias;
	}

	public void setCardiopatias(String cardiopatias) {
		this.cardiopatias = cardiopatias;
	}

	@MemberOrder(sequence = "1")
	public String getDiabetes1() {
		return diabetes1;
	}

	public void setDiabetes1(String diabetes1) {
		this.diabetes1 = diabetes1;
	}

	@MemberOrder(sequence = "1")
	public String getAcv1() {
		return acv1;
	}

	public void setAcv1(String acv1) {
		this.acv1 = acv1;
	}

	@MemberOrder(sequence = "1")
	public String getCancerdeColon() {
		return cancerdeColon;
	}

	public void setCancerdeColon(String cancerdeColon) {
		this.cancerdeColon = cancerdeColon;
	}

	@MemberOrder(sequence = "1")
	public String getCancerdePulmon() {
		return cancerdePulmon;
	}

	public void setCancerdePulmon(String cancerdePulmon) {
		this.cancerdePulmon = cancerdePulmon;
	}

	@MemberOrder(sequence = "1")
	public String getCancerdeMama() {
		return cancerdeMama;
	}

	public void setCancerdeMama(String cancerdeMama) {
		this.cancerdeMama = cancerdeMama;
	}

	@MemberOrder(sequence = "1")
	public String getConsumodeDrogas() {
		return consumodeDrogas;
	}

	public void setConsumodeDrogas(String consumodeDrogas) {
		this.consumodeDrogas = consumodeDrogas;
	}

	@MemberOrder(sequence = "1")
	public String getAbusodeAlchool() {
		return abusodeAlchool;
	}

	public void setAbusodeAlchool(String abusodeAlchool) {
		this.abusodeAlchool = abusodeAlchool;
	}

	@MemberOrder(sequence = "1")
	public String getDepresion() {
		return depresion;
	}

	public void setDepresion(String depresion) {
		this.depresion = depresion;
	}

	@MemberOrder(sequence = "1")
	public String getPiel() {
		return piel;
	}

	public void setPiel(String piel) {
		this.piel = piel;
	}

	@MemberOrder(sequence = "1")
	public String getUtilizalentes() {
		return utilizalentes;
	}

	public void setUtilizalentes(String utilizalentes) {
		this.utilizalentes = utilizalentes;
	}

	@MemberOrder(sequence = "1")
	public String getAgudezaVisual() {
		return agudezaVisual;
	}

	public void setAgudezaVisual(String agudezaVisual) {
		this.agudezaVisual = agudezaVisual;
	}

	@MemberOrder(sequence = "1")
	public String getOidos() {
		return oidos;
	}

	public void setOidos(String oidos) {
		this.oidos = oidos;
	}

	@MemberOrder(sequence = "1")
	public String getDentadura() {
		return dentadura;
	}

	public void setDentadura(String dentadura) {
		this.dentadura = dentadura;
	}

	@MemberOrder(sequence = "1")
	public String getPulmones() {
		return pulmones;
	}

	public void setPulmones(String pulmones) {
		this.pulmones = pulmones;
	}

	@MemberOrder(sequence = "1")
	public String getCorazon() {
		return corazon;
	}

	public void setCorazon(String corazon) {
		this.corazon = corazon;
	}

	@MemberOrder(sequence = "1")
	public String getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(String abdomen) {
		this.abdomen = abdomen;
	}

	@MemberOrder(sequence = "1")
	public String getGenitales() {
		return genitales;
	}

	public void setGenitales(String genitales) {
		this.genitales = genitales;
	}

	@MemberOrder(sequence = "1")
	public String getMamas() {
		return mamas;
	}

	public void setMamas(String mamas) {
		this.mamas = mamas;
	}

	@MemberOrder(sequence = "1")
	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	@MemberOrder(sequence = "1")
	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	@MemberOrder(sequence = "1")
	public String getTemperaturaCorporal() {
		return temperaturaCorporal;
	}

	public void setTemperaturaCorporal(String temperaturaCorporal) {
		this.temperaturaCorporal = temperaturaCorporal;
	}

	@MemberOrder(sequence = "1")
	public String getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

	@MemberOrder(sequence = "1")
	public String getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}

	public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}

	@MemberOrder(sequence = "1")
	public String getTensionArterial() {
		return tensionArterial;
	}

	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}

	@MemberOrder(sequence = "1")
	public String getEstadoGeneral() {
		return estadoGeneral;
	}

	public void setEstadoGeneral(String estadoGeneral) {
		this.estadoGeneral = estadoGeneral;
	}

}

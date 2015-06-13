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
package dom.Paciente;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.Estado.EstadoEnum;
import dom.GrupoSanguineo.GrupoSanguineoEnum;
import dom.Persona.Persona;

//Primera Estrategia: Una tabla por cada clase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Paciente extends Persona {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Paciente");
	}

	public String iconName() {
		return "paciente";
	}

	// {{ Legajo (property)
	private int legajo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	// @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence =
	// "LegajoPaciente")
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(final int legajo) {
		this.legajo = legajo;
	}

	// }}

	// {{ Estado (property)
	private EstadoEnum estado;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}

	// {{ GrupoSanguineo (property)
	private GrupoSanguineoEnum grupoSanguineo;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public GrupoSanguineoEnum getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(final GrupoSanguineoEnum grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	// }}

	// {{ ObraSocial (property)
	private String obraSocial;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(final String obraSocial) {
		this.obraSocial = obraSocial;
	}

	// }}

	public String validateObraSocial(String obr) {

		if (obr.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos incorrectos, por favor vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ NumCarnet (property)
	private String numCarnet;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public String getNumCarnet() {
		return numCarnet;
	}

	public void setNumCarnet(final String numCarnet) {
		this.numCarnet = numCarnet;
	}

	// }}

	public String validateNumCarnet(String car) {

		if (car.matches("[0-9,-]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ CoberturaMedica (property)
	private String cobertura;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getCoberturaMedica() {
		return cobertura;
	}

	public void setCoberturaMedica(final String cobertura) {
		this.cobertura = cobertura;
	}

	// }}

	public String validateCoberturaMedica(String cob) {

		if (cob.matches("[a-z, A-Z,0-9,-]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	@Inject
	private PacienteServicio pacienteServicio;
	@Inject
	private DomainObjectContainer container;

}
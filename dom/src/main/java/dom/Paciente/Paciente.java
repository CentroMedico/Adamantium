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

import javax.jdo.annotations.PersistenceCapable;


import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.Estado.EstadoEnum;
import dom.GrupoSanguineo.GrupoSanguineoEnum;
import dom.Persona.Persona;

/**
 * Entidad Paciente la cual representa a cualquier persona que se haga atender
 * en el centro medico. Extiende de la clase Persona.
 * 
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
// Primera Estrategia: Una tabla por cada clase
// @PersistenceCapable(identityType = IdentityType.DATASTORE)
// @Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
// Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Paciente extends Persona {
	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Paciente");
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "paciente";
	}

	// {{ Legajo (property)
	private int legajo;

	/**
	 * Pemite obtener el legajo de un Paciente
	 * 
	 * @return legajo int
	 */
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	// @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence =
	// "LegajoPaciente")
	public int getLegajo() {
		return legajo;
	}

	/**
	 * Setea el legajo que se va a crear.
	 * 
	 * @param legajo
	 *            legajo
	 */
	public void setLegajo(final int legajo) {
		this.legajo = legajo;
	}

	// }}

	// {{ Estado (property)
	private EstadoEnum estado;

	/**
	 * Pemite obtener el estado de un Paciente
	 * 
	 * @return estado String
	 */
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	/**
	 * Setea el estado que se va a crear.
	 * 
	 * @param estado
	 *            estado
	 */
	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}

	// {{ GrupoSanguineo (property)
	private GrupoSanguineoEnum grupoSanguineo;

	/**
	 * Pemite obtener el grupoSanguineo de un Paciente
	 * 
	 * @return grupoSanguineo GrupoSanguineoEnum
	 */
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public GrupoSanguineoEnum getGrupoSanguineo() {
		return grupoSanguineo;
	}

	/**
	 * Setea el grupoSanguineo que se va a crear.
	 * 
	 * @param grupoSanguineo
	 *            grupoSanguineo
	 */
	public void setGrupoSanguineo(final GrupoSanguineoEnum grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	// }}

	// {{ ObraSocial (property)
	private String obraSocial;

	/**
	 * Pemite obtener la obraSocial de un Paciente
	 * 
	 * @return obraSocial String
	 */
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public String getObraSocial() {
		return obraSocial;
	}

	/**
	 * Setea la obraSocial que se va a crear.
	 * 
	 * @param obraSocial
	 *            obraSocial
	 */
	public void setObraSocial(final String obraSocial) {
		this.obraSocial = obraSocial;
	}

	// }}
	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param obr
	 * @return String
	 */
	public String validateObraSocial(String obr) {

		if (obr.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos incorrectos, por favor vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ NumCarnet (property)
	private String numCarnet;

	/**
	 * Pemite obtener el numCarnet de un Paciente
	 * 
	 * @return numCarnet String
	 */
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public String getNumCarnet() {
		return numCarnet;
	}

	/**
	 * Setea el numCarnet que se va a crear.
	 * 
	 * @param numCarnet
	 *            numCarnet
	 */
	public void setNumCarnet(final String numCarnet) {
		this.numCarnet = numCarnet;
	}

	// }}
	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param car
	 * @return String
	 */
	public String validateNumCarnet(String car) {

		if (car.matches("[0-9,-]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ CoberturaMedica (property)
	private String cobertura;

	/**
	 * Pemite obtener la CoberturaMEdica de un Paciente
	 * 
	 * @return cobertura String
	 */
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getCoberturaMedica() {
		return cobertura;
	}

	/**
	 * Setea la coberturaMedica que se va a crear.
	 * 
	 * @param cobertura
	 *            cobertura
	 */
	public void setCoberturaMedica(final String cobertura) {
		this.cobertura = cobertura;
	}

	// }}
	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param cob
	 * @return String
	 */
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
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
package dom.obrasocial;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.estado.EstadoEnum;

/**
 * Entidad OraSocial la cual representa la obra social de cada paciente.
 * 
 * 
 * @author Adamantium
 * @since 01/08/2015
 * @version 1.0.0
 */
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.obrasocial.ObraSocial "),
		@javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL", value = "SELECT "
				+ "FROM dom.obrasocial.ObraSocial "
				+ "WHERE nombre == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0"), })
@DomainObject(autoCompleteRepository = ObraSocialServicio.class, autoCompleteAction = "buscarObraSocial")
@PersistenceCapable
public class ObraSocial {
	/**
	 * Representa en UI el nombre "Obra Social" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Obra Social: "
				+ getNombre());
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "obraSocial";
	}

	// {{ Nombre (property)
	private String nombre;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener el nombre de Obra Social
	 * 
	 * @return nombre String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre de la obra social que se va crear.
	 * 
	 * @param nombre
	 *            nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	// }}
	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param obr
	 * @return String
	 */
	public String validateNombre(String obr) {

		if (obr.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos incorrectos, por favor vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ NombreCorto (property)
	private String nombreCorto;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener el nombreCortode Obra Social
	 * 
	 * @return nombre String
	 */
	public String getNombreCorto() {
		return nombreCorto;
	}

	/**
	 * Setea nombreCorto de la obra social que se va crear.
	 * 
	 * @param nombreCorto
	 *            nombreCorto
	 */
	public void setNombreCorto(final String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	// }}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param cob
	 * @return String
	 */
	public String validateNombreCorto(String cob) {

		if (cob.matches("[a-z, A-Z,0-9,-]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ Estado (property)
	private EstadoEnum estado;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener el estado de Obra Social
	 * 
	 * @return nombre String
	 */
	public EstadoEnum getEstado() {
		return estado;
	}

	/**
	 * Setea el estado de la obra social que se va crear.
	 * 
	 * @param estado
	 *            estado
	 */

	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}
}
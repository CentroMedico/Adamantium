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
package dom.recepcionista;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.estado.EstadoEnum;
import dom.persona.Persona;

/**
 * Entidad Recepcionista la cual representa a cualquier persona que trabaje en
 * el centro medico. Extiende de la clase Persona.
 * 
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */

@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.recepcionista.Recepcionista "),

		@javax.jdo.annotations.Query(name = "buscarNombre,Apellido,Id", language = "JDOQL", value = "SELECT "
				+ "FROM dom.recepcionista.Recepcionista "
				+ "WHERE documento == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0 || apellido.indexOf(:parametro) == 0 "
				+ " && apellido.indexOf(:parametro) >= 0 "),
		@javax.jdo.annotations.Query(name = "buscarDocDuplicados", language = "JDOQL", value = "SELECT "
				+ "FROM dom.paciente.Paciente "
				+ " WHERE documento ==:documento") })
@DomainObject(autoCompleteRepository = RecepcionistaServicio.class, autoCompleteAction = "buscarRecepcionista")
// Primera Estrategia: Una tabla por cada clase
// @PersistenceCapable(identityType = IdentityType.DATASTORE)
// @Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
// Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Recepcionista extends Persona {
	/**
	 * Representa en UI el nombre "Recepcionista" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Recepcionista: "
				+ this.getApellido() + ", " + this.getNombre());
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "recepcionista";
	}

	// {{ Legajo (property)
	private int legajo;

	/**
	 * Pemite obtener el legajo de un Paciente
	 * 
	 * @return legajo int
	 */
	@MemberOrder(sequence = "0")
	@Column(allowsNull = "false")
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence = "numeroLegajo")
	@Property(editing = Editing.DISABLED)
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
}
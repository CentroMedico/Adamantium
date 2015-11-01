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
package dom.dueño;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.joda.time.DateTime;

import dom.estado.EstadoEnum;
import dom.persona.Persona;

/**
 * Entidad Dueño la cual representa al dueño del centro medico. Extiende de la
 * clase Persona.
 * 
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
// Primera Estrategia: Una tabla por cada clase con la superclase
// @PersistenceCapable(identityType = IdentityType.DATASTORE)
// @Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
// Segunda Estrategia: Una tabla por cada clase, solo las subclases
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.dueño.Dueño "),

		@javax.jdo.annotations.Query(name = "buscarNombre,Apellido,Id", language = "JDOQL", value = "SELECT "
				+ "FROM dom.dueño.Dueño "
				+ "WHERE documento == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0 || apellido.indexOf(:parametro) == 0 "
				+ " && apellido.indexOf(:parametro) >= 0 "),
		@javax.jdo.annotations.Query(name = "buscarDuplicados", language = "JDOQL", value = "SELECT "
				+ "FROM dom.dueño.Dueño " + " WHERE documento ==:documento") })
@DomainObject(autoCompleteRepository = DueñoServicio.class, autoCompleteAction = "buscarDueño")
@PersistenceCapable
public class Dueño extends Persona {
	/**
	 * Representa en UI el nombre "Dueño" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Dueño: " + this.getApellido() + ", " + this.getNombre());
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "dueño";
	}

}
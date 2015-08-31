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
import javax.swing.JOptionPane;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.DoctorServicio;
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
				+ " && apellido.indexOf(:parametro) >= 0 ") })
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

	// {{ Iniciales (property)
	private String iniciales;

	/**
	 * Pemite obtener las iniciales del Dueño
	 * 
	 * @return matricula String
	 */
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getIniciales() {
		return iniciales;
	}

	/**
	 * Setea las iniciales que se va a crear.
	 * 
	 * @param iniciales
	 *            iniciales
	 */
	public void setIniciales(final String iniciales) {
		this.iniciales = iniciales;
	}

	// }}
	/**
	 * Valida las iniciales.
	 * 
	 * @param ini
	 * @return String
	 */
	public String validateIniciales(String ini) {

		if (ini.matches("[a-z,A-Z,ñ,Ñ]{2}$+") == false) {
			return "Las iniciales deben tener sólo 2 caracteres";
		} else {
			return null;
		}
	}

	// {{ Estado (property)
	private EstadoEnum estado;

	/**
	 * Pemite obtener el estado del Dueño
	 * 
	 * @return estado EstadoEnum
	 */
	@MemberOrder(sequence = "11")
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
	/**
	 * Metodo para inactivar el Dueño mediante un boton.
	 */

	public void InactivarDueño() {

		int resp = JOptionPane.showConfirmDialog(null,
				"¿Seguro que desea eliminar?");

		if (resp == JOptionPane.YES_OPTION) {
			if (this.estado == EstadoEnum.Inactivo) {
				JOptionPane.showMessageDialog(null,
						"El dueño ya se encuentra inactivo");
			} else {
				this.setEstado(EstadoEnum.Inactivo);
				JOptionPane.showMessageDialog(null,
						"Dueño inactivado correctamente");
			}

		} else if (resp == JOptionPane.NO_OPTION) {
		} else if (resp == JOptionPane.CANCEL_OPTION) {
		}
	}
}
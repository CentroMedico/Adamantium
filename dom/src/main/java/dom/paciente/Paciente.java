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
package dom.paciente;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.swing.JOptionPane;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.estado.EstadoEnum;
import dom.grupoSanguineo.GrupoSanguineoEnum;
import dom.persona.Persona;

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
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.paciente.Paciente "),

		@javax.jdo.annotations.Query(name = "buscarNombre,Apellido,Id", language = "JDOQL", value = "SELECT "
				+ "FROM dom.paciente.Paciente "
				+ "WHERE documento == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0 || apellido.indexOf(:parametro) == 0 "
				+ " && apellido.indexOf(:parametro) >= 0 ") })
@DomainObject(autoCompleteRepository = PacienteServicio.class, autoCompleteAction = "buscarPaciente")
@PersistenceCapable
public class Paciente extends Persona {
	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Paciente: " + this.getApellido() + ", " + this.getNombre());
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

	public void InactivarPaciente() {

		int resp = JOptionPane.showConfirmDialog(null,
				"¿Seguro que desea eliminar?");

		if (resp == JOptionPane.YES_OPTION) {
			if (this.estado == EstadoEnum.Inactivo) {
				JOptionPane.showMessageDialog(null,
						"El paciente ya se encuentra inactivo");
			} else {
				this.setEstado(EstadoEnum.Inactivo);
				JOptionPane.showMessageDialog(null,
						"Paciente inactivado correctamente");
			}

		} else if (resp == JOptionPane.NO_OPTION) {
		} else if (resp == JOptionPane.CANCEL_OPTION) {
		}
	}
}
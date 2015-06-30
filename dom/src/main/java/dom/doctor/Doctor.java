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
package dom.doctor;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.swing.JOptionPane;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.dias.DiasEnum;
import dom.especialidad.EspecialidadEnum;
import dom.estado.EstadoEnum;
import dom.persona.Persona;


/**
 * Entidad Doctor la cual representa a cualquier persona que atienda en el
 * centro medico. Extiende de la clase Persona.
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
				+ "FROM dom.doctor.Doctor "),

		@javax.jdo.annotations.Query(name = "buscarNombre,Apellido,Id", language = "JDOQL", value = "SELECT "
				+ "FROM dom.doctor.Doctor "
				+ "WHERE documento == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0 || apellido.indexOf(:parametro) == 0 "
				+ " && apellido.indexOf(:parametro) >= 0 ") })
@DomainObject(autoCompleteRepository = DoctorServicio.class, autoCompleteAction = "buscarDoctor")
@PersistenceCapable
public class Doctor extends Persona {

	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Doctor: " + this.getApellido() + ", " + this.getNombre());
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "doctor";
	}

	// {{ Matricula (property)
	private String matricula;

	/**
	 * Pemite obtener una matricula del Doctor
	 * 
	 * @return matricula String
	 */
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Setea la Matricula que se va a crear.
	 * 
	 * @param matricula
	 *            matricula
	 */
	/*----------------------------------------------------*/
	public void setMatricula(final String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Valida la Matricula a la hora de modificar.
	 * 
	 */
	/*----------------------------------------------------*/
	public String validateMatricula(String matr) {

		if (matr.matches("[a-z,A-Z,0-9,ñ,Ñ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ Especialidad (property)
	private EspecialidadEnum especialidad;

	/**
	 * Pemite obtener una especialidad del Doctor
	 * 
	 * @return especialidad EspecialidadEnum
	 */
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public EspecialidadEnum getEspecialidad() {
		return especialidad;
	}

	/**
	 * Setea la Especialidad que se va a crear.
	 * 
	 * @param especialidad
	 *            especialidad
	 */
	public void setEspecialidad(final EspecialidadEnum especialidad) {
		this.especialidad = especialidad;
	}

	// {{ Estado (property)
	private EstadoEnum estado;

	/**
	 * Pemite obtener un estado del Doctor
	 * 
	 * @return estado String
	 */
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	/**
	 * Setea la Estado que se va a crear.
	 * 
	 * @param estado
	 *            estado
	 */
	/*----------------------------------------------------*/
	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}

	// {{ Dia (property)
	private DiasEnum dia;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public DiasEnum getDia() {
		return dia;
	}

	public void setDia(final DiasEnum dia) {
		this.dia = dia;
	}

	// }}

	public void InactivarDoctor() {

		int resp = JOptionPane.showConfirmDialog(null,
				"¿Seguro que desea eliminar?");

		if (resp == JOptionPane.YES_OPTION) {
			if (this.estado == EstadoEnum.Inactivo) {
				JOptionPane.showMessageDialog(null,
						"El doctor ya se encuentra inactivo");
			} else {
				this.setEstado(EstadoEnum.Inactivo);
				JOptionPane.showMessageDialog(null,
						"Doctor inactivado correctamente");
			}

		} else if (resp == JOptionPane.NO_OPTION) {
		} else if (resp == JOptionPane.CANCEL_OPTION) {
		}
	}
}
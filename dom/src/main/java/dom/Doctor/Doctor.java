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
package dom.Doctor;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.Especialidad.EspecialidadEnum;
import dom.Estado.EstadoEnum;
import dom.Persona.Persona;
/**
 * Entidad Doctor la cual representa a cualquier persona que atienda en el
 * centro medico. Extiende de la clase Persona.
 * 
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
//Primera Estrategia: Una tabla por cada clase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Doctor extends Persona {
	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Doctor");
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

	@Inject
	private DoctorServicio doctorServicio;
	@Inject
	private DomainObjectContainer container;

}
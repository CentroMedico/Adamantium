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
package dom.ciudadProvincia;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.DoctorServicio;
/**
 * Entidad Provincia la cual representa a cualquier Provincia
 * 
 * 
 * @author Adamantium
 * @since 25/09/2015
 * @version 1.0.0
 */
@javax.jdo.annotations.Queries({
//		@javax.jdo.annotations.Query(name = "traerProvincia", language = "JDOQL", value = "SELECT"
//				+ "FROM dom.ciudadProvincia.Provincia "
//				+ "WHERE nombre.indexOf(:parametro) == 0 "
//				+ " && nombre.indexOf(:parametro) >= 0"),
	@javax.jdo.annotations.Query(name = "traerProvincia", language = "JDOQL", value = "SELECT "
			+ "FROM dom.ciudadProvincia.Provincia WHERE nombre == :nombre "
			+ " || nombre.indexOf(:nombre) >= 0"),
			@javax.jdo.annotations.Query(name = "traerTodas", language = "JDOQL", value = "SELECT "
					+ "FROM dom.ciudadProvincia.Provincia"),})

@DomainObject(autoCompleteRepository = DoctorServicio.class, autoCompleteAction = "buscarProvincia")
@PersistenceCapable
public class Provincia 
{
	
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				" " + this.getNombre());
	}
	// {{ Nombre (property)
	private String nombre;
	/**
	 * Pemite obtener el nombre de la provincia
	 * 
	 * @return nombre String
	 */
	
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setea el nombre de la Provincia
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

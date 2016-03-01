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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "traerPorDoctor", language = "JDOQL", value = "SELECT "
		+ "FROM dom.doctor.HorasTrabajadas " + " WHERE doctor ==:doctor"), })
@PersistenceCapable
public class HorasTrabajadas {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Horario doctor: "
				+ doctor.getApellido() + ", " + doctor.getNombre());
	}

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}

	// {{ Ingreso (property)
	private String ingresoEgreso;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getIngreso() {
		return ingresoEgreso;
	}

	public void setIngreso(final String ingresoEgreso) {
		this.ingresoEgreso = ingresoEgreso;
	}
	// }}

}
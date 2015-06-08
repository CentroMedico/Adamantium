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
package dom.Persona;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;

//Primera Estrategia: Una tabla por cada clase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
// @Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//----------------------------------------------------------------
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Persona {

	private String apellido;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}

	private String nombre;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	private long documento;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public long getDocumento() {
		return documento;
	}

	public void setDocumento(final long documento) {
		this.documento = documento;
	}

	private String direccion;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	private String correo;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(final String correo) {
		this.correo = correo;
	}

	private String telefono;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}
}
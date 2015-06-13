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
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;

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

	public String validateApellido(String ape) {

		if (ape.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
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

	public String validateNombre(String nom) {

		if (nom.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	private String documento;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	// @Unique
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(final String documento) {
		this.documento = documento;
	}

	public String validateDocumento(String doc) {

		if (doc.matches("[0-9]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
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

	public String validateDireccion(String dir) {

		if (dir.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
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

	public String validateCorreo(String cor) {

		if (cor.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
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

	public String validateTelefono(String tel) {

		if (tel.matches("[+]?[0-9 -]*") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}
}
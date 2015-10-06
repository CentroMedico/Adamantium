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
package dom.persona;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;
import org.joda.time.LocalDate;

import dom.ciudadProvincia.Ciudad;
import dom.ciudadProvincia.Provincia;
import dom.tipoDeSexo.TipoDeSexoEnum;
import dom.tipoDocumento.TipoDocumentoEnum;

/**
 * Clase abstracta que representa a una persona, de la cual extenderan todos los
 * Doctores, Pacientes, Dueño y Recepcionista
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
// Primera Estrategia: Una tabla por cada clase
// @PersistenceCapable(identityType = IdentityType.DATASTORE)
// @Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
// ----------------------------------------------------------------
// Segunda Estrategia: Una tabla por cada clase, solo las subclases

@javax.jdo.annotations.Queries({

		@javax.jdo.annotations.Query(name = "traerPorProvincia", language = "JDOQL", value = "SELECT "
				+ "FROM dom.persona.Persona where provincia == :provincia"),

		@javax.jdo.annotations.Query(name = "traerCiudades", language = "JDOQL", value = "SELECT "
				+ "FROM dom.persona.Persona") })
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Persona {

	private String apellido;

	/**
	 * Pemite obtener un apellido a la Persona
	 * 
	 * @return apellido String
	 */
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getApellido() {
		return apellido;
	}

	/**
	 * Setea el apellido de la Persona
	 * 
	 * @param apellido
	 *            String
	 */
	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param ape
	 * @return String
	 */
	public String validateApellido(String ape) {

		if (ape.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	private String nombre;

	/**
	 * Pemite obtener un nombre a la Persona
	 * 
	 * @return nombre String
	 */
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre de la Persona
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param nom
	 * @return String
	 */
	public String validateNombre(String nom) {

		if (nom.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ TipoDeSexoEnum (property)
	private TipoDeSexoEnum tipoDeSexo;

	/**
	 * Pemite obtener el TipoDeSexo de la Persona
	 * 
	 * @return nombre TipoDeSexoEnum
	 */
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public TipoDeSexoEnum getTipoDeSexoEnum() {
		return tipoDeSexo;
	}

	/**
	 * Setea el TipoDeSexo de la Persona
	 * 
	 * @param tipoDeSexo
	 *            TipoDeSexoEnum
	 */
	public void setTipoDeSexoEnum(final TipoDeSexoEnum tipoDeSexo) {
		this.tipoDeSexo = tipoDeSexo;
	}

	// }}

	// {{ FechaNacimiento (property)
	private LocalDate fechaNacimiento;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	// }}

	// {{ TipoDocumento (property)
	private TipoDocumentoEnum tipoDocumento;

	/**
	 * Pemite obtener el TipoDeDocumento de la Persona
	 * 
	 * @return nombre TipoDeDocumentoEnum
	 */
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Setea el TipoDeDocumento de la Persona
	 * 
	 * @param tipoDocumento
	 *            TipoDeDocumentoEnum
	 */
	public void setTipoDocumento(final TipoDocumentoEnum propertyName) {
		this.tipoDocumento = propertyName;
	}

	// }}

	private String documento;

	/**
	 * Pemite obtener un numero de documento a la Persona
	 * 
	 * @return documento String
	 */
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getDocumento() {
		return documento;
	}

	/**
	 * Setea el numero de documento de la Persona
	 * 
	 * @param documento
	 *            long
	 */
	public void setDocumento(final String documento) {
		this.documento = documento;
	}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param doc
	 * @return String
	 */
	public String validateDocumento(String doc) {

		if (doc.matches("[0-9]+") == false) {
			return "Datos erroneos, ingrese el número sin puntos ni espacios.";
		} else {
			return null;
		}
	}

	// {{ Provincia (property)
	private Provincia provincia;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "false")
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(final Provincia provincia) {
		this.provincia = provincia;
	}

	// }}

	// {{ Ciudad (property)
	private Ciudad ciudad;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(final Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	// }}

	private String direccion;

	/**
	 * Pemite obtener una direccion de la Persona
	 * 
	 * @return direccion String
	 */
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "false")
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Setea la direccion de la Persona
	 * 
	 * @param correo
	 *            String
	 */
	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param dir
	 * @return String
	 */
	public String validateDireccion(String dir) {

		if (dir.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	private String correo;

	/**
	 * Pemite obtener un correo electronico a la Persona
	 * 
	 * @return correo String
	 */
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getCorreo() {
		return correo;
	}

	/**
	 * Setea el correo electronico de la Persona
	 * 
	 * @param correo
	 *            String
	 */
	public void setCorreo(final String correo) {
		this.correo = correo;
	}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param cor
	 * @return String
	 */
	public String validateCorreo(String cor) {

		if (cor.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	private String telefono;

	/**
	 * Pemite obtener un numero de telefono celular a la Persona
	 * 
	 * @return telefono String
	 */
	@MemberOrder(sequence = "11")
	@Column(allowsNull = "false")
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Setea el numero de telefono celular de la Persona
	 * 
	 * @param telefono
	 *            String
	 */
	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param tel
	 * @return String
	 */
	public String validateTelefono(String tel) {

		if (tel.matches("[+]?[0-9 -]*") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

}
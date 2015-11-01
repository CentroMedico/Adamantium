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

import java.util.List;

import javax.inject.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.google.common.base.Predicate;

import dom.ciudadprovincia.Ciudad;
import dom.ciudadprovincia.Provincia;
import dom.especialidad.EspecialidadEnum;
import dom.estado.EstadoEnum;
import dom.tipodesexo.TipoDeSexoEnum;
import dom.tipodocumento.TipoDocumentoEnum;

/**
 * Contiene la funcionalidad para Cargar/Listar un nuevo Doctor
 * 
 * @author Adamantiums
 * @since 01/06/2015
 * @version 1.0.0
 */
@DomainService(repositoryFor = Doctor.class)
@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "2")
@Named("Doctor")
public class DoctorServicio extends AbstractFactoryAndRepository {
	/**
	 * Retorna el nombre del icono para el Doctor
	 * 
	 * @return String
	 */
	public String iconName() {
		return "doctor";
	}

	/**
	 * Atributo Extra para las validaciones de las fechas
	 */
	final LocalDate fecha_actual = LocalDate.now();

	/**
	 * Obtiene los datos validados del Cliente
	 * 
	 * @param apellido
	 *            String
	 * @param nombre
	 *            String
	 * @param documento
	 *            long
	 * @param direccion
	 *            String
	 * @param correo
	 *            String
	 * @param telefono
	 *            String
	 * @param matricula
	 *            String
	 * @param especialidad
	 *            EspecialidadEnum
	 * 
	 * @return doctor
	 */

	@MemberOrder(name = "Doctor", sequence = "2.1")
	@ActionLayout(cssClass = "boton")
	public Doctor crearDoctor(
			@ParameterLayout(named = "Matricula") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaMatricula.MATRICULA) final String matricula,
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Tipo De Sexo") final TipoDeSexoEnum tipoSexo,
			@ParameterLayout(named = "Fecha de Nacimiento") final LocalDate fechaNacimiento,
			@ParameterLayout(named = "Tipo De Documento") final TipoDocumentoEnum tipoDocumento,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String documento,
			@ParameterLayout(named = "Provincia") final Provincia provincia,
			@ParameterLayout(named = "Ciudad") final Ciudad ciudad,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.DIRECCION) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Especialidad") final EspecialidadEnum especialidad) {

		final Doctor doctor = newTransientInstance(Doctor.class);
		doctor.setApellido(apellido.substring(0, 1).toUpperCase()
				+ apellido.substring(1));
		doctor.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		doctor.setTipoDeSexoEnum(tipoSexo);
		doctor.setFechaNacimiento(fechaNacimiento);
		doctor.setTipoDocumento(tipoDocumento);
		doctor.setDocumento(documento);
		doctor.setProvincia(provincia);
		doctor.setCiudad(ciudad);
		doctor.setDireccion(direccion.substring(0, 1).toUpperCase()
				+ direccion.substring(1));
		doctor.setCorreo(correo);
		doctor.setTelefono(telefono);
		doctor.setMatricula(matricula);
		doctor.setEspecialidad(especialidad);
		doctor.setEstado(EstadoEnum.Activo);
		doctor.setFechaAlta(LocalDate.now());
		persist(doctor);
		container.flush();
		return doctor;
	}

	/**
	 * Obtiene una lista de Doctores
	 * 
	 * @return listaDeDoctores List<Doctores>
	 */

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<Doctor> buscarDoctor(String doctor) {
		return allMatches(QueryDefault.create(Doctor.class,
				"buscarNombre,Apellido,Id", "parametro", doctor));
	}

	/**
	 * Obtiene una lista de todos los doctores
	 * 
	 * @return listaDeDoctores List<Doctores>
	 */
	@MemberOrder(name = "Doctor", sequence = "2.3")
	public List<Doctor> listarDoctores() {
		return container.allInstances(Doctor.class);
	}

	/**
	 * Obtiene una lista de Doctores Activos
	 * 
	 * @return List<Doctor>
	 */
	@MemberOrder(name = "Doctor", sequence = "2.4")
	public List<Doctor> listarDoctoresActivos() {
		return allMatches(Doctor.class, new Predicate<Doctor>() {

			@Override
			public boolean apply(Doctor input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	/**
	 * Obtiene una lista de Doctores Inactivos
	 * 
	 * @return List<Doctor>
	 */
	@MemberOrder(name = "Doctor", sequence = "2.5")
	public List<Doctor> listarDoctoresInactivos() {
		return allMatches(Doctor.class, new Predicate<Doctor>() {

			@Override
			public boolean apply(Doctor input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public String buscarCiudad(final Provincia provincia, Ciudad ciudad) {

		return "";

	}

	public List<Provincia> choices0BuscarCiudad(final Provincia provincia) {
		return container.allMatches(QueryDefault.create(Provincia.class,
				"traerProvincia", "provincia", provincia));
	}

	public Provincia default0BuscarCiudad(final Provincia prov) {
		return container.allInstances(Provincia.class, 0).get(0);
	}

	public List<Provincia> choices1BuscarCiudad(final Provincia provincia,
			Ciudad ciudad) {
		return container.allMatches(QueryDefault.create(Provincia.class,
				"traerCiudad", "provincia", provincia, "ciudad", ciudad));
	}

	// Choices de Provincia y ciudades
	/**
	 * buscarProvincia retorna una lista de todas las Provincias en la base de
	 * datos.
	 * 
	 */

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<Provincia> buscarProvincia(String prov) {
		return allMatches(QueryDefault.create(Provincia.class,
				"traerProvincia", "nombre", prov));
	}

	/**
	 * Choice default devuelve la primer provincia de la lista.
	 * 
	 */
	public Provincia default7CrearDoctor() {
		return container.firstMatch(QueryDefault.create(Provincia.class,
				"traerTodas"));

	}

	/**
	 * Choice7 devuelve una lista de ciudades dependiendo cual provincia se
	 * selecciono previamente.
	 */

	public List<Ciudad> choices8CrearDoctor(final String matricula,
			final String apellido, final String nombre,
			final TipoDeSexoEnum tipoSexo, final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincias) {
		return container.allMatches(QueryDefault.create(Ciudad.class,
				"traerCiudad", "provincia", provincias));
	}

	/**
	 * Valida
	 */

	public String validateCrearDoctor(final String matricula,
			final String apellido, final String nombre,
			final TipoDeSexoEnum tipoSexo, final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincia, final Ciudad ciudad,
			final String direccion, final String correo, final String telefono,
			final EspecialidadEnum especialidad) {

		final Doctor miDoctor = container.firstMatch(QueryDefault.create(
				Doctor.class, "buscarDuplicados", "documento", documento));
		if (miDoctor != null) {
			if (miDoctor.getDocumento().equals(documento)) {
				return "Ya existe un doctor con este numero de documento: "
						+ documento;
			}
		}
		if (fechaNacimiento.isAfter(fecha_actual))
			return "La fecha de Nacimiento debe ser menor o igual a la fecha actual";
		if (validaMayorEdad(fechaNacimiento) == false)
			return "El doctor es menor de edad";
		if (validaMayorCien(fechaNacimiento) == false)
			return "El doctor no puede ser mayor a 100 años";
		return "";

		// if (firstMatch(Persona.class, new Predicate<Persona>() {
		//
		// @Override
		// public boolean apply(Persona _persona) {
		// // TODO Auto-generated method stub
		// return _persona.getUsuario().getNombre().equals(_nombreUsuario);
		// }
		// }) != null)
		// return "Ya existe el nombre de usuario!";
		// return _telefono == null && _celular == null ?
		// "Debe ingresar al menos un teléfono"
		// : null;
	}

	/**
	 * Validacion de la mayoria de edad de las personas ingresadas, 6575 es la
	 * cantidad de dias que tiene una persona de 18 años
	 * 
	 * @param fechadeNacimiento
	 *            LocalDate
	 * @return boolean
	 */

	@ActionLayout(hidden = Where.EVERYWHERE)
	public boolean validaMayorEdad(LocalDate fechadeNacimiento) {

		if (getDiasNacimiento_Hoy(fechadeNacimiento) >= 6575) {
			return true;
		}
		return false;
	}

	/**
	 * Validacion de la edad de las personas ingresadas, 36500 es la cantidad de
	 * dias que tiene una persona de 100 años
	 * 
	 * @param fechadeNacimiento
	 *            LocalDate
	 * @return boolean
	 */

	@ActionLayout(hidden = Where.EVERYWHERE)
	public boolean validaMayorCien(LocalDate fechadeNacimiento) {

		if (getDiasNacimiento_Hoy(fechadeNacimiento) <= 36500) {
			return true;
		}
		return false;
	}

	/**
	 * Obtiene la cantidad de dias entre la fecha de nacimiento y la fecha
	 * actual
	 * 
	 * @param fechadeNacimiento
	 *            LocalDate
	 * @return org.joda.time.Days meses
	 */
	@ActionLayout(hidden = Where.EVERYWHERE)
	public int getDiasNacimiento_Hoy(LocalDate fechadeNacimiento) {

		Days meses = Days.daysBetween(fechadeNacimiento, fecha_actual);
		return meses.getDays();
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
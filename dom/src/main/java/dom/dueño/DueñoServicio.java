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

import java.util.List;

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

import dom.ciudadProvincia.Ciudad;
import dom.ciudadProvincia.Provincia;
import dom.estado.EstadoEnum;
import dom.tipoDeSexo.TipoDeSexoEnum;
import dom.tipoDocumento.TipoDocumentoEnum;

/**
 * Contiene la funcionalidad para Cargar/Listar un nuevo Dueño
 * 
 * @author Adamantiums
 * @since 01/06/2015
 * @version 1.0.0
 */
@DomainService(repositoryFor = Dueño.class)
@DomainServiceLayout(named = "Dueño", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "1")
public class DueñoServicio extends AbstractFactoryAndRepository {
	/**
	 * Retorna el nombre del icono para el Dueño
	 * 
	 * @return String
	 */
	public String iconName() {
		return "dueño";
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
	 * @param iniciales
	 *            String
	 *
	 * @return dueño
	 */
	@MemberOrder(name = "Dueño", sequence = "1.1")
	public Dueño crearDueño(
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
			@ParameterLayout(named = "Iniciales") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.INICIALES) final String iniciales) {

		final Dueño dueño = newTransientInstance(Dueño.class);
		dueño.setApellido(apellido.substring(0, 1).toUpperCase()
				+ apellido.substring(1));
		dueño.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		dueño.setTipoDeSexoEnum(tipoSexo);
		dueño.setFechaNacimiento(fechaNacimiento);
		dueño.setTipoDocumento(tipoDocumento);
		dueño.setDocumento(documento);
		dueño.setProvincia(provincia);
		dueño.setCiudad(ciudad);
		dueño.setDireccion(direccion.substring(0, 1).toUpperCase()
				+ direccion.substring(1));
		dueño.setCorreo(correo);
		dueño.setTelefono(telefono);
		dueño.setIniciales(iniciales.substring(0, 1).toUpperCase()
				+ iniciales.substring(1));
		dueño.setEstado(EstadoEnum.Activo);
		persist(dueño);
		container.flush();
		return dueño;
	}

	/**
	 * Obtiene una lista de todos los dueños
	 * 
	 * @return listaDeDueños List<Dueño>
	 */
	@MemberOrder(name = "Dueño", sequence = "1.2")
	public List<Dueño> listarDueños() {
		return container.allInstances(Dueño.class);
	}

	/**
	 * Obtiene una lista de Dueños Activos
	 * 
	 * @return List<Dueño>
	 */
	@MemberOrder(name = "Dueño", sequence = "1.3")
	public List<Dueño> listarDueñosActivos() {
		return allMatches(Dueño.class, new Predicate<Dueño>() {

			@Override
			public boolean apply(Dueño input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	/**
	 * Obtiene una lista de Dueños Inactivos
	 * 
	 * @return List<Dueño>
	 */
	@MemberOrder(name = "Dueño", sequence = "1.3")
	public List<Dueño> listarDueñosInactivos() {
		return allMatches(Dueño.class, new Predicate<Dueño>() {

			@Override
			public boolean apply(Dueño input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});
	}

	/**
	 * Choice default devuelve la primer provincia de la lista.
	 * 
	 */

	public Provincia default6CrearDueño() {
		return container.firstMatch(QueryDefault.create(Provincia.class,
				"traerTodas"));

	}

	/**
	 * Choice7 devuelve una lista de ciudades dependiendo cual provincia se
	 * selecciono previamente.
	 */

	public List<Ciudad> choices7CrearDueño(final String apellido,
			final String nombre, final TipoDeSexoEnum tipoSexo,
			final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincias) {
		return container.allMatches(QueryDefault.create(Ciudad.class,
				"traerCiudad", "provincia", provincias));
	}

	/**
	 * // * Valida //
	 */

	public String validateCrearDueño(final String apellido,
			final String nombre, final TipoDeSexoEnum tipoSexo,
			final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincia, final Ciudad ciudad,
			final String direccion, final String correo, final String telefono,
			final String iniciales) {

		final Dueño miDueño = container.firstMatch(QueryDefault.create(
				Dueño.class, "buscarDuplicados", "documento", documento));
		if (miDueño != null) {
			if (miDueño.getDocumento().equals(documento)) {
				return "Ya existe un Dueño con este numero de documento: "
						+ documento;
			}
		}
		if (fechaNacimiento.isAfter(fecha_actual))
			return "La fecha de Nacimiento debe ser menor o igual a la fecha actual";
		if (validaMayorEdad(fechaNacimiento) == false)
			return "El Doctor es menor de edad";
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
	 * Validacion de la mayoria de edad de los empleados ingresados 6575 son la
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
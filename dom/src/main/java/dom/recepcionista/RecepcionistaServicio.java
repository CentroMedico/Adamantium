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
package dom.recepcionista;

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

import dom.ciudadprovincia.Ciudad;
import dom.ciudadprovincia.Provincia;
import dom.estado.EstadoEnum;
import dom.tipodesexo.TipoDeSexoEnum;
import dom.tipodocumento.TipoDocumentoEnum;

/**
 * Contiene la funcionalidad para Cargar/Listar un nuev@ Recepcionista
 * 
 * @author Adamantiums
 * @since 01/06/2015
 * @version 1.0.0
 */
@DomainService(repositoryFor = Recepcionista.class)
@DomainServiceLayout(named = "Recepcionista", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "3")
public class RecepcionistaServicio extends AbstractFactoryAndRepository {
	/**
	 * Retorna el nombre del icono para el Doctor
	 * 
	 * @return String
	 */
	public String iconName() {
		return "recepcionista";
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
	 * @param legajo
	 *            int
	 *
	 * @return recepcionista
	 */
	@MemberOrder(name = "Recepcionista", sequence = "3.1")
	@ActionLayout(cssClass = "boton")
	public Recepcionista crearRecepcionista(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Tipo De Sexo") final TipoDeSexoEnum tipoSexo,
			@ParameterLayout(named = "Fecha de Nacimiento") final LocalDate fechaNacimiento,
			@ParameterLayout(named = "Tipo De Documento") final TipoDocumentoEnum tipoDocumento,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String documento,
			@ParameterLayout(named = "Provincia") final Provincia provincia,
			@ParameterLayout(named = "Ciudad") final Ciudad ciudad,
			@ParameterLayout(named = "Código Postal") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.CODIGOPOSTAL) final String codigoPostal,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.DIRECCION) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono) {

		final Recepcionista recepcionista = newTransientInstance(Recepcionista.class);
		recepcionista.setApellido(apellido.substring(0, 1).toUpperCase()
				+ apellido.substring(1));
		recepcionista.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		recepcionista.setTipoDeSexoEnum(tipoSexo);
		recepcionista.setFechaNacimiento(fechaNacimiento);
		recepcionista.setTipoDocumento(tipoDocumento);
		recepcionista.setDocumento(documento);
		recepcionista.setProvincia(provincia);
		recepcionista.setCiudad(ciudad);
		recepcionista.setCodigoPostal(codigoPostal);
		recepcionista.setDireccion(direccion.substring(0, 1).toUpperCase()
				+ direccion.substring(1));
		recepcionista.setCorreo(correo);
		recepcionista.setTelefono(telefono);
		recepcionista.setEstado(EstadoEnum.Activo);
		recepcionista.setFechaAlta(LocalDate.now());
		persist(recepcionista);
		container.flush();
		return recepcionista;
	}

	/**
	 * Obtiene una lista de todos l@s Recepcionistas
	 * 
	 * @return listaDeRecepcionistas List<Recepcionista>
	 */
	@MemberOrder(name = "Recepcionista", sequence = "3.2")
	public List<Recepcionista> listarRecepcionista() {
		return container.allInstances(Recepcionista.class);
	}

	/**
	 * Obtiene una lista de todos l@s Recepcionistas Activas
	 * 
	 * @return List<Recepcionista>
	 */
	@MemberOrder(name = "Recepcionista", sequence = "3.3")
	public List<Recepcionista> listarRecepcionistasActivos() {
		return allMatches(Recepcionista.class, new Predicate<Recepcionista>() {

			@Override
			public boolean apply(Recepcionista input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	/**
	 * Obtiene una lista de todos l@s Recepcionistas Inactivas
	 * 
	 * @return List<Recepcionista>
	 */
	@MemberOrder(name = "Recepcionista", sequence = "3.4")
	public List<Recepcionista> listarRecepcionistasInactivos() {
		return allMatches(Recepcionista.class, new Predicate<Recepcionista>() {

			@Override
			public boolean apply(Recepcionista input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});
	}

	/**
	 * Choices para Traer la primer Provincia
	 * 
	 */

	public Provincia default6CrearRecepcionista() {
		return container.firstMatch(QueryDefault.create(Provincia.class,
				"traerTodas"));

	}

	/**
	 * Choices para Listar todas las ciudades de la provincia Seleccionada.
	 * 
	 */

	public List<Ciudad> choices7CrearRecepcionista(final String apellido,
			final String nombre, final TipoDeSexoEnum tipoSexo,
			final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincias) {
		return container.allMatches(QueryDefault.create(Ciudad.class,
				"traerCiudad", "provincia", provincias));
	}

	/**
	 * Valida
	 */

	public String validateCrearRecepcionista(final String apellido,
			final String nombre, final TipoDeSexoEnum tipoSexo,
			final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincia, final Ciudad ciudad,
			final String codigoPostal, final String direccion,
			final String correo, final String telefono) {

		final Recepcionista miRecepcionista = container.firstMatch(QueryDefault
				.create(Recepcionista.class, "buscarDocDuplicados",
						"documento", documento));
		if (miRecepcionista != null) {
			if (miRecepcionista.getDocumento().equals(documento)) {
				return "Ya existe un/@ Recepcionista con este numero de documento: "
						+ documento;

			}
		}
		if (fechaNacimiento.isAfter(fecha_actual))
			return "La fecha de Nacimiento debe ser menor o igual a la fecha actual";
		if (validaMayorEdad(fechaNacimiento) == false)
			return "Recepcionista menor de edad";
		if (validaMayorCien(fechaNacimiento) == false)
			return "Recepcionista mayor a 100 años";
		return "";
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
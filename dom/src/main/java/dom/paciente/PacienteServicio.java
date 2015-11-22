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
package dom.paciente;

import java.util.List;
import java.util.Scanner;

import javax.inject.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
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
import dom.gruposanguineo.GrupoSanguineoEnum;
import dom.obrasocial.ObraSocial;
import dom.paciente.graficotorta.MayoriaEdadEnum;
import dom.paciente.graficotorta.RangoEdadEnum;
import dom.tipodesexo.TipoDeSexoEnum;
import dom.tipodocumento.TipoDocumentoEnum;

/**
 * Contiene la funcionalidad para Cargar/Listar un nuevo Paciente
 * 
 * @author Adamantiums
 * @since 01/06/2015
 * @version 1.0.0
 */
@DomainService(repositoryFor = Paciente.class)
@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
@Named("Paciente")
public class PacienteServicio extends AbstractFactoryAndRepository {

	/**
	 * Retorna el nombre del icono para el Doctor
	 * 
	 * @return String
	 */
	public String iconName() {
		return "paciente";
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
	 * @param grupoSanguineo
	 *            GrupoSanguineoEnum
	 *
	 * @return paciente
	 */

	@MemberOrder(name = "Paciente", sequence = "5.1")
	@ActionLayout(cssClass = "boton")
	public Paciente crearPaciente(
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
			@ParameterLayout(named = "Grupo Sanguineo") final GrupoSanguineoEnum grupoSanguineo,
			@ParameterLayout(named = "Obra Social") @Parameter(optionality = Optionality.OPTIONAL) final ObraSocial obraSocial,
			@ParameterLayout(named = "Numero de Carnet") @Parameter(optionality = Optionality.OPTIONAL) final String numCarnet,
			@ParameterLayout(named = "Numero de Plan") @Parameter(optionality = Optionality.OPTIONAL) final String numPlan) {

		final Paciente paciente = newTransientInstance(Paciente.class);
		paciente.setApellido(apellido.substring(0, 1).toUpperCase()
				+ apellido.substring(1));
		paciente.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		paciente.setTipoDeSexoEnum(tipoSexo);
		paciente.setFechaNacimiento(fechaNacimiento);
		paciente.setTipoDocumento(tipoDocumento);
		paciente.setDocumento(documento);
		paciente.setProvincia(provincia);
		paciente.setCiudad(ciudad);
		paciente.setDireccion(direccion.substring(0, 1).toUpperCase()
				+ direccion.substring(1));
		paciente.setCorreo(correo);
		paciente.setTelefono(telefono);
		paciente.setEstado(EstadoEnum.Activo);
		paciente.setGrupoSanguineo(grupoSanguineo);
		paciente.setFechaAlta(LocalDate.now());
		paciente.setObraSocial(obraSocial);
		if (obraSocial != null) {
			paciente.setNumerodeCarnet(numCarnet);
			paciente.setNumerodePlan(numPlan);
		}
		if (getDiasNacimiento_Hoy(fechaNacimiento) <= 6570) {
			paciente.setMayoriaEdad(MayoriaEdadEnum.Menor);
		} else
			paciente.setMayoriaEdad(MayoriaEdadEnum.Mayor);
		if (getDiasNacimiento_Hoy(fechaNacimiento) <= 1825) {
			paciente.setRangoEdad(RangoEdadEnum.MenorCinco);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 3650) {
			paciente.setRangoEdad(RangoEdadEnum.MenorDiez);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 7300) {
			paciente.setRangoEdad(RangoEdadEnum.MenorVeinte);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 10950) {
			paciente.setRangoEdad(RangoEdadEnum.MenorTreinta);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 14600) {
			paciente.setRangoEdad(RangoEdadEnum.MenorCuarenta);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 18250) {
			paciente.setRangoEdad(RangoEdadEnum.MenorCincuenta);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 21900) {
			paciente.setRangoEdad(RangoEdadEnum.MenorSesenta);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 25550) {
			paciente.setRangoEdad(RangoEdadEnum.MenorSetenta);
		} else if (getDiasNacimiento_Hoy(fechaNacimiento) <= 29200) {
			paciente.setRangoEdad(RangoEdadEnum.MenorOchenta);
		} else
			paciente.setRangoEdad(RangoEdadEnum.MayorOchenta);

		persist(paciente);
		container.flush();
		return paciente;
	}

	/**
	 * Obtiene una lista de todos los Pacientes
	 * 
	 * @return listaDePaiente List<Paciente>
	 */
	@MemberOrder(name = "Paciente", sequence = "5.2")
	public List<Paciente> listarPacientes() {
		return container.allInstances(Paciente.class);
	}

	/**
	 * Obtiene una lista de Pacientes Activos
	 * 
	 * @return List<Paciente>
	 */
	@MemberOrder(name = "Paciente", sequence = "5.3")
	public List<Paciente> listarPacientesActivos() {
		return allMatches(Paciente.class, new Predicate<Paciente>() {

			@Override
			public boolean apply(Paciente input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	/**
	 * Obtiene una lista de Pacientes Inactivos
	 * 
	 * @return List<Paciente>
	 */
	@MemberOrder(name = "Paciente", sequence = "5.4")
	public List<Paciente> listarPacientesInactivos() {
		return allMatches(Paciente.class, new Predicate<Paciente>() {

			@Override
			public boolean apply(Paciente input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});
	}

	// Choices Provincias
	/**
	 * Choice default devuelve la primer provincia de la lista.
	 * 
	 */
	public Provincia default6CrearPaciente() {
		return container.firstMatch(QueryDefault.create(Provincia.class,
				"traerTodas"));

	}

	/**
	 * Choice8 devuelve una lista de ciudades dependiendo cual provincia se
	 * selecciono previamente.
	 */
	public List<Ciudad> choices7CrearPaciente(final String apellido,
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

	public String validateCrearPaciente(final String apellido,
			final String nombre, final TipoDeSexoEnum tipoSexo,
			final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincia, final Ciudad ciudad,
			final String direccion, final String correo, final String telefono,
			final GrupoSanguineoEnum grupoSanguineo,
			final ObraSocial obraSocial, final String numCarnet,
			final String numPlan) {

		final Paciente miPaciente = container.firstMatch(QueryDefault.create(
				Paciente.class, "buscarDocDuplicados", "documento", documento));
		if (miPaciente != null) {
			if (miPaciente.getDocumento().equals(documento)) {
				return "Ya existe un Paciente con este numero de documento: "
						+ documento;
			}
		}
		if (fechaNacimiento.isAfter(fecha_actual))
			return "La fecha de Nacimiento debe ser menor o igual a la fecha actual";
		if (validaMayorEdad(fechaNacimiento) == false)
			return "El Paciente es menor a 2 años";
		if (validaMayorCien(fechaNacimiento) == false)
			return "El paciente no puede ser mayor a 100 años";
		return "";
	}

	/**
	 * Validacion de la edad de los pacientes ingresados, 730 es la cantidad de
	 * dias que tiene un paciente mayor a 18 años
	 * 
	 * @param fechadeNacimiento
	 *            LocalDate
	 * @return boolean
	 */

	@ActionLayout(hidden = Where.EVERYWHERE)
	public boolean validaMayorEdad(LocalDate fechadeNacimiento) {

		if (getDiasNacimiento_Hoy(fechadeNacimiento) >= 730) {
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

	@MemberOrder(name = "Paciente", sequence = "5.1.2")
	public Paciente buscarPaciente(
			@ParameterLayout(named = "Buscar Paciente") Paciente paciente) {
		return paciente;
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<Paciente> autocompletarPaciente(final String paciente) {
		return allMatches(QueryDefault.create(
				Paciente.class,
				"traerPaciente",
				"nombre",
				paciente.substring(0, 1).toUpperCase()
						+ paciente.substring(1, paciente.length())));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
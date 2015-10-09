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
import org.joda.time.LocalDate;

import com.google.common.base.Predicate;

import dom.agendaDoctor.AgendaDoctor;
import dom.ciudadProvincia.Ciudad;
import dom.ciudadProvincia.Provincia;
import dom.doctor.Doctor;
import dom.especialidad.EspecialidadEnum;
import dom.estado.EstadoEnum;
import dom.grupoSanguineo.GrupoSanguineoEnum;
import dom.tipoDeSexo.TipoDeSexoEnum;
import dom.tipoDocumento.TipoDocumentoEnum;

/**
 * Contiene la funcionalidad para Cargar/Listar un nuevo Paciente
 * 
 * @author Adamantiums
 * @since 01/06/2015
 * @version 1.0.0
 */
@DomainService(repositoryFor = Paciente.class)
@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
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
	public Paciente crearPaciente(
			@ParameterLayout(named = "Legajo") final int legajo,
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Tipo De Sexo") final TipoDeSexoEnum tipoSexo,
			@ParameterLayout(named = "Fecha de Nacimiento") final LocalDate fechaNacimiento,
			@ParameterLayout(named = "Tipo De Documento") final TipoDocumentoEnum tipoDocumento,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String documento,
			@ParameterLayout(named = "Provincia") final Provincia provincia,
			@ParameterLayout(named = "Ciudad") final Ciudad ciudad,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Grupo Sanguineo") final GrupoSanguineoEnum grupoSanguineo) {

		final Paciente paciente = newTransientInstance(Paciente.class);
		paciente.setLegajo(legajo);
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
	public Provincia default7CrearPaciente() {
		return container.firstMatch(QueryDefault.create(Provincia.class,
				"traerTodas"));

	}

	/**
	 * Choice8 devuelve una lista de ciudades dependiendo cual provincia se
	 * selecciono previamente.
	 */
	public List<Ciudad> choices8CrearPaciente(final int legajo,
			final String apellido, final String nombre,
			final TipoDeSexoEnum tipoSexo, final LocalDate fechaNacimiento,
			final TipoDocumentoEnum tipoDocumento, final String documento,
			final Provincia provincias) {
		return container.allMatches(QueryDefault.create(Ciudad.class,
				"traerCiudad", "provincia", provincias));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
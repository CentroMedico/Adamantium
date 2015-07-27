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
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.LocalDate;

import com.google.common.base.Predicate;

import dom.estado.EstadoEnum;
import dom.tipoDeSexo.TipoDeSexoEnum;
import dom.tipoDocumento.TipoDocumentoEnum;

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
	public Recepcionista crearRecepcionista(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Tipo De Sexo") final TipoDeSexoEnum tipoSexo,
			@ParameterLayout(named = "Fecha de Nacimiento") final LocalDate fechaNacimiento,
			@ParameterLayout(named = "Tipo De Documento") final TipoDocumentoEnum tipoDocumento,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String documento,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Legajo") final int legajo) {

		final Recepcionista recepcionista = newTransientInstance(Recepcionista.class);
		recepcionista.setApellido(apellido.substring(0, 1).toUpperCase()
				+ apellido.substring(1));
		recepcionista.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		recepcionista.setTipoDeSexoEnum(tipoSexo);
		recepcionista.setFechaNacimiento(fechaNacimiento);
		recepcionista.setTipoDocumento(tipoDocumento);
		recepcionista.setDocumento(documento);
		recepcionista.setDireccion(direccion.substring(0, 1).toUpperCase()
				+ direccion.substring(1));
		recepcionista.setCorreo(correo);
		recepcionista.setTelefono(telefono);
		recepcionista.setLegajo(legajo);
		recepcionista.setEstado(EstadoEnum.Activo);
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

	// @MemberOrder(name = "Recepcionista", sequence = "3.4")
	// public void buscarRecepcionista() {
	//
	// }

	@javax.inject.Inject
	DomainObjectContainer container;

}
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
package dom.obrasocial;

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

import com.google.common.base.Predicate;

import dom.estado.EstadoEnum;
import dom.paciente.Paciente;

/**
 * Contiene la funcionalidad para Cargar/Listar una Obra Social
 * 
 * @author Adamantiums
 * @since 01/08/2015
 * @version 1.0.0
 */
@DomainService(repositoryFor = ObraSocial.class)
@DomainServiceLayout(named = "Obra Social", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "4")
public class ObraSocialServicio extends AbstractFactoryAndRepository {
	/**
	 * Retorna el nombre del icono para el Doctor
	 * 
	 * @return String
	 */
	public String iconName() {
		return "obraSocial";
	}

	@MemberOrder(name = "Obra Social", sequence = "4.1")
	@ActionLayout(cssClass = "boton")
	public ObraSocial crearObraSocial(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Nombr Abreviado") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaCobertura.COBERTURA) final String cobertura) {
		final ObraSocial obraSocial = newTransientInstance(ObraSocial.class);
		obraSocial.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		obraSocial.setNombreCorto(cobertura.substring(0, 1).toUpperCase()
				+ cobertura.substring(1));
		persist(obraSocial);
		container.flush();
		return obraSocial;
	}

	/**
	 * // * Obtiene una lista de todos las ObrasSociales // * // * @return
	 * listaDeObrasSociales List<ObraSocial> //
	 */
	@MemberOrder(name = "Obra Social", sequence = "4.2")
	public List<ObraSocial> listarObraSocial() {
		final List<ObraSocial> listaObraSocial = allInstances(ObraSocial.class);
		return listaObraSocial;
	}

	/**
	 * // * Obtiene una lista de ObraSocial Activos // * // * @return
	 * List<ObraSocial> //
	 */
	@MemberOrder(name = "Obra Social", sequence = "4.3")
	public List<ObraSocial> listarObraSocialesActivos() {
		return allMatches(ObraSocial.class, new Predicate<ObraSocial>() {

			@Override
			public boolean apply(ObraSocial input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	/**
	 * Obtiene una lista de ObraSocial Inactivos
	 * 
	 * @return List<ObraSocial>
	 */
	@MemberOrder(name = "Obra Social", sequence = "4.4")
	public List<ObraSocial> listarObraSocialInactivos() {
		return allMatches(ObraSocial.class, new Predicate<ObraSocial>() {

			@Override
			public boolean apply(ObraSocial input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});

	}

	@ActionLayout(hidden = Where.EVERYWHERE, cssClass = "boton")
	public List<ObraSocial> buscarObraSocial(String obraSocial) {
		return allMatches(QueryDefault.create(ObraSocial.class, "buscarNombre",
				"parametro", obraSocial));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
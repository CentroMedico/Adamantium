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
package dom.vademecum;

import java.util.List;

import javax.inject.Named;
import javax.xml.ws.Action;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.CssClass;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;

@DomainService(repositoryFor = Vademecum.class,nature=NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Vademecum", menuBar = DomainServiceLayout.MenuBar.SECONDARY, menuOrder = "1")
@Named("Vademecum")
public class VademecumServicio extends AbstractFactoryAndRepository {
	
	
	/**
	 * Retorna el nombre del icono para el Vademecum
	 * 
	 * @return String
	 */
	public String iconName() {
		return "vademecum";
	}
	@ActionLayout(named="Buscar Medicamento" ,cssClass="buscarMedicamento")
	public Vademecum buscarMedicamento(
			@ParameterLayout(named="Buscar Medicamento en Mayusculas",describedAs = "Busca Medicamentos en el Vademecum" ,cssClass="buscarMedicamento")Vademecum vademecum) 
	{	
		return vademecum;
	}

	/**
	 * Autocompleta los medicamentos
	 * 
	 * @param vademecum
	 * @return autocompletarMedicamento
	 */
	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<Vademecum> autocompletarMedicamento(final String vademecum) {
		return allMatches(QueryDefault.create(Vademecum.class,
				"traerMedicamentos", "nombre", vademecum));
	}

	/**
	 * Obtiene una lista de Medicamentos
	 * 
	 * @return buscarMedicamentos List<Vademecum>
	 */
	
	@MemberOrder(name = "Vademecum", sequence = "2.3")
	public List<Vademecum> listarMedicamentos() {
		return container.allInstances(Vademecum.class);
	}

	// public Vademecum buscarMedicamento() {
	// return container.firstMatch(QueryDefault.create(Vademecum.class,
	// "traerTodos"));
	//
	// }

	@javax.inject.Inject
	DomainObjectContainer container;
}

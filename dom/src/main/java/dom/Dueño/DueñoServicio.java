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
package dom.Dueño;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import com.google.common.base.Predicate;

import dom.Estado.EstadoEnum;

@DomainService(repositoryFor = Dueño.class)
@DomainServiceLayout(named = "Dueño", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "1")
public class DueñoServicio extends AbstractFactoryAndRepository {

	@MemberOrder(name = "Dueño", sequence = "1.1")
	public Dueño crearDueño(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaDoc.DOCUMENTO) final long documento,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Iniciales") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.INICIALES) final String iniciales) {

		final Dueño dueño = newTransientInstance(Dueño.class);
		dueño.setApellido(apellido.toUpperCase());
		dueño.setNombre(nombre.toUpperCase());
		dueño.setDocumento(documento);
		dueño.setDireccion(direccion.toUpperCase());
		dueño.setCorreo(correo);
		dueño.setTelefono(telefono);
		dueño.setIniciales(iniciales.toUpperCase());
		dueño.setEstado(EstadoEnum.Activo);
		persist(dueño);
		return dueño;
	}

	@MemberOrder(name = "Dueño", sequence = "1.2")
	public List<Dueño> listarDueños() {
		return container.allInstances(Dueño.class);
	}

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

	@MemberOrder(name = "Dueño", sequence = "1.4")
	public void buscarDueño() {

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
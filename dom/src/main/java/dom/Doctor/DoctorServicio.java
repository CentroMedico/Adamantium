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
package dom.Doctor;

import java.util.List;

import javax.inject.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;

import com.google.common.base.Predicate;

import dom.Especialidad.EspecialidadEnum;
import dom.Estado.EstadoEnum;

@DomainService(repositoryFor = Doctor.class)
@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "3")
@Named("Doctor")
public class DoctorServicio extends AbstractFactoryAndRepository {
	Doctor doc = new Doctor();

	@MemberOrder(name = "Doctor", sequence = "3.1")
	public Doctor crearDoctor(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaDoc.DOCUMENTO) final long documento,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Matricula") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaMatricula.MATRICULA) final String matricula,
			@ParameterLayout(named = "Especialidad") final EspecialidadEnum especialidad) {

		final Doctor doctor = newTransientInstance(Doctor.class);
		doctor.setApellido(apellido.toUpperCase());
		doctor.setNombre(nombre.toUpperCase());
		doctor.setDocumento(documento);
		doctor.setDireccion(direccion.toUpperCase());
		doctor.setCorreo(correo);
		doctor.setTelefono(telefono);
		doctor.setMatricula(matricula.toUpperCase());
		doctor.setEspecialidad(especialidad);
		doctor.setEstado(EstadoEnum.Activo);
		persist(doctor);
		return doctor;
	}

	@MemberOrder(name = "Doctor", sequence = "3.2")
	public List<Doctor> listarDoctores() {
		return container.allInstances(Doctor.class);
	}

	@MemberOrder(name = "Doctor", sequence = "3.3")
	public List<Doctor> listarDoctoresActivos() {
		return allMatches(Doctor.class, new Predicate<Doctor>() {

			@Override
			public boolean apply(Doctor input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	@MemberOrder(name = "Doctor", sequence = "3.4")
	public List<Doctor> listarDoctoresInactivos() {
		return allMatches(Doctor.class, new Predicate<Doctor>() {

			@Override
			public boolean apply(Doctor input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});
	}

	@MemberOrder(name = "Doctor", sequence = "3.5")
	public void buscarDoctor() {

	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
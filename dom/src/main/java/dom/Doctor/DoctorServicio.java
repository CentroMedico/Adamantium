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
			@ParameterLayout(named = "Especialidad") final EspecialidadEnum especialidad,
			@ParameterLayout(named = "Estado") final EstadoEnum estado) {

		final Doctor doctor = newTransientInstance(Doctor.class);
		doctor.setApellido(apellido.toUpperCase());
		doctor.setNombre(nombre.toUpperCase());
		doctor.setDocumento(documento);
		doctor.setDireccion(direccion.toUpperCase());
		doctor.setCorreo(correo);
		doctor.setTelefono(telefono);
		doctor.setMatricula(matricula.toUpperCase());
		doctor.setEspecialidad(especialidad);
		doctor.setEstado(estado);
		persist(doctor);
		return doctor;
	}

	// @Programmatic
	// public List<EspecialidadEnum> listarEspecialidad() {
	// return especialidadServicio.listarEspecialidades();
	// }

	@MemberOrder(name = "Doctor", sequence = "3.2")
	public List<Doctor> listarDoctores() {
		return container.allInstances(Doctor.class);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
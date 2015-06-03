package dom.Paciente;

import java.util.List;
import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

@DomainService(repositoryFor = Paciente.class)
@DomainServiceLayout(named = "Paciente", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
public class PacienteServicio extends AbstractFactoryAndRepository {
	@MemberOrder(name = "Paciente", sequence = "5.1")
	public Paciente crearPaciente(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final long documento,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Legajo") final int legajo) {

		final Paciente paciente = newTransientInstance(Paciente.class);
		paciente.setApellido(apellido.toUpperCase());
		paciente.setNombre(nombre.toUpperCase());
		paciente.setDocumento(documento);
		paciente.setDireccion(direccion.toUpperCase());
		paciente.setCorreo(correo);
		paciente.setTelefono(telefono);
		paciente.setLegajo(legajo);
		persist(paciente);
		return paciente;
	}

	@MemberOrder(name = "Paciente", sequence = "5.2")
	public List<Paciente> listarPacientes() {
		return container.allInstances(Paciente.class);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
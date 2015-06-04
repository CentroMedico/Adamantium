package dom.Dueño;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

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
			@ParameterLayout(named = "Iniciales") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.INICIALES) final String iniciales,
			@ParameterLayout(named = "Estado") final EstadoEnum estado) {

		final Dueño dueño = newTransientInstance(Dueño.class);
		dueño.setApellido(apellido.toUpperCase());
		dueño.setNombre(nombre.toUpperCase());
		dueño.setDocumento(documento);
		dueño.setDireccion(direccion.toUpperCase());
		dueño.setCorreo(correo);
		dueño.setTelefono(telefono);
		dueño.setIniciales(iniciales.toUpperCase());
		dueño.setEstado(estado);
		persist(dueño);
		return dueño;
	}

	@MemberOrder(name = "Dueño", sequence = "1.2")
	public List<Dueño> listarDueños() {
		return container.allInstances(Dueño.class);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
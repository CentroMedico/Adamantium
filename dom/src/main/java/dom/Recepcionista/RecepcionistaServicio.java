package dom.Recepcionista;

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

@DomainService(repositoryFor = Recepcionista.class)
@DomainServiceLayout(named = "Recepcionista", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "4")
public class RecepcionistaServicio extends AbstractFactoryAndRepository {

	@MemberOrder(name = "Recepcionista", sequence = "4.1")
	public Recepcionista crearRecepcionista(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String apellido,
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Documento") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaDoc.DOCUMENTO) final long documento,
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaNombres.REFERENCIA) final String direccion,
			@ParameterLayout(named = "Correo") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaMail.EMAIL) final String correo,
			@ParameterLayout(named = "Telefono") @Parameter(regexPattern = dom.Regex.RegexValidation.ValidaTel.NUMEROTEL) final String telefono,
			@ParameterLayout(named = "Legajo") final int legajo,
			@ParameterLayout(named = "Estado") final EstadoEnum estado) {

		final Recepcionista recepcionista = newTransientInstance(Recepcionista.class);
		recepcionista.setApellido(apellido.toUpperCase());
		recepcionista.setNombre(nombre.toUpperCase());
		recepcionista.setDocumento(documento);
		recepcionista.setDireccion(direccion.toUpperCase());
		recepcionista.setCorreo(correo);
		recepcionista.setTelefono(telefono);
		recepcionista.setLegajo(legajo);
		recepcionista.setLegajo(legajo);
		persist(recepcionista);
		return recepcionista;
	}

	@MemberOrder(name = "Recepcionista", sequence = "4.2")
	public List<Recepcionista> listarRecepcionista() {
		return container.allInstances(Recepcionista.class);
	}

	@MemberOrder(name = "Recepcionista", sequence = "4.3")
	public List<Recepcionista> listarRecepcionistasActivos() {
		return allMatches(Recepcionista.class, new Predicate<Recepcionista>() {

			@Override
			public boolean apply(Recepcionista input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Activo ? true : false;
			}
		});
	}

	@MemberOrder(name = "Recepcionista", sequence = "4.3")
	public List<Recepcionista> listarRecepcionistasInactivos() {
		return allMatches(Recepcionista.class, new Predicate<Recepcionista>() {

			@Override
			public boolean apply(Recepcionista input) {
				// TODO Auto-generated method stub
				return input.getEstado() == EstadoEnum.Inactivo ? true : false;
			}
		});
	}

	@MemberOrder(name = "Recepcionista", sequence = "4.4")
	public void buscarRecepcionista() {

	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
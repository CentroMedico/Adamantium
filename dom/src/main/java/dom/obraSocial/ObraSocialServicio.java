package dom.obraSocial;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import com.google.common.base.Predicate;

import dom.estado.EstadoEnum;

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
	public ObraSocial crearObraSocial(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Cobertura Medica") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaCobertura.COBERTURA) final String cobertura) {
		final ObraSocial obraSocial = newTransientInstance(ObraSocial.class);
		obraSocial.setNombre(nombre.substring(0, 1).toUpperCase()
				+ nombre.substring(1));
		obraSocial.setCoberturaMedica(cobertura.substring(0, 1).toUpperCase()
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

	@javax.inject.Inject
	DomainObjectContainer container;
}
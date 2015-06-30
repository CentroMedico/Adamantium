package dom.obraSocial;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

@DomainService(repositoryFor = ObraSocial.class)
@DomainServiceLayout(named = "Obra Social")
public class ObraSocialServicio extends AbstractFactoryAndRepository {
	/**
	 * Retorna el nombre del icono para el Doctor
	 * 
	 * @return String
	 */
	public String iconName() {
		return "ObraSocial";
	}

	@MemberOrder(name = "Recepcionista", sequence = "4.5")
	public ObraSocial crearObraSocial(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String nombre,
			@ParameterLayout(named = "Cobertura Medica") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaCobertura.COBERTURA) final String cobertura) {
		final ObraSocial obraSocial = newTransientInstance(ObraSocial.class);
		obraSocial.setNombre(nombre);
		obraSocial.setCoberturaMedica(cobertura);
		persist(obraSocial);
		return obraSocial;
	}

	/**
	 * // * Obtiene una lista de todos las ObrasSociales // * // * @return
	 * listaDeObrasSociales List<ObraSocial> //
	 */
	@MemberOrder(name = "Recepcionista", sequence = "4.6")
	public List<ObraSocial> listarObraSocial() {
		final List<ObraSocial> listaObraSocial = allInstances(ObraSocial.class);
		return listaObraSocial;
	}

}
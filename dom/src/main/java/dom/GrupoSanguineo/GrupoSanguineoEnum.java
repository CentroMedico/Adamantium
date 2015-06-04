package dom.GrupoSanguineo;

import javax.inject.Inject;
import org.apache.isis.applib.DomainObjectContainer;

public enum GrupoSanguineoEnum {

	// O- O+ A− A+ B− B+ AB− AB+

	OMenos("0-"), OMas("0+"), AMenos("A-"), AMas("A+"), BMenos("B-"), BMas("B+"), ABMenos(
			"AB-"), ABMas("AB+");
	private final String nombre;

	// private final Class type;
	public String getNombre() {
		return nombre;
	}

	private GrupoSanguineoEnum(String nom) {
		nombre = nom;
	}
	@Override
	public String toString() {
		return this.nombre;
	}

	@Inject
	private IGrupoSanguineo iGrupoSanguineo;
	@Inject
	private DomainObjectContainer container;

}

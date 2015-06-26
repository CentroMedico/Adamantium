package dom.tipoDeSexo;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
/**
 * Clase enumerada, de la cual agrega el tipo de Sexo.
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
public enum TipoDeSexoEnum {
	M("Masculino"), F("Femenino"), OTRO("Otros");
	
	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private TipoDeSexoEnum(String nom) {

		nombre = nom;

	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Inject
	private ITipoDeSexo itipodesexo;
	@Inject
	private DomainObjectContainer container;

}

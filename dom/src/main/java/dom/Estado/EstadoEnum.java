package dom.Estado;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;

public enum EstadoEnum {
	Activo("Activo"), Inactivo("Inactivo");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private EstadoEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Inject
	private IEstado iestado;
	@Inject
	private DomainObjectContainer container;

}

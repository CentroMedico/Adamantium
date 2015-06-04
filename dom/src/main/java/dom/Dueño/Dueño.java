package dom.Dueño;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

import dom.Estado.EstadoEnum;
import dom.Persona.Persona;


//Primera Estrategia: Una tabla por cada clase con la superclase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Dueño extends Persona {

	// {{ Iniciales (property)
	private String iniciales;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getIniciales() {
		return iniciales;
	}
	public void setIniciales(final String iniciales) {
		this.iniciales = iniciales;
	}

	// }}

	// {{ Estado (property)
	private EstadoEnum estado;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}
	
	@Inject
	private DueñoServicio dueñoServicio;
	@Inject
	private DomainObjectContainer container;
}
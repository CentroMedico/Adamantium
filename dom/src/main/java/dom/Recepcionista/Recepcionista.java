package dom.Recepcionista;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

import dom.Estado.EstadoEnum;
import dom.Persona.Persona;

//Primera Estrategia: Una tabla por cada clase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Recepcionista extends Persona {

	// {{ Legajo (property)
	private int legajo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(final int legajo) {
		this.legajo = legajo;
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

	@Inject
	private RecepcionistaServicio recepcionistaServicio;
	@Inject
	private DomainObjectContainer container;

}
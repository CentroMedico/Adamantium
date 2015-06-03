package dom.Doctor;


import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

import dom.Persona.Persona;

//Primera Estrategia: Una tabla por cada clase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable

public class Doctor extends Persona {

	// {{ Matricula (property)
	private String matricula;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(final String matricula) {
		this.matricula = matricula;
	}

	@Inject
	private DoctorServicio doctorServicio;
	@Inject
	private DomainObjectContainer container;

}

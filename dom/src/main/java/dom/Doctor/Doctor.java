package dom.Doctor;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import dom.Especialidad.EspecialidadEnum;
import dom.Estado.EstadoEnum;
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

	// {{ Especialidad (property)
	private EspecialidadEnum especialidad;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public EspecialidadEnum getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(final EspecialidadEnum especialidad) {
		this.especialidad = especialidad;
	}

	// {{ Estado (property)
	private EstadoEnum estado;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}
	@Inject
	private DoctorServicio doctorServicio;
	@Inject
	private DomainObjectContainer container;

}
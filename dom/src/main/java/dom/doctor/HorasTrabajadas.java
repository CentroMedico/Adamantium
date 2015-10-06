package dom.doctor;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;

@PersistenceCapable
public class HorasTrabajadas {

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}

	// {{ Ingreso (property)
	private String ingresoEgreso;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getIngreso() {
		return ingresoEgreso;
	}

	public void setIngreso(final String ingresoEgreso) {
		this.ingresoEgreso = ingresoEgreso;
	}
	// }}

}

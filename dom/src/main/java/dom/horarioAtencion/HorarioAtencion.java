//package dom.horarioAtencion;
//
//import org.apache.isis.applib.annotation.MemberOrder;
//
//import javax.jdo.annotations.Column;
//import javax.jdo.annotations.PersistenceCapable;
//
//import dom.doctor.Doctor;
//
//@PersistenceCapable
//public class HorarioAtencion {
//
//	// {{ Doctor (property)
//	private Doctor doctor;
//
//	@MemberOrder(sequence = "1")
//	@Column(allowsNull = "false")
//	public Doctor getDoctor() {
//		return doctor;
//	}
//
//	public void setDoctor(final Doctor doctor) {
//		this.doctor = doctor;
//	}
//
//	// }}
//
//	// {{ RangoDesde (property)
//	private String rangoDesde;
//
//	@MemberOrder(sequence = "2")
//	@Column(allowsNull = "false")
//	public String getRangoDesde() {
//		return rangoDesde;
//	}
//
//	public void setRangoDesde(final String rangoDesde) {
//		this.rangoDesde = rangoDesde;
//	}
//
//	// }}
//
//	public String validateRangoDesde(String desde) {
//
//		if (desde.matches("[0-9,:]+") == false) {
//			return "Datos erroneos, vuelva a intentarlo";
//		} else {
//			return null;
//		}
//	}
//
//	// {{ RangoHasta (property)
//	private String rangoHasta;
//
//	@MemberOrder(sequence = "3")
//	@Column(allowsNull = "false")
//	public String getRangoHasta() {
//		return rangoHasta;
//	}
//
//	public void setRangoHasta(final String rangoHasta) {
//		this.rangoHasta = rangoHasta;
//	}
//
//	// }}
//
//	public String validateRangoHasta(String hasta) {
//
//		if (hasta.matches("[0-9,:]+") == false) {
//			return "Datos erroneos, vuelva a intentarlo";
//		} else {
//			return null;
//		}
//	}
//
//	// {{ DiaDeLaSemana (property)
//	private DiaSemanaEnum diaDeLaSemana;
//
//	@MemberOrder(sequence = "4")
//	@Column(allowsNull = "false")
//	public DiaSemanaEnum getDiaDeLaSemana() {
//		return diaDeLaSemana;
//	}
//
//	public void setDiaDeLaSemana(final DiaSemanaEnum diaDeLaSemana) {
//		this.diaDeLaSemana = diaDeLaSemana;
//	}
//	// }}
//
// }
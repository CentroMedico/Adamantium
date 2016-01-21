package dom.reportes;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteAgenda
{
	private String dia;
	private String Doctor;
	private String estado;
	
	@MemberOrder(sequence = "1")
	public String getDia() {
		return dia;
	}
	
	public void setDia(String dia) {
		this.dia = dia;
	}
	@MemberOrder(sequence = "1")
	public String getDoctor() {
		return Doctor;
	}
	public void setDoctor(String doctor) {
		Doctor = doctor;
	}
	@MemberOrder(sequence = "1")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
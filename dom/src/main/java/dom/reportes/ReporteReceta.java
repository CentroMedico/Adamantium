package dom.reportes;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteReceta 
{
	private String paciente;
	private String obraSocial;
	private String medicamento;
	private String medicamento1;
	private String doctor;
	
	@MemberOrder(sequence = "1")
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	@MemberOrder(sequence = "1")
	public String getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}
	@MemberOrder(sequence = "1")
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	@MemberOrder(sequence = "1")
	public String getMedicamento1() {
		return medicamento1;
	}
	public void setMedicamento1(String medicamento1) {
		this.medicamento1 = medicamento1;
	}
	@MemberOrder(sequence = "1")
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	

}

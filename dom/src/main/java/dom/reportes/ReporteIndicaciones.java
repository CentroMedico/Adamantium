package dom.reportes;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteIndicaciones
{
	private String paciente;
	private String medicamento;
	private String comotomarlo;
	private String doctor;
	private String matricula;
	
	@MemberOrder(sequence = "1")
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	@MemberOrder(sequence = "1")
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	@MemberOrder(sequence = "1")
	public String getComotomarlo() {
		return comotomarlo;
	}
	public void setComotomarlo(String comotomarlo) {
		this.comotomarlo = comotomarlo;
	}
	@MemberOrder(sequence = "1")
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	@MemberOrder(sequence = "1")
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	
	
}

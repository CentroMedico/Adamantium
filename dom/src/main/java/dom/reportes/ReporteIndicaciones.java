/*
 Copyright 2015 Adamantium

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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

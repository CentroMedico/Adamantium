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
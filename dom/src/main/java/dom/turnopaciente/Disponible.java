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
package dom.turnopaciente;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.agendadoctor.AgendaDoctor;
import dom.doctor.Doctor;
import dom.paciente.Paciente;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "idTurnoDisponible")
public class Disponible implements IEstadoTurno {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Turno Disponible.");
	}

	private TurnoPaciente turno;

	private TurnoPaciente getTurno() {
		return turno;
	}

	private void setTurno(TurnoPaciente turno) {
		this.turno = turno;
	}

	public Disponible(TurnoPaciente turno) {
		this.setTurno(turno);
	}

	@Override
	public void disponerTurno() {
		turno.mostarMensajeUsuario("El turno ya se encuentra disponible");

	}

	@Override
	public void solicitarTurno(Doctor doctor, Paciente paciente) {
		this.getTurno().setDoctor(doctor);
		this.getTurno().setPaciente(paciente);
		// paciente.addListaTurnos(turno);
		this.getTurno().setEstado(this.getTurno().getSolicitado());
	}

	@Override
	public void aceptarTurno() {
		turno.mostarMensajeUsuario("El turno ya se encuentra disponible, se debe solicitar antes de aceptar");

	}

	@Override
	public void atenderTurno() {
		turno.mostarMensajeUsuario("El turno se encuentra disponible, se debe solicitar antes de atender");

	}

	@Override
	public void cancelarTurno() {
		turno.mostarMensajeUsuario("El turno se encuentra disponible, no se puede cancelar");

	}
}
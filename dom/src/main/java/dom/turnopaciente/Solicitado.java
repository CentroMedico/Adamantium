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

import dom.doctor.Doctor;
import dom.paciente.Paciente;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "idTurnoSolicitado")
public class Solicitado implements IEstadoTurno {

	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Turno Solicitado.");
	}

	private TurnoPaciente turno;

	private TurnoPaciente getTurno() {
		return turno;
	}

	private void setTurno(TurnoPaciente turno) {
		this.turno = turno;
	}

	public Solicitado(TurnoPaciente turno) {
		this.setTurno(turno);
		// this.getTurno().setDisponerOcultado(true);
		// this.getTurno().setSolicitarOcultado(true);
		// this.getTurno().setAceptarOcultado(false);
		// this.getTurno().setCancelarOcultado(false);
		// this.getTurno().setAtenderOcultado(true);
	}

	@Override
	public void disponerTurno() {
		turno.mostarMensajeUsuario("El turno se encuentra solicitado, se debe cancelar para poder disponer");

	}

	@Override
	public void solicitarTurno(Doctor doctor, Paciente paciente) {
		turno.mostarMensajeUsuario("El turno ya se encuentra solicitado");

	}

	@Override
	public void aceptarTurno() {
		this.getTurno().setEstado(this.getTurno().getAceptado());

	}

	@Override
	public void atenderTurno() {
		turno.mostarMensajeUsuario("El turno se encuentra solicitado, se debe aceptar antes de atender");

	}

	@Override
	public void cancelarTurno() {
		this.getTurno().setEstado(this.getTurno().getCancelado());

	}

}
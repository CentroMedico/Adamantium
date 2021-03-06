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

import dom.doctor.Doctor;
import dom.paciente.Paciente;

/**
 * Interface para implementar los distintos tipos de Estado de los turnos
 * 
 * @author Adamantium
 * @since 01/08/2015
 * @version 1.0.0
 */

public interface IEstadoTurno {

	public void disponerTurno();

	public void solicitarTurno(final Doctor doctor, final Paciente paciente);

	public void aceptarTurno();

	public void atenderTurno();

	public void cancelarTurno();

}
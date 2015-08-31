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
package dom.tipoDocumento;
/**
 * Clase enumerada, de la cual agrega el Grupo Sanguineo de un Paciente.
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
public enum TipoDocumentoEnum {
	DNI("DNI: Documento Nacional de Identidad"), LC("LC: Libreta Civica"), LE(
			"LE:  Libreta de Enrolamiento"), CI("CI: Cedula de Identidad"), PASAPORTE(
			"Pasaporte");
	private final String nombre;

	// private final Class type;
	public String getNombre() {
		return nombre;
	}

	private TipoDocumentoEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
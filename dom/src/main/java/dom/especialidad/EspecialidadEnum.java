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
package dom.especialidad;

/**
 * Clase enumerada, de la cual agrega la especialidad de un doctor.
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
public enum EspecialidadEnum {
	Clinica_General("Clinica General"), Dermatologia("Dermatologia"), Nutricion(
			"Nutricion"), Medicina_de_urgencias("Medicina de Urgencias"), Pediatría(
			"Pediatría");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private EspecialidadEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
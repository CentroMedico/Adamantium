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
package dom.Especialidad;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;

public enum EspecialidadEnum {
	Clinica_General("Clinica General"), Dermatologia("Dermatologia"), Nutricion(
			"Nutricion"), Alergología("Alergología"), Anestesiología_y_reanimación(
			"Anestesiología y Reanimación"), Cardiología("Cardiología"), Gastroenterología(
			"Gastroenterología"), Endocrinología("Endocrinología"), Geriatría(
			"Geriatría"), Hematología_y_hemoterapia("Hematología y Hemoterapia"), Hidrología_médica(
			"Hidrología Médica"), Infectología("Infectología"), Medicina_aeroespacial(
			"Medicina Aeroespacial"), Medicina_del_deporte(
			"Medicina del Deporte"), Medicina_del_trabajo(
			"Medicina del Trabajo"), Medicina_de_urgencias(
			"Medicina de Urgencias"), Medicina_familiar_y_comunitaria(
			"Medicina Familiar y Comunitaria"), Medicina_intensiva(
			"Medicina Intensiva"), Medicina_interna("Medicina Interna"), Medicina_legal_y_forense(
			"Medicina Legal y Forense"), Medicina_preventiva_y_salud_pública(
			"Medicina Preventiva y Salud Pública"), Nefrología("Nefrología"), Neumología(
			"Neumología"), Neurología("Neurología"), Nutriología("Nutriología"), Odontología(
			"Odontología"), Oftalmología("Oftalmología"), Oncología_médica(
			"Oncología Médica"), Oncología_radioterápica(
			"Oncología Radioterápica"), Otorrinolaringología(
			"Otorrinolaringología"), Pediatría("Pediatría"), Proctología(
			"Proctología"), Psiquiatría("Psiquiatría"), Rehabilitación(
			"Rehabilitación"), Reumatología("Reumatología"), Traumatología(
			"Traumatología"), Toxicología("Toxicología"), Urología("Urología");

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

	@Inject
	private IEspecialidad iespecialidad;
	@Inject
	private DomainObjectContainer container;
}

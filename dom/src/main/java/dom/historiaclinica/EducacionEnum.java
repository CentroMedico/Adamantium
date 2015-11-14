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

package dom.historiaclinica;
/**
* Clase enumerada, de la cual se agrega estudios al Paciente.
* 
* @author Adamantium
* @since 16/10/2015
* @version 1.0.0
*/

public enum EducacionEnum {

	Analfabeto("Analfabeto"),PrimariaIncompleto("Primaria Incompleto"),PrimariaCompleto("Primaria Completo"),
	SecundarioIncompleto("Secundario Incompleto"),SecundarioCompleto("Secundaario Completo"),
	TerciarioUniversitario("Terciario-Universitario");
	
	private final String nombre;

	public String getNombre() {
		return nombre;
	}
	private EducacionEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
}
}
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
package dom.dias;
/**
 * Clase enumerada, de la cual agrega las horas de la mañana,
 * 
 * @author Adamantium
 * @since 01/08/2015
 * @version 1.0.0
 */
public enum HorasMañanaEnum {

	Siete("07:00 am"), SieteTreinta("07:30 am"), Ocho("08:00 am"), OchoTreinta(
			"08:30 am"), Nueve("09:00 am"), NueveTreinta("09:30 am"), Diez(
			"10:30 am"), DiezTreinta("10:30 am"), Once("11:00 am"), OnceTreinta(
			"11:30 am"), Doce("12:00 am");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private HorasMañanaEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
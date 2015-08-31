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
 * Clase enumerada, de la cual agrega las horas de la tarde.
 * 
 * @author Adamantium
 * @since 01/08/2015
 * @version 1.0.0
 */
public enum HorasTardeEnum {
	DoceTreinta("12:30 pm"), Trece("13:00 pm"), TreceTreinta("13:30 pm"), Catorce(
			"14:00 pm"), CatorceTreinta("14:30 am"), Quince("15:00 pm"), QuinceTreinta(
			"15:30 pm"), Dieciseis("16:00 pm"), DeciseisTreinta("16:30 pm"), Diecisiete(
			"17:00 pm"), DiecisieteTreinta("17:30 pm"), Dieciocho("18:00 pm"), DieciochoTreinta(
			"18:30 pm");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private HorasTardeEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
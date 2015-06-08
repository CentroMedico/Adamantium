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
package dom.GrupoSanguineo;

import javax.inject.Inject;
import org.apache.isis.applib.DomainObjectContainer;

public enum GrupoSanguineoEnum {

	// O- O+ A− A+ B− B+ AB− AB+

	OMenos("0-"), OMas("0+"), AMenos("A-"), AMas("A+"), BMenos("B-"), BMas("B+"), ABMenos(
			"AB-"), ABMas("AB+");
	private final String nombre;

	// private final Class type;
	public String getNombre() {
		return nombre;
	}

	private GrupoSanguineoEnum(String nom) {
		nombre = nom;
	}
	@Override
	public String toString() {
		return this.nombre;
	}

	@Inject
	private IGrupoSanguineo iGrupoSanguineo;
	@Inject
	private DomainObjectContainer container;

}

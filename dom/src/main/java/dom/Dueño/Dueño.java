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
package dom.Dueño;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.Estado.EstadoEnum;
import dom.Persona.Persona;


//Primera Estrategia: Una tabla por cada clase con la superclase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Dueño extends Persona {
	
	public TranslatableString title() {
        return TranslatableString.tr("{nombre}", "nombre", "Dueño");
    }
	
	public String iconName()
	{
		return "dueño";	
	}

	// {{ Iniciales (property)
	private String iniciales;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getIniciales() {
		return iniciales;
	}
	public void setIniciales(final String iniciales) {
		this.iniciales = iniciales;
	}

	// }}

	// {{ Estado (property)
	private EstadoEnum estado;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}
	
	@Inject
	private DueñoServicio dueñoServicio;
	@Inject
	private DomainObjectContainer container;
}
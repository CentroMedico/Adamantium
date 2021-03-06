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
package dom.factura;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

@PersistenceCapable
public class ItemFactura {

	/**
	 * Retorna el nombre del icono del item de la factura
	 * 
	 * @return String
	 */
	public String iconName() {
		return "ItemFactura";
	}

	/**
	 * Constructor de la clase ItemFactura
	 * 
	 * @return String
	 */
	public ItemFactura() {
		// TODO Auto-generated constructor stub
	}

	// {{ Nombre (property)
	private String nombre;

	/**
	 * Obtiene el nombre de una nueva Factura
	 * 
	 * @return nombre String
	 */
	@Title
	@PropertyLayout(hidden = Where.ALL_TABLES)
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre de una factura
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	// {{ Precio (property)
	private Double precio;

	/**
	 * Obtiene el precio de una nueva Factura
	 * 
	 * @return precio double
	 */
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Double getPrecio() {
		return precio;
	}

	/**
	 * Setea el precio de una nueva Factura
	 * 
	 * @param precio
	 *            double
	 */
	public void setPrecio(final Double precio) {
		this.precio = precio;
	}

	// // {{ Motivo (property)
	// private String motivo;
	//
	// @Title
	// @PropertyLayout(hidden = Where.ALL_TABLES)
	// @MemberOrder(sequence = "3")
	// @Column(allowsNull = "false")
	// @Property(editing = Editing.DISABLED)
	// public String getMotivo() {
	// return motivo;
	// }
	//
	// public void setMotivo(final String motivo) {
	// this.motivo = motivo;
	// }
	// // }}

}
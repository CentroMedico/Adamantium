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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Sequence;
import javax.jdo.annotations.SequenceStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.paciente.Paciente;
import dom.turnopaciente.TurnoPaciente;

@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "traerFacturas", language = "JDOQL", value = "SELECT "
		+ " FROM dom.factura.Factura"), })
@Sequence(name = "secuenciaNumeroFactura", strategy = SequenceStrategy.CONTIGUOUS)
@PersistenceCapable
@DomainObject(autoCompleteRepository = FacturaServicio.class, autoCompleteAction = "autocompleteFactura")
public class Factura {

	/**
	 * Representa en UI el nombre "Factura" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Factura N° " + this.getNumeroFactura() + ". Del paciente: "
						+ this.getPaciente().getApellido() + ", "
						+ this.getPaciente().getNombre());
	}

	/**
	 * Retorna el nombre del icono de una nueva Factura
	 * 
	 * @return String
	 */
	public String iconName() {
		return "Factura";
	}

	/**
	 * Constructor de la clase Factura
	 */
	public Factura() {

	}

	// {{ NumeroFactura (property)
	private long numeroFactura;

	/**
	 * Obtiene el numero de una nueva Factura, el cual se genera en forma
	 * automatica
	 * 
	 * @return numero long
	 */
	@Title(prepend = "Factura Nº ")
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence = "secuenciaNumeroFactura")
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public long getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * Setea el numero de una Factura
	 * 
	 * @param numero
	 *            long
	 */
	public void setNumeroFactura(final long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	// {{ FechaHora (property)
	private Date fechaHora;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(final Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	// }}

	// {{ Paciente (property)
	private Paciente paciente;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	// {{ Turno (property)
	private TurnoPaciente turno;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public TurnoPaciente getTurno() {
		return turno;
	}

	public void setTurno(final TurnoPaciente turno) {
		this.turno = turno;
	}

	// }}

	// {{ Total (property)
	private double total;

	/**
	 * Obtiene el total de la Factura que se va a crear.
	 * 
	 * @return total double
	 */
	// @Named("Total ($)")
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public double getTotal() {
		return total;
	}

	/**
	 * Setea el total de la Factura
	 * 
	 * @param total
	 *            double
	 */
	public void setTotal(final double total) {
		this.total = total;
	}

	// }}

	// {{ Items (Collection)
	private List<ItemFactura> items = new ArrayList<ItemFactura>();

	/**
	 * Obtiene una lista de los items de la factura
	 * 
	 * @return items List<Itemfactura>
	 */
	@Join
	// @Named("Detalle")
	// @Render(Type.EAGERLY)
	@MemberOrder(sequence = "6")
	@CollectionLayout(render = RenderType.EAGERLY)
	public List<ItemFactura> getItems() {
		return items;
	}

	/**
	 * Setea la lista de los items de la factura
	 * 
	 * @param items
	 *            List<Itemfactura>
	 */
	public void setItems(final List<ItemFactura> items) {
		this.items = items;
	}

	/**
	 * Agrega un item a la lista de items de la factura
	 * 
	 * @param _item
	 *            List<Itemfactura>
	 */
	public void addToItems(final ItemFactura _item) {
		items.add(_item);
	}

	/**
	 * remueve un item de la lista de items de la factura
	 * 
	 * @param _item
	 *            List<Itemfactura>
	 */
	public void removeFromItems(final ItemFactura _item) {
		items.remove(_item);
	}

	@SuppressWarnings("unused")
	@Inject
	private DomainObjectContainer contenedor;
}
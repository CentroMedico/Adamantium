package dom.factura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Sequence;
import javax.jdo.annotations.SequenceStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.TypicalLength;

import dom.paciente.Paciente;

import org.apache.isis.applib.annotation.Render.Type;
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name = "traerFacturas", language = "JDOQL", value = "SELECT "
		+ " FROM dom.factura.Factura"), })
	@Sequence(name = "secuenciaNumeroFactura", strategy = SequenceStrategy.CONTIGUOUS)
	@PersistenceCapable(identityType = IdentityType.DATASTORE)
	@DomainObject(autoCompleteRepository =FacturaServicio.class, autoCompleteAction ="autocompleteFactura")
	public class Factura {
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

		// {{ Numero (property)
		private long numero;

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
		public long getNumero() {
			return numero;
		}

		/**
		 * Setea el numero de una Factura
		 * 
		 * @param numero
		 *            long
		 */
		public void setNumero(final long numero) {
			this.numero = numero;
		}

		// {{ Total (property)
		private double total;

		/**
		 * Obtiene el total de la Factura que se va a crear.
		 * 
		 * @return total double
		 */
//		@Named("Total ($)")
		@MemberOrder(sequence = "1")
		@Column(allowsNull = "false")
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
//		@Named("Detalle")
//		@Render(Type.EAGERLY)
		@MemberOrder(sequence = "1")
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

		// {{ FechaHora (property)
		private Date fechaHora;

		@MemberOrder(sequence = "1")
		@Column(allowsNull = "false")
		public Date getFechaHora() {
			return fechaHora;
		}

		public void setFechaHora(final Date fechaHora) {
			this.fechaHora = fechaHora;
		}

		// }}
		
		//{{ Paciente (property)
		private Paciente paciente;
		
		@MemberOrder(sequence = "1")
		@Column(allowsNull = "false")
		public Paciente getPaciente()
		{
			return paciente;
		}
		public void setPaciente(Paciente paciente)
		{
			this.paciente=paciente;
		}
		
		@SuppressWarnings("unused")
		@Inject
		private DomainObjectContainer contenedor;
	}

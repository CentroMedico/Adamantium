package dom.obraSocial;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

import javax.swing.JOptionPane;

import dom.doctor.DoctorServicio;
import dom.estado.EstadoEnum;

@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.obraSocial.ObraSocial "),

		@javax.jdo.annotations.Query(name = "buscarNombre", language = "JDOQL", value = "SELECT "
				+ "FROM dom.obraSocial.ObraSocial "
				+ "WHERE nombre == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0") })
@DomainObject(autoCompleteRepository = ObraSocialServicio.class, autoCompleteAction = "buscarObraSocial")
@PersistenceCapable
public class ObraSocial {
	/**
	 * Representa en UI el nombre "Obra Social" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre", "Obra Social");
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "obraSocial";
	}

	// {{ Nombre (property)
	private String nombre;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	// }}
	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param obr
	 * @return String
	 */

	public String validateNombre(String obr) {

		if (obr.matches("[a-z,A-Z,0-9,ñ,Ñ, ]+") == false) {
			return "Datos incorrectos, por favor vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ CoberturaMedica (property)
	private String coberturaMedica;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public String getCoberturaMedica() {
		return coberturaMedica;
	}

	public void setCoberturaMedica(final String coberturaMedica) {
		this.coberturaMedica = coberturaMedica;
	}

	// }}

	/**
	 * Valida que no se introduzcan datos erroneos
	 * 
	 * @param cob
	 * @return String
	 */

	public String validateCoberturaMedica(String cob) {

		if (cob.matches("[a-z, A-Z,0-9,-]+") == false) {
			return "Datos erroneos, vuelva a intentarlo";
		} else {
			return null;
		}
	}

	// {{ Estado (property)
	private EstadoEnum estado;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(final EstadoEnum estado) {
		this.estado = estado;
	}

	// }}

	public void InactivarObraSocial() {

		int resp = JOptionPane.showConfirmDialog(null,
				"¿Seguro que desea eliminar?");

		if (resp == JOptionPane.YES_OPTION) {
			if (this.estado == EstadoEnum.Inactivo) {
				JOptionPane.showMessageDialog(null,
						"La obra social ya se encuentra inactiva");
			} else {
				this.setEstado(EstadoEnum.Inactivo);
				JOptionPane.showMessageDialog(null,
						"Obra social inactivada correctamente");
			}

		} else if (resp == JOptionPane.NO_OPTION) {
		} else if (resp == JOptionPane.CANCEL_OPTION) {
		}
	}
}
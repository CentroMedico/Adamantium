package dom.agendadoctor;

/**
 * Entidad Turno la cual representa a los turnos que va tener disponible el Doctor.
 * 
 * 
 * @author Adamantium
 * @since 01/08/2015
 * @version 1.0.0
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.i18n.TranslatableString;

import dom.doctor.Doctor;
import dom.paciente.Paciente;

@javax.jdo.annotations.Queries({

		@javax.jdo.annotations.Query(name = "traerPorDoctor", language = "JDOQL", value = "SELECT "
				+ "FROM dom.agendadoctor.AgendaDoctor WHERE doctor == :doctor ORDER BY dia"),
		@javax.jdo.annotations.Query(name = "traerTurnosDisponibles", language = "JDOQL", value = "SELECT "
				+ "FROM dom.agendadoctor.AgendaDoctor WHERE estado == 'Disponible' "),
		@javax.jdo.annotations.Query(name = "traerTurnos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.agendadoctor.AgendaDoctor"),
		@javax.jdo.annotations.Query(name = "traerTurnosDisponiblesDoctor", language = "JDOQL", value = "SELECT "
				+ "FROM dom.agendadoctor.AgendaDoctor WHERE estado == 'Disponible' && doctor== :doctor ORDER BY dia") })
@PersistenceCapable
public class AgendaDoctor {

	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	public TranslatableString title() {
		final SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"dd MMMM YYYY HH:mm");
		return TranslatableString.tr("{nombre}", "nombre", "Turno "
				+ formatoFecha.format(this.getDia()));
	}

	/**
	 * Obtiene el nombre del icono.
	 */

	public String iconName() {
		return "calendario";
	}

	/**
	 * 
	 */

	// {{ Dia (property)
	private Date dia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener un dia 
	 * 
	 * @return dia Date
	 */
	public Date getDia() {
		return dia;
	}

	/**
	 * Setea el dia que se va a crear.
	 * 
	 * @param dia
	 *            dia
	 */
	public void setDia(final Date dia) {
		this.dia = dia;
	}

	// }}

	// {{ Doctor (property)
	private Doctor doctor;

	@MemberOrder(sequence = "2")
	// @Persistent(table = "lista_agenda", mappedBy = "listaAgenda")
	// @Join(column = "agenda_id")
	@Column(allowsNull = "false")
	/**
	 * Pemite obtener un doctor 
	 * 
	 * @return doctor Doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Setea el Doctor al que se le va asignar los turnos.
	 * 
	 * @param doctor
	 *            doctor
	 */
	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	// }}

	// {{ Estado (property)
	private String estado;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public String getEstado() {
		return estado;
	}

	public void setEstado(final String estado) {
		this.estado = estado;
	}

	// }}

}
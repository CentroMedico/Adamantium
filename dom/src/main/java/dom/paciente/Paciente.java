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
package dom.paciente;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Sequence;
import javax.jdo.annotations.SequenceStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.isisaddons.wicket.gmap3.cpt.applib.Locatable;
import org.isisaddons.wicket.gmap3.cpt.applib.Location;
import org.isisaddons.wicket.gmap3.cpt.service.LocationLookupService;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import dom.gruposanguineo.GrupoSanguineoEnum;
import dom.obrasocial.ObraSocial;
import dom.paciente.grafico.MayoriaEdadEnum;
import dom.paciente.grafico.RangoEdadEnum;
import dom.persona.Persona;
import dom.turnopaciente.TurnoPaciente;
import dom.turnopaciente.TurnoPacienteServicio;

/**
 * Entidad Paciente la cual representa a cualquier persona que se haga atender
 * en el centro medico. Extiende de la clase Persona.
 * 
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
// Primera Estrategia: Una tabla por cada clase
// @PersistenceCapable(identityType = IdentityType.DATASTORE)
// @Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
// Segunda Estrategia: Una tabla por cada clase, solo las subclases
@Sequence(name = "numeroLegajo", strategy = SequenceStrategy.CONTIGUOUS)
@javax.jdo.annotations.Queries({

		@javax.jdo.annotations.Query(name = "traerCiudades", language = "JDOQL", value = "SELECT "
				+ "FROM dom.turno.Turno"),
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.paciente.Paciente "),
		@javax.jdo.annotations.Query(name = "buscarNombre,Apellido,Id", language = "JDOQL", value = "SELECT "
				+ "FROM dom.paciente.Paciente "
				+ "WHERE documento == :parametro || nombre.indexOf(:parametro) == 0 "
				+ " && nombre.indexOf(:parametro) >= 0 || apellido.indexOf(:parametro) == 0 "
				+ " && apellido.indexOf(:parametro) >= 0 "),
		@javax.jdo.annotations.Query(name = "buscarDocDuplicados", language = "JDOQL", value = "SELECT "
				+ "FROM dom.paciente.Paciente "
				+ " WHERE documento ==:documento"),
		@javax.jdo.annotations.Query(name = "traerPaciente", language = "JDOQL", value = "SELECT "
				+ " FROM dom.paciente.Paciente "),
		@javax.jdo.annotations.Query(name = "traerPacientesActivos", language = "JDOQL", value = "SELECT "
				+ " FROM dom.paciente.Paciente WHERE estado == 'Activo'"),

})
@DomainObject(autoCompleteRepository = TurnoPacienteServicio.class, autoCompleteAction = "buscarPaciente")
@PersistenceCapable
public class Paciente extends Persona implements Locatable {
	/**
	 * Representa en UI el nombre "Paciente" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				"Paciente: " + this.getApellido() + ", " + this.getNombre());
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "paciente";
	}

	// {{ Legajo (property)
	private int legajo;

	/**
	 * Pemite obtener el legajo de un Paciente
	 * 
	 * @return legajo int
	 */
	@MemberOrder(sequence = "0")
	@Column(allowsNull = "false")
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence = "numeroLegajo")
	@Property(editing = Editing.DISABLED)
	public int getLegajo() {
		return legajo;
	}

	/**
	 * Setea el legajo que se va a crear.
	 * 
	 * @param legajo
	 *            legajo
	 */
	public void setLegajo(final int legajo) {
		this.legajo = legajo;
	}

	// }}

	// {{ FechaNacimiento (property)
	private LocalDate fechaNacimiento;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	final LocalDate fecha_actual = LocalDate.now();

	public String validateFechaNacimiento(final LocalDate fechaNacimiento) {

		if (fechaNacimiento.isAfter(fecha_actual))
			return "La fecha de Nacimiento debe ser menor o igual a la fecha actual";
		if (validaMayorEdad(fechaNacimiento) == false)
			return "El paciente es menor a 2 años";
		if (validaMayorCien(fechaNacimiento) == false)
			return "La persona no puede ser mayor a 100 años";
		return "";
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public boolean validaMayorEdad(LocalDate fechadeNacimiento) {

		if (getDiasNacimiento_Hoy(fechadeNacimiento) >= 730) {
			return true;
		}
		return false;
	}

	@ActionLayout(hidden = Where.EVERYWHERE)
	public int getDiasNacimiento_Hoy(LocalDate fechadeNacimiento) {

		Days meses = Days.daysBetween(fechadeNacimiento, fecha_actual);
		return meses.getDays();
	}

	// {{ GrupoSanguineo (property)
	private GrupoSanguineoEnum grupoSanguineo;

	/**
	 * Pemite obtener el grupoSanguineo de un Paciente
	 * 
	 * @return grupoSanguineo GrupoSanguineoEnum
	 */
	@MemberOrder(sequence = "14")
	@Column(allowsNull = "false")
	public GrupoSanguineoEnum getGrupoSanguineo() {
		return grupoSanguineo;
	}

	/**
	 * Setea el grupoSanguineo que se va a crear.
	 * 
	 * @param grupoSanguineo
	 *            grupoSanguineo
	 */
	public void setGrupoSanguineo(final GrupoSanguineoEnum grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	// }}

	// {{ ObraSocial (property)
	private ObraSocial obraSocial;

	@MemberOrder(sequence = "14")
	@Column(allowsNull = "true")
	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(final ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	// }}

	// {{ NumerodeCarnet (property)
	private String numerodeCarnet;

	@MemberOrder(sequence = "18")
	@Column(allowsNull = "true")
	public String getNumerodeCarnet() {
		return numerodeCarnet;
	}

	public void setNumerodeCarnet(final String numerodeCarnet) {
		this.numerodeCarnet = numerodeCarnet;
	}

	// }}

	// }}

	// {{ NumerodePlan (property)
	private String numerodePlan;

	@MemberOrder(sequence = "19")
	@Column(allowsNull = "true")
	public String getNumerodePlan() {
		return numerodePlan;
	}

	public void setNumerodePlan(final String numerodePlan) {
		this.numerodePlan = numerodePlan;
	}

	// }}
	// {{ ListaTurnos (property)
	private List<TurnoPaciente> listaTurnos = new ArrayList<TurnoPaciente>();

	@MemberOrder(sequence = "15")
	@Column(allowsNull = "false")
	@Persistent(table = "lista_turnos", mappedBy = "paciente")
	@Join(column = "paciente_id")
	@CollectionLayout(render = RenderType.EAGERLY)
	/**
	 * Pemite obtener una lista de turnos
	 * 
	 * @return listaturnos List<TurnoPaciente>
	 */
	public List<TurnoPaciente> getListaTurnos() {
		return listaTurnos;
	}

	/**
	 * Setea la lista de turnos.
	 * 
	 * @param List
	 *            <TurnoPaciente> listaTurnos listaTurnos
	 */
	public void setListaTurnos(final List<TurnoPaciente> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	// {{ MayoriaEdad (property)
	private MayoriaEdadEnum mayoriaEdad;

	@MemberOrder(sequence = "16")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED, hidden = Where.ANYWHERE)
	public MayoriaEdadEnum getMayoriaEdad() {
		return mayoriaEdad;
	}

	public void setMayoriaEdad(final MayoriaEdadEnum mayoriaEdad) {
		this.mayoriaEdad = mayoriaEdad;
	}

	// }}

	// {{ RangoEdad (property)
	private RangoEdadEnum rangoEdad;

	@MemberOrder(sequence = "17")
	@Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED, hidden = Where.ANYWHERE)
	public RangoEdadEnum getRangoEdad() {
		return rangoEdad;
	}

	public void setRangoEdad(final RangoEdadEnum rangoEdad) {
		this.rangoEdad = rangoEdad;
	}

	// }}

	// {{ Localizacion (property)

	@javax.jdo.annotations.Persistent
	private Location location;

	@MemberOrder(sequence = "18")
	@Column(allowsNull = "true")
	@Property(editing = Editing.DISABLED, hidden = Where.ANYWHERE)
	public Location getLocation() {
		if (getDireccion() != null) {
			LocationLookupService loc = new LocationLookupService();
			return loc.lookup(this.getDireccion() + ", "
					+ this.getCiudad().getNombre() + ", "
					+ this.getProvincia().getNombre());
		}
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private List<Paciente> listaPaciente = new ArrayList<Paciente>();

	@MemberOrder(sequence = "16")
	@CollectionLayout(render = RenderType.EAGERLY)
	@NotPersistent
	public List<Paciente> getListaDireccion() {
		this.listaPaciente.add(this);
		return this.listaPaciente;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
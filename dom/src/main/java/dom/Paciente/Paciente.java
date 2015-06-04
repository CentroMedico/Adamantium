package dom.Paciente;


import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

import dom.Estado.EstadoEnum;
import dom.GrupoSanguineo.GrupoSanguineoEnum;
import dom.Persona.Persona;


//Primera Estrategia: Una tabla por cada clase
//@PersistenceCapable(identityType = IdentityType.DATASTORE)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
//Segunda Estrategia: Una tabla por cada clase, solo las subclases
@PersistenceCapable
public class Paciente extends Persona {

	// {{ Legajo (property)
	private int legajo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(final int legajo) {
		this.legajo = legajo;
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

	// {{ GrupoSanguineo (property)
	private GrupoSanguineoEnum grupoSanguineo;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public GrupoSanguineoEnum getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(final GrupoSanguineoEnum grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	// }}

	// {{ ObraSocial (property)
	private String obraSocial;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(final String obraSocial) {
		this.obraSocial = obraSocial;
	}

	// }}
	// {{ NumCarnet (property)
	private String numCarnet;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public String getNumCarnet() {
		return numCarnet;
	}

	public void setNumCarnet(final String numCarnet) {
		this.numCarnet = numCarnet;
	}

	// }}

	// {{ CoberturaMedica (property)
	private String cobertura;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getCoberturaMedica() {
		return cobertura;
	}

	public void setCoberturaMedica(final String cobertura) {
		this.cobertura = cobertura;
	}

	// }}
	
	@Inject
	private PacienteServicio pacienteServicio;
	@Inject
	private DomainObjectContainer container;

}

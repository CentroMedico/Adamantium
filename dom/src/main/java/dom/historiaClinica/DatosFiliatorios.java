package dom.historiaClinica;

import org.apache.isis.applib.annotation.MemberOrder;
import org.joda.time.LocalDate;

import dom.tipoDeSexo.TipoDeSexoEnum;
import dom.tipoDocumento.TipoDocumentoEnum;

public class DatosFiliatorios {

	// {{ Apellido (property)
	private String apellido;

	@MemberOrder(sequence = "1")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}

	// }}

	// {{ Nombre (property)
	private String nombre;

	@MemberOrder(sequence = "1")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	// }}

	// {{ Edad (property)
	private int edad;

	@MemberOrder(sequence = "1")
	public int getEdad() {
		return edad;
	}

	public void setEdad(final int edad) {
		this.edad = edad;
	}

	// }}

	// {{ Sexo (property)
	private TipoDeSexoEnum sexo;

	@MemberOrder(sequence = "1")
	public TipoDeSexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(final TipoDeSexoEnum sexo) {
		this.sexo = sexo;
	}

	// }}

	// {{ Ocupacion (property)
	private String ocupacion;

	@MemberOrder(sequence = "1")
	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(final String ocupacion) {
		this.ocupacion = ocupacion;
	}

	// }}

	// {{ FechaNacimiento (property)
	private LocalDate fechaNacimiento;

	@MemberOrder(sequence = "1")
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	// }}

	// {{ NroHistoriaClinica (property)
	private int nroHistoriaClinica;

	@MemberOrder(sequence = "1")
	public int getNroHistoriaClinica() {
		return nroHistoriaClinica;
	}

	public void setNroHistoriaClinica(final int nroHistoriaClinica) {
		this.nroHistoriaClinica = nroHistoriaClinica;
	}

	// }}

	// {{ EstadoCivil (property)
	private String estadoCivil;

	@MemberOrder(sequence = "1")
	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(final String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	// }}

	// {{ TipoDocumento (property)
	private TipoDocumentoEnum tipoDocumento;

	@MemberOrder(sequence = "1")
	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(final TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	// }}

	// {{ Documento (property)
	private String documento;

	@MemberOrder(sequence = "1")
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(final String documento) {
		this.documento = documento;
	}

	// }}

	// {{ Nacionalidad (property)
	private String nacionalidad;

	@MemberOrder(sequence = "1")
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(final String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	// }}

	// {{ ResidenciaActual (property)
	private String residenciaActual;

	@MemberOrder(sequence = "1")
	public String getResidenciaActual() {
		return residenciaActual;
	}

	public void setResidenciaActual(final String residenciaActual) {
		this.residenciaActual = residenciaActual;
	}

	// }}

	// {{ ResidenciaAnterior (property)
	private String residenciaAnterior;

	@MemberOrder(sequence = "1")
	public String getResidenciaAnterior() {
		return residenciaAnterior;
	}

	public void setResidenciaAnterior(final String residenciaAnterior) {
		this.residenciaAnterior = residenciaAnterior;
	}
	// }}

}
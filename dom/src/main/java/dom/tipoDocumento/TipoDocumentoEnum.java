package dom.tipoDocumento;

/**
 * Clase enumerada, de la cual agrega el Grupo Sanguineo de un Paciente.
 * 
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
public enum TipoDocumentoEnum {
	DNI("DNI: Documento Nacional de Identidad"), LC("LC: Libreta Civica"), LE(
			"LE:  Libreta de Enrolamiento"), CI("CI: Cedula de Identidad"), PASAPORTE(
			"Pasaporte");
	private final String nombre;

	// private final Class type;
	public String getNombre() {
		return nombre;
	}

	private TipoDocumentoEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
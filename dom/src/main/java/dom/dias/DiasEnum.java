package dom.dias;

public enum DiasEnum {

	LUNES("Lunes"), MARTES("Martes"), MIERCOLES("Miercoles"), JUEVES("Jueves"), VIERNES(
			"Viernes"), SABADO("Sabado"), DOMINGO("Domingo");
	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private DiasEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
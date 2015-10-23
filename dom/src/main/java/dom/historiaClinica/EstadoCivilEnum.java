package dom.historiaClinica;

public enum EstadoCivilEnum {

	Soltero("Soltero"),Casado("Casado"),UnionEstable("Union Estable"),Separado("Separado"),Divorsiado("Divorsiado") ;

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private EstadoCivilEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;

	}
}

package dom.dias;

public enum HorasMañanaEnum {

	Siete("07:00 am"), SieteTreinta("07:30 am"), Ocho("08:00 am"), OchoTreinta(
			"08:30 am"), Nueve("09:00 am"), NueveTreinta("09:30 am"), Diez(
			"10:30 am"), DiezTreinta("10:30 am"), Once("11:00 am"), OnceTreinta(
			"11:30 am"), Doce("12:00 am");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private HorasMañanaEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
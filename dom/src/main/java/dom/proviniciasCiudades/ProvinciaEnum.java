package dom.proviniciasCiudades;

public enum ProvinciaEnum {

	BuenosAires("Buenos Aires"), Catamarca("Catamarca"), Chaco("Chaco"), Chubut(
			"Chubut"), Cordoba("Cordoba"), Corrientes("Corrientes"), EntreRios(
			"Entre Rios"), Formosa("Formosa"), Jujuy("Jujuy"), LaPampa(
			"La Pampa"), LaRioja("La Rioja"), Mendoza("Mendoza"), Misiones(
			"Misiones"), Neuquen("Neuquen"), RioNegro("Rio Negro"), Salta(
			"Salta"), SanJuan("San Juan"), SanLuis("San Luis"), SantaCruz(
			"Santa Cruz"), SantaFe("Santa Fe"), SantiagoDelEstero(
			"Santiago Del Estero"), TierraDelFuego("Tierra Del Fuego"), Tucuman(
			"Tucuman");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private ProvinciaEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	// /////////////////

	// RioNegro {
	// @Override
	// public List<CiudadEnum> listaCiudades() {
	// return Arrays.asList(null, CiudadEnum.Cipolletti, CiudadEnum.Allen,
	// CiudadEnum.FernandezOro);
	// }
	// },
	// Neuquen {
	// @Override
	// public List<CiudadEnum> listaCiudades() {
	// return Arrays.asList(null, CiudadEnum.Neuquen, CiudadEnum.Plottier,
	// CiudadEnum.Centenario);
	// }
	// };
	//
	// public abstract List<CiudadEnum> listaCiudades();

}
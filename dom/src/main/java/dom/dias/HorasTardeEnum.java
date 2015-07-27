package dom.dias;

public enum HorasTardeEnum {
	DoceTreinta("12:30 pm"), Trece("13:00 pm"), TreceTreinta("13:30 pm"), Catorce(
			"14:00 pm"), CatorceTreinta("14:30 am"), Quince("15:00 pm"), QuinceTreinta(
			"15:30 pm"), Dieciseis("16:00 pm"), DeciseisTreinta("16:30 pm"), Diecisiete(
			"17:00 pm"), DiecisieteTreinta("17:30 pm"), Dieciocho("18:00 pm"), DieciochoTreinta(
			"18:30 pm");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private HorasTardeEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
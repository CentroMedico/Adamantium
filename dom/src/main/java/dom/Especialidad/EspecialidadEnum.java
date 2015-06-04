package dom.Especialidad;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;

public enum EspecialidadEnum {
	Clinica_General("Clinica General"), Dermatologia("Dermatologia"), Nutricion(
			"Nutricion"), Alergología("Alergología"), Anestesiología_y_reanimación(
			"Anestesiología y Reanimación"), Cardiología("Cardiología"), Gastroenterología(
			"Gastroenterología"), Endocrinología("Endocrinología"), Geriatría(
			"Geriatría"), Hematología_y_hemoterapia("Hematología y Hemoterapia"), Hidrología_médica(
			"Hidrología Médica"), Infectología("Infectología"), Medicina_aeroespacial(
			"Medicina Aeroespacial"), Medicina_del_deporte(
			"Medicina del Deporte"), Medicina_del_trabajo(
			"Medicina del Trabajo"), Medicina_de_urgencias(
			"Medicina de Urgencias"), Medicina_familiar_y_comunitaria(
			"Medicina Familiar y Comunitaria"), Medicina_intensiva(
			"Medicina Intensiva"), Medicina_interna("Medicina Interna"), Medicina_legal_y_forense(
			"Medicina Legal y Forense"), Medicina_preventiva_y_salud_pública(
			"Medicina Preventiva y Salud Pública"), Nefrología("Nefrología"), Neumología(
			"Neumología"), Neurología("Neurología"), Nutriología("Nutriología"), Odontología(
			"Odontología"), Oftalmología("Oftalmología"), Oncología_médica(
			"Oncología Médica"), Oncología_radioterápica(
			"Oncología Radioterápica"), Otorrinolaringología(
			"Otorrinolaringología"), Pediatría("Pediatría"), Proctología(
			"Proctología"), Psiquiatría("Psiquiatría"), Rehabilitación(
			"Rehabilitación"), Reumatología("Reumatología"), Traumatología(
			"Traumatología"), Toxicología("Toxicología"), Urología("Urología");

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

	private EspecialidadEnum(String nom) {
		nombre = nom;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Inject
	private IEspecialidad iespecialidad;
	@Inject
	private DomainObjectContainer container;
}

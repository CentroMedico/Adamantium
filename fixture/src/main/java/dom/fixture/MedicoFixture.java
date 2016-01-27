package dom.fixture;

import org.apache.isis.applib.DomainObjectContainer;
import dom.doctor.Doctor;
import dom.doctor.DoctorServicio;

public class MedicoFixture extends Fixture {
	private String matricula = "IJD12,SAD547,MED234,ICS124";
	private String apellido = "Perez,Cabral,Crespin,Bonafide";
	private String nombre = "Juan Carlos,Federico,Carlos,Pedro";
	// private TipoDeSexoEnum
	// tipoSexo="Masculino,Masculino,Masculino,Masculino";
	// private LocalDate fechaNacimiento="01-10-1979";
	// private TipoDocumentoEnum tipoDocumento;
	private String documento = "34798266,23554785,36886145,1462845";
	// private Provincia provincia;
	// private Ciudad ciudad;
	// private String direccion;
	private String correo = "jcperez@gmail.com,cf@cabral@.com,carlos.crespin@gmail.com,pbonadide@homail.com";
	private String telefono = "2995488508,2995558325,2995811814,2995123456";
	// private EspecialidadEnum especialidad;

	public MedicoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private String getMatricula(int x) {
		return obtenerValor(matricula, x);
		
	}

	private String getApellido(int x) {
		return obtenerValor(apellido, x);
	}

	private String getNombre(int x) {
		return obtenerValor(nombre, x);
	}

	// public String getTipoSexo(int x) {
	// return obtenerValor(tipoSexo,x);
	// }

	private String getDocumento(int x) {
		return obtenerValor(documento,x);
	}

	private String getCorreo(int x) {
		return obtenerValor(correo, x);
	}

	private String getTelefono(int x) {
		return obtenerValor(telefono, x);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Medico");
		for (int x = 0; x < 3; x++) {
			create(getMatricula(x), getApellido(x), getNombre(x), getDocumento(x), getCorreo(x), getTelefono(x),
					executionContext);
		}
	}

	private Doctor create(final String matricula, final String apellido, final String nombre, final String documento,
			final String correo, final String telefono, ExecutionContext executionContext) {
		return executionContext.addResult(this, repoDoctor.crearDoctor(matricula, apellido, nombre, null, null, null,
				documento, null, null, null, correo, telefono, null));
	}

	@javax.inject.Inject
	private DoctorServicio repoDoctor;
	@javax.inject.Inject
	DomainObjectContainer container;

}

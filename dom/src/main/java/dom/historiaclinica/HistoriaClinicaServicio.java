package dom.historiaclinica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.doctor.Doctor;
import dom.especialidad.EspecialidadEnum;
import dom.obrasocial.ObraSocial;
import dom.paciente.Paciente;
import dom.reportes.IndicacionesDataSource;
import dom.reportes.RecetaDataSource;
import dom.reportes.ReporteIndicaciones;
import dom.reportes.ReporteReceta;
import dom.vademecum.Vademecum;

@DomainService(repositoryFor = AntecedentesPersonales.class)
@DomainServiceLayout(named = "Historia Clinica", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "9")
public class HistoriaClinicaServicio extends AbstractFactoryAndRepository {
	/**
	 * Retorna el nombre del icono para el Dueño
	 * 
	 * @return String
	 */
	public String iconName() {
		return "historia";
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1")
	@ActionLayout(cssClass = "boton")
	public AdicionalesPaciente crearAdicionalesPaciente(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Estado Civil") final EstadoCivilEnum estadoCivil,
			@ParameterLayout(named = "Tabaja ?", cssClass = "trabaja") final boolean trabajo,
			@ParameterLayout(named = "Tiene Obra Social", cssClass = "historiaClinica") final boolean obraSocial,
			@ParameterLayout(named = "Educacion") final EducacionEnum educacion) {
		final AdicionalesPaciente adicionalPaciente = newTransientInstance(AdicionalesPaciente.class);

		adicionalPaciente.setPaciente(paciente);
		adicionalPaciente.setEstadoCivil(estadoCivil);
		adicionalPaciente.setTrabajo(trabajo);
		adicionalPaciente.setObraSocial(obraSocial);
		adicionalPaciente.setEducacion(educacion);
		container.persistIfNotAlready(adicionalPaciente);
		return adicionalPaciente;
	}

	public List<Paciente> choices0CrearAdicionalesPaciente(
			final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1.2")
	public List<AdicionalesPaciente> listarAdicionalesPaciente() {
		return container.allInstances(AdicionalesPaciente.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "2")
	@ActionLayout(cssClass = "boton")
	public AntecedentesPersonales crearAntecedentesPersonales(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Tabaquismo") final boolean tabaquismo,
			@ParameterLayout(named = "Edad que empezo a Fumar") @Parameter(optionality = Optionality.OPTIONAL, regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String edad,
			@ParameterLayout(named = "Cantidad de Cigarrillos") @Parameter(optionality = Optionality.OPTIONAL, regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String cantidad,
			@ParameterLayout(named = "Alcohol") final boolean alcohol,
			@ParameterLayout(named = "Lo han criticado por tomar") final boolean criticas,
			@ParameterLayout(named = "Toma por la mañana") final boolean tomapormañana,
			@ParameterLayout(named = "Drogas") final boolean drogas,
			@ParameterLayout(named = "Tipos") @Parameter(optionality = Optionality.OPTIONAL, regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String tipodogras,
			@ParameterLayout(named = "Actividad Fisica ") final boolean actividad,
			@ParameterLayout(named = "Tipo de Actividad Fisica") @Parameter(optionality = Optionality.OPTIONAL, regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String tipoactividad,
			@ParameterLayout(named = "HTA") final boolean hta,
			@ParameterLayout(named = "Diabetes") final boolean diabetes,
			@ParameterLayout(named = "Enfermedad Coronaria") final boolean coronaria,
			@ParameterLayout(named = "ACV ") final boolean acv,
			@ParameterLayout(named = "EPOC") final boolean epoc,
			@ParameterLayout(named = "Alergias") final boolean alergias,
			@ParameterLayout(named = "Enfermedad Reumatica") final boolean reumatica,
			@ParameterLayout(named = "Enfermedad Oncologica") final boolean oncologica,
			@ParameterLayout(named = "TBC") final boolean tbc,
			@ParameterLayout(named = "HIV") final boolean hiv,
			@ParameterLayout(named = "Chagas") final boolean chagas,
			@ParameterLayout(named = "ITS") final boolean its,
			@ParameterLayout(named = "Enfermedad Neurologicas") final boolean neurologicas,
			@ParameterLayout(named = "Tranfuciones de sangre") final boolean tranfuciones) {
		final AntecedentesPersonales antecedentes = newTransientInstance(AntecedentesPersonales.class);
		antecedentes.setPaciente(paciente);
		antecedentes.setTabaquismo(tabaquismo);
		antecedentes.setDesdequeEdad(edad);
		antecedentes.setCantidadCigarrillos(cantidad);
		antecedentes.setAlcohol(alcohol);
		antecedentes.setCriticasporTomar(criticas);
		antecedentes.setTomaporlaMañana(tomapormañana);
		antecedentes.setDrogas(drogas);
		antecedentes.setTipoDroga(tipodogras);
		antecedentes.setActividadFisica(actividad);
		antecedentes.setTipoActividad(tipoactividad);
		antecedentes.setHTA(hta);
		antecedentes.setDiabetes(diabetes);
		antecedentes.setEnfermedadCoronaria(coronaria);
		antecedentes.setACV(acv);
		antecedentes.setEPOC(epoc);
		antecedentes.setAlergia(alergias);
		antecedentes.setEnfermedadReumatica(reumatica);
		antecedentes.setEnfermedadOncologica(oncologica);
		antecedentes.setTBC(tbc);
		antecedentes.setVIH(hiv);
		antecedentes.setChagas(chagas);
		antecedentes.setITS(its);
		antecedentes.setNeurologicos(neurologicas);
		antecedentes.setTranfuciones(tranfuciones);
		container.persistIfNotAlready(antecedentes);
		return antecedentes;
	}

	public List<Paciente> choices0CrearAntecedentesPersonales(
			final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.1")
	public List<AntecedentesPersonales> listarAntecedentesPersonales() {
		return container.allInstances(AntecedentesPersonales.class);
	}

	/***
	 * 
	 * 
	 */

	@MemberOrder(name = "Historia Clinica", sequence = "3")
	@ActionLayout(cssClass = "boton")
	public AntecedentesFamiliares crearAntecedentesFamiliares(

			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "HTA") final boolean hta,
			@ParameterLayout(named = "Cardiopatias") final boolean cardiopatias,
			@ParameterLayout(named = "Diabetes") final boolean diabetes,
			@ParameterLayout(named = "Acv") final boolean acv,
			@ParameterLayout(named = "Cancer de Colon") final boolean caddeColon,
			@ParameterLayout(named = "Cancer de Pulmon") final boolean cadePulmon,
			@ParameterLayout(named = "Cancer de Mama") final boolean cadeMama,
			@ParameterLayout(named = "Consumo de drogas") final boolean consumoDrogas,
			@ParameterLayout(named = "Abuso de Alcohol") final boolean abusoAlcohol,
			@ParameterLayout(named = "Depresion") final boolean depresion) {
		final AntecedentesFamiliares antecedentesfamiliares = newTransientInstance(AntecedentesFamiliares.class);

		antecedentesfamiliares.setPaciente(paciente);
		antecedentesfamiliares.setHta(hta);
		antecedentesfamiliares.setCardiopatias(cardiopatias);
		antecedentesfamiliares.setDiabetes(diabetes);
		antecedentesfamiliares.setACV(acv);
		antecedentesfamiliares.setCadeColon(caddeColon);
		antecedentesfamiliares.setCadePulmon(cadePulmon);
		antecedentesfamiliares.setCadeMama(cadeMama);
		antecedentesfamiliares.setConsumoDrogas(consumoDrogas);
		antecedentesfamiliares.setAbusoAlcohol(abusoAlcohol);
		antecedentesfamiliares.setDepresion(depresion);
		container.persistIfNotAlready(antecedentesfamiliares);
		return antecedentesfamiliares;
	}

	public List<Paciente> choices0CrearAntecedentesFamiliares(
			final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "3.1")
	public List<AntecedentesFamiliares> listarAntecedentesFamiliares() {
		return container.allInstances(AntecedentesFamiliares.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "4")
	@ActionLayout(cssClass = "boton")
	public ExamenFisico crearExamenFisico(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Piel y Anexos") @Parameter(optionality = Optionality.OPTIONAL) final String piel,
			@ParameterLayout(named = "Utiliza Lentes? Tipo de enfermedad") @Parameter(optionality = Optionality.OPTIONAL) final String lentes,
			@ParameterLayout(named = "Agudeza Visual") @Parameter(optionality = Optionality.OPTIONAL) final String agudezaVisual,
			@ParameterLayout(named = "Oidos") @Parameter(optionality = Optionality.OPTIONAL) final String oidos,
			@ParameterLayout(named = "Dentadura") @Parameter(optionality = Optionality.OPTIONAL) final String dentadura,
			@ParameterLayout(named = "Pulmones") @Parameter(optionality = Optionality.OPTIONAL) final String pulmones,
			@ParameterLayout(named = "Corazon") @Parameter(optionality = Optionality.OPTIONAL) final String corazon,
			@ParameterLayout(named = "Abdomen") @Parameter(optionality = Optionality.OPTIONAL) final String abdomen,
			@ParameterLayout(named = "Genitales") @Parameter(optionality = Optionality.OPTIONAL) final String genitales,
			@ParameterLayout(named = "Mamas") @Parameter(optionality = Optionality.OPTIONAL) final String mamas,
			@ParameterLayout(named = "Altura") @Parameter(optionality = Optionality.OPTIONAL) final String talla,
			@ParameterLayout(named = "Peso") @Parameter(optionality = Optionality.OPTIONAL) final String peso,
			@ParameterLayout(named = "Temperatura Corporal") @Parameter(optionality = Optionality.OPTIONAL) final String temperatura,
			@ParameterLayout(named = "Frecuencia Cardiaca") @Parameter(optionality = Optionality.OPTIONAL) final String frecuenciaCardiaca,
			@ParameterLayout(named = "Frecuencia Respiratoria") @Parameter(optionality = Optionality.OPTIONAL) final String frecuenciaRespiratoria,
			@ParameterLayout(named = "Tension Arterial") @Parameter(optionality = Optionality.OPTIONAL) final String tensionArterial,
			@ParameterLayout(named = "Estado General") @Parameter(optionality = Optionality.OPTIONAL) final String estadoGeneral) {
		final ExamenFisico examen = newTransientInstance(ExamenFisico.class);

		examen.setPaciente(paciente);
		examen.setPiel(piel);
		examen.setLentes(lentes);
		examen.setAgudezaVisual(agudezaVisual);
		examen.setOidos(oidos);
		examen.setDentadura(dentadura);
		examen.setPulmones(pulmones);
		examen.setCorazon(corazon);
		examen.setAbdomen(abdomen);
		examen.setGenitales(genitales);
		examen.setMamas(mamas);
		examen.setTalla(talla);
		examen.setPeso(peso);
		examen.setTemperatura(temperatura);
		examen.setFrecuenciaCardiaca(frecuenciaCardiaca);
		examen.setFrecuenciaRespiratoria(frecuenciaRespiratoria);
		examen.setTensionArterial(tensionArterial);
		examen.setEstadoGeneral(estadoGeneral);
		container.persistIfNotAlready(examen);
		return examen;

	}

	public List<Paciente> choices0CrearExamenFisico(final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "4.1")
	public List<ExamenFisico> listarExamenFisico() {
		return container.allInstances(ExamenFisico.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "5")
	@ActionLayout(cssClass = "boton")
	public Receta crearReceta(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Obra Social") @Parameter(optionality = Optionality.OPTIONAL) final ObraSocial obraSocial,
			@ParameterLayout(named = "Medicamento") final Vademecum medicamento,
			@ParameterLayout(named = "Medicamento2") @Parameter(optionality = Optionality.OPTIONAL) final Vademecum medicamento2,
			@ParameterLayout(named = "Doctor") final Doctor doctor) {
		final Receta receta = newTransientInstance(Receta.class);
		receta.setPaciente(paciente);
		receta.setObraSocial(obraSocial);
		receta.setMedicamento(medicamento);
		receta.setMedicamento2(medicamento2);
		receta.setDoctor(doctor);
		container.persistIfNotAlready(receta);
		return receta;

	}

	public List<Paciente> choices0CrearReceta(final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "5.1")
	public List<Receta> listarReceta() {
		return container.allInstances(Receta.class);
	}

	public List<Doctor> choices4CrearReceta(final Paciente paciente,
			final ObraSocial obraSocial, final Vademecum medicamento,
			final Vademecum medicamento2) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "6")
	@ActionLayout(cssClass = "boton")
	public IndicacionesMedicas crearIndicacionesMedicas(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Medicamento") final Vademecum medicamento,
			@ParameterLayout(named = "Como Tomarlo", multiLine = 5) final String comoTomarlo,
			@ParameterLayout(named = "Doctor") final Doctor doctor) {
		final IndicacionesMedicas indicaciones = newTransientInstance(IndicacionesMedicas.class);

		indicaciones.setPaciente(paciente);
		indicaciones.setMedicamento(medicamento);
		indicaciones.setComotomarlo(comoTomarlo);
		indicaciones.setDoctor(doctor);
		container.persistIfNotAlready(indicaciones);
		return indicaciones;

	}

	public List<Paciente> choices0CrearIndicacionesMedicas(
			final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "6.1")
	public List<IndicacionesMedicas> listarIndicacionesMedicas() {
		return container.allInstances(IndicacionesMedicas.class);
	}

	public List<Doctor> choices3CrearIndicacionesMedicas(
			final Paciente paciente, final Vademecum medicamento,
			final String comoTomarlo) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "6.2")
	@ActionLayout(named = "Listar Indicaciones por Paciente")
	public List<IndicacionesMedicas> listaindicaciones(final Paciente paciente) {
		return allMatches(QueryDefault.create(IndicacionesMedicas.class,
				"traerPorPaciente", "paciente", paciente));

	}

	// Indicaciones medicas

	@MemberOrder(name = "Historia Clinica", sequence = "10.1")
	@ActionLayout(named = "Exportar Indicaciones Medicas")
	// public Blob downloadAll(final Paciente paciente) throws JRException,
	// IOException {
	public Blob downloadAll(final Paciente paciente,
			IndicacionesMedicas indicacion) throws JRException, IOException {
		IndicacionesDataSource datasource = new IndicacionesDataSource();
		for (IndicacionesMedicas a : listaindicaciones(paciente)) {
			ReporteIndicaciones indicaciones = new ReporteIndicaciones();
			indicaciones.setPaciente(a.getPaciente().getApellido() + " "
					+ a.getPaciente().getNombre());
			indicaciones.setMedicamento(a.getMedicamento().getProducto());
			indicaciones.setComotomarlo(a.getComotomarlo());
			indicaciones.setDoctor(a.getDoctor().getApellido() + " "
					+ a.getDoctor().getNombre());
			// indicaciones.setMatricula(a.getDoctor().getMatricula().toString());
			datasource.addParticipante(indicaciones);
		}
		File file = new File("Indicaciones.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
				datasource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/tmp/salida.pdf");
		File archivo = new File("/tmp/salida.pdf");
		byte[] fileContent = new byte[(int) archivo.length()];
		if (!(archivo.exists())) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(archivo);

			fileInputStream.read(fileContent);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return new Blob(paciente.getApellido() + " - "
					+ paciente.getNombre() + ".pdf", "application/pdf",
					fileContent);
		} catch (Exception e) {
			byte[] result = new String("error en crear archivo").getBytes();
			return new Blob("error.txt", "text/plain", result);
		}

	}

	public List<Paciente> choices0DownloadAll(final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	public List<IndicacionesMedicas> choices1DownloadAll(final Paciente paciente) {

		return allMatches(QueryDefault.create(IndicacionesMedicas.class,
				"traerPorPaciente", "paciente", paciente));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "5.3")
	@ActionLayout(named = "Listar Receta por Paciente")
	public List<Receta> listareceta(final Paciente paciente) {
		return allMatches(QueryDefault.create(Receta.class,
				"traerRecetaPorPaciente", "paciente", paciente));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "10.2")
	@ActionLayout(named = "Exportar Receta")
	// public Blob downloadAll1(final Paciente paciente) throws JRException,
	// IOException {
	public Blob downloadAll1(final Paciente paciente, final Receta rec)
			throws JRException, IOException {
		RecetaDataSource datasource = new RecetaDataSource();

		for (Receta a : listareceta(paciente)) {
			ReporteReceta receta = new ReporteReceta();
			receta.setPaciente(a.getPaciente().getApellido() + " "
					+ a.getPaciente().getNombre());
			receta.setObraSocial(a.getObraSocial().getNombre());
			receta.setMedicamento(a.getMedicamento().getProducto() + " "
					+ a.getMedicamento().getPresentacion() + " "
					+ a.getMedicamento().getTamaño() + " "
					+ a.getMedicamento().getLaboratorio());
			receta.setMedicamento1(a.getMedicamento2().getProducto() + " "
					+ a.getMedicamento2().getPresentacion() + " "
					+ a.getMedicamento2().getTamaño() + " "
					+ a.getMedicamento2().getLaboratorio());
			receta.setDoctor(a.getDoctor().getApellido() + " "
					+ a.getDoctor().getNombre());
			datasource.addParticipante(receta);
		}
		File file = new File("Receta.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
				datasource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/tmp/salida.pdf");
		File archivo = new File("/tmp/salida.pdf");

		byte[] fileContent = new byte[(int) archivo.length()];

		if (!(archivo.exists())) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(archivo);

			fileInputStream.read(fileContent);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return new Blob(paciente.getApellido() + " - "
					+ paciente.getNombre() + " Receta" + ".pdf",
					"application/pdf", fileContent);
		} catch (Exception e) {
			byte[] result = new String("error en crear archivo").getBytes();
			return new Blob("error.txt", "text/plain", result);
		}
	}

	public List<Paciente> choices0DownloadAll1(final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	public List<Receta> choices1DownloadAll1(final Paciente paciente) {

		return allMatches(QueryDefault.create(Receta.class,
				"traerRecetaPorPaciente", "paciente", paciente));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
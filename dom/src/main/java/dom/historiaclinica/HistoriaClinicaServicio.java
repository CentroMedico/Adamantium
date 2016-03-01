/*
s Copyright 2015 Adamantium

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package dom.historiaclinica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import dom.obrasocial.ObraSocial;
import dom.paciente.Paciente;
import dom.reportes.HistoriaClinicaDataSource;
import dom.reportes.IndicacionesDataSource;
import dom.reportes.RecetaDataSource;
import dom.reportes.ReporteHistoriaClinica;
import dom.reportes.ReporteIndicaciones;
import dom.reportes.ReporteReceta;
import dom.turnopaciente.TurnoPaciente;
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

	@MemberOrder(name = "Historia Clinica", sequence = "1.1")
	@ActionLayout(cssClass = "boton")
	public AdicionalesPaciente crearAdicionalesPaciente(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Estado Civil") final EstadoCivilEnum estadoCivil,
			@ParameterLayout(named = "Tabaja ?", cssClass = "trabaja") @Parameter(optionality = Optionality.OPTIONAL) final boolean trabajo,
			@ParameterLayout(named = "Tiene Obra Social", cssClass = "historiaClinica") @Parameter(optionality = Optionality.OPTIONAL) final boolean obraSocial,
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

	@MemberOrder(name = "Historia Clinica", sequence = "2.1")
	public List<AdicionalesPaciente> listarAdicionalesPaciente() {
		return container.allInstances(AdicionalesPaciente.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1.2")
	@ActionLayout(cssClass = "boton")
	public AntecedentesPersonales crearAntecedentesPersonales(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Tabaquismo") @Parameter(optionality = Optionality.OPTIONAL) final boolean tabaquismo,
			@ParameterLayout(named = "Edad que empezo a Fumar") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String edad,
			@ParameterLayout(named = "Cantidad de Cigarrillos") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final String cantidad,
			@ParameterLayout(named = "Alcohol") @Parameter(optionality = Optionality.OPTIONAL) final boolean alcohol,
			@ParameterLayout(named = "Lo han criticado por tomar") @Parameter(optionality = Optionality.OPTIONAL) final boolean criticas,
			@ParameterLayout(named = "Toma por la mañana") @Parameter(optionality = Optionality.OPTIONAL) final boolean tomapormañana,
			@ParameterLayout(named = "Drogas") @Parameter(optionality = Optionality.OPTIONAL) final boolean drogas,
			@ParameterLayout(named = "Tipos") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String tipodogras,
			@ParameterLayout(named = "Actividad Fisica ") @Parameter(optionality = Optionality.OPTIONAL) final boolean actividad,
			@ParameterLayout(named = "Tipo de Actividad Fisica") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaNombres.REFERENCIA) final String tipoactividad,
			@ParameterLayout(named = "HTA") @Parameter(optionality = Optionality.OPTIONAL) final boolean hta,
			@ParameterLayout(named = "Diabetes") @Parameter(optionality = Optionality.OPTIONAL) final boolean diabetes,
			@ParameterLayout(named = "Enfermedad Coronaria") @Parameter(optionality = Optionality.OPTIONAL) final boolean coronaria,
			@ParameterLayout(named = "ACV ") @Parameter(optionality = Optionality.OPTIONAL) final boolean acv,
			@ParameterLayout(named = "EPOC") @Parameter(optionality = Optionality.OPTIONAL) final boolean epoc,
			@ParameterLayout(named = "Alergias") @Parameter(optionality = Optionality.OPTIONAL) final boolean alergias,
			@ParameterLayout(named = "Enfermedad Reumatica") @Parameter(optionality = Optionality.OPTIONAL) final boolean reumatica,
			@ParameterLayout(named = "Enfermedad Oncologica") @Parameter(optionality = Optionality.OPTIONAL) final boolean oncologica,
			@ParameterLayout(named = "TBC") @Parameter(optionality = Optionality.OPTIONAL) final boolean tbc,
			@ParameterLayout(named = "HIV") @Parameter(optionality = Optionality.OPTIONAL) final boolean hiv,
			@ParameterLayout(named = "Chagas") @Parameter(optionality = Optionality.OPTIONAL) final boolean chagas,
			@ParameterLayout(named = "ITS") @Parameter(optionality = Optionality.OPTIONAL) final boolean its,
			@ParameterLayout(named = "Enfermedad Neurologicas") @Parameter(optionality = Optionality.OPTIONAL) final boolean neurologicas,
			@ParameterLayout(named = "Tranfuciones de sangre") @Parameter(optionality = Optionality.OPTIONAL) final boolean tranfuciones) {
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
		antecedentes.setTBc(tbc);
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

	@MemberOrder(name = "Historia Clinica", sequence = "2.2")
	public List<AntecedentesPersonales> listarAntecedentesPersonales() {
		return container.allInstances(AntecedentesPersonales.class);
	}

	/***
	 * 
	 * 
	 */

	@MemberOrder(name = "Historia Clinica", sequence = "1.3")
	@ActionLayout(cssClass = "boton")
	public AntecedentesFamiliares crearAntecedentesFamiliares(

			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "HTA") @Parameter(optionality = Optionality.OPTIONAL) final boolean hta,
			@ParameterLayout(named = "Cardiopatias") @Parameter(optionality = Optionality.OPTIONAL) final boolean cardiopatias,
			@ParameterLayout(named = "Diabetes") @Parameter(optionality = Optionality.OPTIONAL) final boolean diabetes,
			@ParameterLayout(named = "Acv") @Parameter(optionality = Optionality.OPTIONAL) final boolean acv,
			@ParameterLayout(named = "Cancer de Colon") @Parameter(optionality = Optionality.OPTIONAL) final boolean caddeColon,
			@ParameterLayout(named = "Cancer de Pulmon") @Parameter(optionality = Optionality.OPTIONAL) final boolean cadePulmon,
			@ParameterLayout(named = "Cancer de Mama") @Parameter(optionality = Optionality.OPTIONAL) final boolean cadeMama,
			@ParameterLayout(named = "Consumo de drogas") @Parameter(optionality = Optionality.OPTIONAL) final boolean consumoDrogas,
			@ParameterLayout(named = "Abuso de Alcohol") @Parameter(optionality = Optionality.OPTIONAL) final boolean abusoAlcohol,
			@ParameterLayout(named = "Depresion") @Parameter(optionality = Optionality.OPTIONAL) final boolean depresion) {
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

	@MemberOrder(name = "Historia Clinica", sequence = "2.3")
	public List<AntecedentesFamiliares> listarAntecedentesFamiliares() {
		return container.allInstances(AntecedentesFamiliares.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1.4")
	@ActionLayout(cssClass = "boton")
	public ExamenFisico crearExamenFisico(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Piel y Anexos") final String piel,
			@ParameterLayout(named = "Utiliza Lentes? Tipo de enfermedad") final String lentes,
			@ParameterLayout(named = "Agudeza Visual") final String agudezaVisual,
			@ParameterLayout(named = "Oidos") final String oidos,
			@ParameterLayout(named = "Dentadura") final String dentadura,
			@ParameterLayout(named = "Pulmones") final String pulmones,
			@ParameterLayout(named = "Corazon") final String corazon,
			@ParameterLayout(named = "Abdomen") final String abdomen,
			@ParameterLayout(named = "Genitales") final String genitales,
			@ParameterLayout(named = "Mamas") final String mamas,
			@ParameterLayout(named = "Altura") final String talla,
			@ParameterLayout(named = "Peso") final String peso,
			@ParameterLayout(named = "Temperatura Corporal") final String temperatura,
			@ParameterLayout(named = "Frecuencia Cardiaca") final String frecuenciaCardiaca,
			@ParameterLayout(named = "Frecuencia Respiratoria") final String frecuenciaRespiratoria,
			@ParameterLayout(named = "Tension Arterial") final String tensionArterial,
			@ParameterLayout(named = "Estado General") final String estadoGeneral) {
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

	@MemberOrder(name = "Historia Clinica", sequence = "2.4")
	public List<ExamenFisico> listarExamenFisico() {
		return container.allInstances(ExamenFisico.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1.5")
	@ActionLayout(cssClass = "boton")
	public Receta crearReceta(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Obra Social") final ObraSocial obraSocial,
			@ParameterLayout(named = "Medicamento") final Vademecum medicamento,
			@ParameterLayout(named = "Doctor") final Doctor doctor,
			@ParameterLayout(named = "Turno") final TurnoPaciente turno) {
		final Receta receta = newTransientInstance(Receta.class);
		receta.setPaciente(paciente);
		receta.setObraSocial(obraSocial);
		receta.setMedicamento(medicamento);
		receta.setDoctor(doctor);
		receta.setTurno(turno);
		container.persistIfNotAlready(receta);
		return receta;

	}

	public List<Paciente> choices0CrearReceta(final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	public List<Doctor> choices3CrearReceta(final Paciente paciente,
			final ObraSocial obraSocial, final Vademecum medicamento) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));

	}

	public List<TurnoPaciente> choices4CrearReceta(final Paciente paciente,
			final ObraSocial obraSocial, final Vademecum medicamento,
			final Doctor doctor) {
		return container.allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerAtendidosPorPaciente", "paciente", paciente));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.5")
	public List<Receta> listarReceta() {
		return container.allInstances(Receta.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1.6")
	@ActionLayout(cssClass = "boton")
	public IndicacionesMedicas crearIndicacionesMedicas(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Medicamento") final Vademecum medicamento,
			@ParameterLayout(named = "Como Tomarlo", multiLine = 5) final String comoTomarlo,
			@ParameterLayout(named = "Doctor") final Doctor doctor,
			@ParameterLayout(named = "Turno") final TurnoPaciente turno) {
		final IndicacionesMedicas indicaciones = newTransientInstance(IndicacionesMedicas.class);

		indicaciones.setPaciente(paciente);
		indicaciones.setMedicamento(medicamento);
		indicaciones.setComotomarlo(comoTomarlo);
		indicaciones.setDoctor(doctor);
		indicaciones.setTurno(turno);
		container.persistIfNotAlready(indicaciones);
		return indicaciones;

	}

	public List<Paciente> choices0CrearIndicacionesMedicas(
			final Paciente paciente) {

		return allMatches(QueryDefault.create(Paciente.class,
				"traerPacientesActivos"));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.6")
	public List<IndicacionesMedicas> listarIndicacionesMedicas() {
		return container.allInstances(IndicacionesMedicas.class);
	}

	public List<Doctor> choices3CrearIndicacionesMedicas(
			final Paciente paciente, final Vademecum medicamento,
			final String comoTomarlo) {

		return container.allMatches(QueryDefault.create(Doctor.class,
				"traerActivos"));

	}

	public List<TurnoPaciente> choices4CrearIndicacionesMedicas(
			final Paciente paciente, final Vademecum medicamento,
			final String comoTomarlo, final Doctor doctor) {
		return container.allMatches(QueryDefault.create(TurnoPaciente.class,
				"traerAtendidosPorPaciente", "paciente", paciente));
	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.7")
	@ActionLayout(named = "Listar Indicaciones por Paciente")
	public List<IndicacionesMedicas> listaindicaciones(final Paciente paciente) {
		return allMatches(QueryDefault.create(IndicacionesMedicas.class,
				"traerPorPaciente", "paciente", paciente));

	}

	// Indicaciones medicas

	@MemberOrder(name = "Historia Clinica", sequence = "10.1")
	@ActionLayout(named = "Exportar Indicaciones Medicas")
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

	@MemberOrder(name = "Historia Clinica", sequence = "2.8")
	@ActionLayout(named = "Listar Receta por Paciente")
	public List<Receta> listareceta(final Paciente paciente) {
		return allMatches(QueryDefault.create(Receta.class,
				"traerRecetaPorPaciente", "paciente", paciente));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "10.2")
	@ActionLayout(named = "Exportar Receta")
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

	@MemberOrder(name = "Historia Clinica", sequence = "2.9")
	@ActionLayout(named = "Listar Adicionales por Paciente")
	public AdicionalesPaciente listarAdicionales(final Paciente paciente) {
		return firstMatch(QueryDefault.create(AdicionalesPaciente.class,
				"traerAdicionalesPorPaciente", "paciente", paciente));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.10")
	@ActionLayout(named = "Listar Antecedentes Personales por Paciente")
	public AntecedentesPersonales listarPersonales(final Paciente paciente) {
		return firstMatch(QueryDefault.create(AntecedentesPersonales.class,
				"traerAdicionalesPorPaciente", "paciente", paciente));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.11")
	@ActionLayout(named = "Listar Antecedentes Familiares por Paciente")
	public AntecedentesFamiliares listarFamiliares(final Paciente paciente) {
		return firstMatch(QueryDefault.create(AntecedentesFamiliares.class,
				"traerFamiliaresPorPaciente", "paciente", paciente));

	}

	@MemberOrder(name = "Historia Clinica", sequence = "2.12")
	@ActionLayout(named = "Listar Examen Fisico por Paciente")
	public ExamenFisico listarExamen(final Paciente paciente) {
		return firstMatch(QueryDefault.create(ExamenFisico.class,
				"traerExamenPorPaciente", "paciente", paciente));

	}

	private String pasarASiONo(boolean entrada) {
		if (entrada) {
			return "Si";
		} else {
			return "No";
		}
	}

	@MemberOrder(name = "Historia Clinica", sequence = "10.4")
	@ActionLayout(named = "Exportar Historia Clinica")
	public Blob downloadAll2(final Paciente paciente) throws JRException,
			IOException {

		HistoriaClinicaDataSource datasource = new HistoriaClinicaDataSource();

		AdicionalesPaciente a = listarAdicionales(paciente);
		ReporteHistoriaClinica general = new ReporteHistoriaClinica();

		general.setPaciente(a.getPaciente().getApellido() + " "
				+ a.getPaciente().getNombre());
		general.setEstadoCivil(a.getEstadoCivil().toString());
		general.setObraSocial(a.getPaciente().getObraSocial().getNombre());
		general.setEducacion(a.getEducacion().getNombre());
		general.setTrabajo(pasarASiONo(a.getTrabajo()));
		general.setDni(a.getPaciente().getDocumento());
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		general.setFechanac(df.format(a.getPaciente().getFechaNacimiento()
				.toDate()));
		general.setNumCarnet(a.getPaciente().getNumerodeCarnet());

		AntecedentesPersonales b = listarPersonales(paciente);
		// ReporteHistoriaClinica personales = new ReporteHistoriaClinica();
		general.setTabaquismo1(pasarASiONo(b.getTabaquismo()));
		general.setEdadqueempezo(b.getDesdequeEdad().toString());
		general.setCantidaddeCigarrillos(b.getCantidadCigarrillos());
		general.setAlchool(pasarASiONo(b.getAlcohol()));
		general.setCriticas(pasarASiONo(b.getCriticasporTomar()));
		general.setTomaporlaMañana(pasarASiONo(b.getTomaporlaMañana()));
		general.setDrogas(pasarASiONo(b.getDrogas()));
		general.setTipoDrogas(b.getTipoDroga());
		general.setActividad(pasarASiONo(b.getActividadFisica()));
		general.setTipoActividad(b.getTipoActividad());
		general.setHta(pasarASiONo(b.getHTA()));
		general.setDiabetes(pasarASiONo(b.getDiabetes()));
		general.setCoronaria(pasarASiONo(b.getEnfermedadCoronaria()));
		general.setAcv(pasarASiONo(b.getACV()));
		general.setEpoc(pasarASiONo(b.getEPOC()));
		general.setAlergias(pasarASiONo(b.getAlergia()));
		general.setReumatica(pasarASiONo(b.getEnfermedadReumatica()));
		general.setOncologicas(pasarASiONo(b.getEnfermedadOncologica()));
		general.setTbc(pasarASiONo(b.getTBc()));
		general.setHiv(pasarASiONo(b.getVIH()));
		general.setChagas(pasarASiONo(b.getChagas()));
		general.setIts(pasarASiONo(b.getITS()));
		general.setNeurologicas(pasarASiONo(b.getNeurologicos()));
		general.setTransfuciones(pasarASiONo(b.getTranfuciones()));

		AntecedentesFamiliares c = listarFamiliares(paciente);
		// ReporteHistoriaClinica familiares = new ReporteHistoriaClinica();

		general.setHta1(pasarASiONo(c.getHta()));
		general.setCardiopatias(pasarASiONo(c.getCardiopatias()));
		general.setDiabetes1(pasarASiONo(c.getDiabetes()));
		general.setAcv1(pasarASiONo(c.getACV()));
		general.setCancerdeColon(pasarASiONo(c.getCadeColon()));
		general.setCancerdePulmon(pasarASiONo(c.getCadePulmon()));
		general.setCancerdeMama(pasarASiONo(c.getCadeMama()));
		general.setConsumodeDrogas(pasarASiONo(c.getConsumoDrogas()));
		general.setAbusodeAlchool(pasarASiONo(c.getAbusoAlcohol()));
		general.setDepresion(pasarASiONo(c.getDepresion()));

		ExamenFisico d = listarExamen(paciente);
		// ReporteHistoriaClinica examen = new ReporteHistoriaClinica();
		general.setPiel(d.getPiel());
		general.setUtilizalentes(d.getLentes().toString());
		general.setAgudezaVisual(d.getAgudezaVisual());
		general.setOidos(d.getOidos());
		general.setDentadura(d.getDentadura());
		general.setPulmones(d.getPulmones());
		general.setCorazon(d.getCorazon());
		general.setAbdomen(d.getAbdomen());
		general.setGenitales(d.getGenitales());
		general.setMamas(d.getMamas());
		general.setAltura(d.getTalla());
		general.setPeso(d.getPeso());
		general.setTemperaturaCorporal(d.getTemperatura());
		general.setFrecuenciaCardiaca(d.getFrecuenciaCardiaca());
		general.setFrecuenciaRespiratoria(d.getFrecuenciaRespiratoria());
		general.setTensionArterial(d.getTensionArterial());
		general.setEstadoGeneral(d.getEstadoGeneral());
		datasource.addParticipante(general);

		File file = new File("HistoriaClinica.jrxml");
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
					+ paciente.getNombre() + " Historia Clinica" + ".pdf",
					"application/pdf", fileContent);
		} catch (Exception e) {
			byte[] result = new String("error en crear archivo").getBytes();
			return new Blob("error.txt", "text/plain", result);
		}
	}

	@MemberOrder(name = "Historia Clinica", sequence = "10.5")
	@ActionLayout(named = "Exportar Mi Historia Clinica")
	public Blob downloadAll3() throws JRException, IOException {

		Paciente paciente = container.firstMatch(QueryDefault.create(
				Paciente.class, "traerPacientePorUsuario", "usuariovinculado",
				container.getUser().getName()));
		HistoriaClinicaDataSource datasource = new HistoriaClinicaDataSource();

		AdicionalesPaciente a = listarAdicionales(paciente);
		ReporteHistoriaClinica general = new ReporteHistoriaClinica();

		general.setPaciente(a.getPaciente().getApellido() + " "
				+ a.getPaciente().getNombre());
		general.setEstadoCivil(a.getEstadoCivil().toString());
		general.setObraSocial(a.getPaciente().getObraSocial().getNombre());
		general.setEducacion(a.getEducacion().getNombre());
		general.setTrabajo(pasarASiONo(a.getTrabajo()));
		general.setDni(a.getPaciente().getDocumento());
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		general.setFechanac(df.format(a.getPaciente().getFechaNacimiento()
				.toDate()));
		general.setNumCarnet(a.getPaciente().getNumerodeCarnet());

		AntecedentesPersonales b = listarPersonales(paciente);
		general.setTabaquismo1(pasarASiONo(b.getTabaquismo()));
		general.setEdadqueempezo(b.getDesdequeEdad().toString());
		general.setCantidaddeCigarrillos(b.getCantidadCigarrillos());
		general.setAlchool(pasarASiONo(b.getAlcohol()));
		general.setCriticas(pasarASiONo(b.getCriticasporTomar()));
		general.setTomaporlaMañana(pasarASiONo(b.getTomaporlaMañana()));
		general.setDrogas(pasarASiONo(b.getDrogas()));
		general.setTipoDrogas(b.getTipoDroga());
		general.setActividad(pasarASiONo(b.getActividadFisica()));
		general.setTipoActividad(b.getTipoActividad());
		general.setHta(pasarASiONo(b.getHTA()));
		general.setDiabetes(pasarASiONo(b.getDiabetes()));
		general.setCoronaria(pasarASiONo(b.getEnfermedadCoronaria()));
		general.setAcv(pasarASiONo(b.getACV()));
		general.setEpoc(pasarASiONo(b.getEPOC()));
		general.setAlergias(pasarASiONo(b.getAlergia()));
		general.setReumatica(pasarASiONo(b.getEnfermedadReumatica()));
		general.setOncologicas(pasarASiONo(b.getEnfermedadOncologica()));
		general.setTbc(pasarASiONo(b.getTBc()));
		general.setHiv(pasarASiONo(b.getVIH()));
		general.setChagas(pasarASiONo(b.getChagas()));
		general.setIts(pasarASiONo(b.getITS()));
		general.setNeurologicas(pasarASiONo(b.getNeurologicos()));
		general.setTransfuciones(pasarASiONo(b.getTranfuciones()));

		AntecedentesFamiliares c = listarFamiliares(paciente);

		general.setHta1(pasarASiONo(c.getHta()));
		general.setCardiopatias(pasarASiONo(c.getCardiopatias()));
		general.setDiabetes1(pasarASiONo(c.getDiabetes()));
		general.setAcv1(pasarASiONo(c.getACV()));
		general.setCancerdeColon(pasarASiONo(c.getCadeColon()));
		general.setCancerdePulmon(pasarASiONo(c.getCadePulmon()));
		general.setCancerdeMama(pasarASiONo(c.getCadeMama()));
		general.setConsumodeDrogas(pasarASiONo(c.getConsumoDrogas()));
		general.setAbusodeAlchool(pasarASiONo(c.getAbusoAlcohol()));
		general.setDepresion(pasarASiONo(c.getDepresion()));

		ExamenFisico d = listarExamen(paciente);
		general.setPiel(d.getPiel());
		general.setUtilizalentes(d.getLentes().toString());
		general.setAgudezaVisual(d.getAgudezaVisual());
		general.setOidos(d.getOidos());
		general.setDentadura(d.getDentadura());
		general.setPulmones(d.getPulmones());
		general.setCorazon(d.getCorazon());
		general.setAbdomen(d.getAbdomen());
		general.setGenitales(d.getGenitales());
		general.setMamas(d.getMamas());
		general.setAltura(d.getTalla());
		general.setPeso(d.getPeso());
		general.setTemperaturaCorporal(d.getTemperatura());
		general.setFrecuenciaCardiaca(d.getFrecuenciaCardiaca());
		general.setFrecuenciaRespiratoria(d.getFrecuenciaRespiratoria());
		general.setTensionArterial(d.getTensionArterial());
		general.setEstadoGeneral(d.getEstadoGeneral());
		datasource.addParticipante(general);

		File file = new File("HistoriaClinica.jrxml");
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
					+ paciente.getNombre() + " Historia Clinica" + ".pdf",
					"application/pdf", fileContent);
		} catch (Exception e) {
			byte[] result = new String("error en crear archivo").getBytes();
			return new Blob("error.txt", "text/plain", result);
		}
	}

	// @MemberOrder(name = "Historia Clinica", sequence = "10.6")
	// @ActionLayout(named = "Exportar Mis Indicaciones Medicas")
	// public Blob downloadAll4(
	// // final Paciente paciente,
	// IndicacionesMedicas indicacion) throws JRException, IOException {
	// Paciente paciente = container.firstMatch(QueryDefault.create(
	// Paciente.class, "traerPacientePorUsuario", "usuariovinculado",
	// container.getUser().getName()));
	// IndicacionesDataSource datasource = new IndicacionesDataSource();
	// for (IndicacionesMedicas a : listaindicaciones(paciente)) {
	// ReporteIndicaciones indicaciones = new ReporteIndicaciones();
	// indicaciones.setPaciente(a.getPaciente().getApellido() + " "
	// + a.getPaciente().getNombre());
	// indicaciones.setMedicamento(a.getMedicamento().getProducto());
	// indicaciones.setComotomarlo(a.getComotomarlo());
	// indicaciones.setDoctor(a.getDoctor().getApellido() + " "
	// + a.getDoctor().getNombre());
	// datasource.addParticipante(indicaciones);
	// }
	// File file = new File("Indicaciones.jrxml");
	// FileInputStream input = null;
	// try {
	// input = new FileInputStream(file);
	//
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// JasperDesign jd = JRXmlLoader.load(input);
	// JasperReport reporte = JasperCompileManager.compileReport(jd);
	// JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
	// datasource);
	// JasperExportManager.exportReportToPdfFile(jasperPrint,
	// "/tmp/salida.pdf");
	// File archivo = new File("/tmp/salida.pdf");
	// byte[] fileContent = new byte[(int) archivo.length()];
	// if (!(archivo.exists())) {
	// try {
	// archivo.createNewFile();
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	// }
	// try {
	// FileInputStream fileInputStream = new FileInputStream(archivo);
	//
	// fileInputStream.read(fileContent);
	// fileInputStream.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// try {
	// return new Blob(paciente.getApellido() + " - "
	// + paciente.getNombre() + ".pdf", "application/pdf",
	// fileContent);
	// } catch (Exception e) {
	// byte[] result = new String("error en crear archivo").getBytes();
	// return new Blob("error.txt", "text/plain", result);
	// }
	//
	// }
	//
	// // public List<Paciente> choices0DownloadAll4(final Paciente paciente) {
	// //
	// // return allMatches(QueryDefault.create(Paciente.class,
	// // "traerPacientesActivos"));
	// // }
	//
	// // public List<IndicacionesMedicas> choices1DownloadAll4(
	// // final Paciente paciente) {
	// //
	// // return allMatches(QueryDefault.create(IndicacionesMedicas.class,
	// // "traerPorPaciente", "paciente", paciente));
	// // }
	//
	// @MemberOrder(name = "Historia Clinica", sequence = "10.7")
	// @ActionLayout(named = "Exportar Mis Recetas")
	// public Blob downloadAll5(final Paciente paciente, final Receta rec)
	// throws JRException, IOException {
	// RecetaDataSource datasource = new RecetaDataSource();
	//
	// for (Receta a : listareceta(paciente)) {
	// ReporteReceta receta = new ReporteReceta();
	// receta.setPaciente(a.getPaciente().getApellido() + " "
	// + a.getPaciente().getNombre());
	//
	// receta.setObraSocial(a.getObraSocial().getNombre());
	//
	// receta.setMedicamento(a.getMedicamento().getProducto() + " "
	// + a.getMedicamento().getPresentacion() + " "
	// + a.getMedicamento().getTamaño() + " "
	// + a.getMedicamento().getLaboratorio());
	//
	// datasource.addParticipante(receta);
	// }
	// File file = new File("Receta.jrxml");
	// FileInputStream input = null;
	// try {
	// input = new FileInputStream(file);
	//
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// JasperDesign jd = JRXmlLoader.load(input);
	// JasperReport reporte = JasperCompileManager.compileReport(jd);
	// JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
	// datasource);
	// JasperExportManager.exportReportToPdfFile(jasperPrint,
	// "/tmp/salida.pdf");
	// File archivo = new File("/tmp/salida.pdf");
	//
	// byte[] fileContent = new byte[(int) archivo.length()];
	//
	// if (!(archivo.exists())) {
	// try {
	// archivo.createNewFile();
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	// }
	// try {
	// FileInputStream fileInputStream = new FileInputStream(archivo);
	//
	// fileInputStream.read(fileContent);
	// fileInputStream.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// try {
	// return new Blob(paciente.getApellido() + " - "
	// + paciente.getNombre() + " Receta" + ".pdf",
	// "application/pdf", fileContent);
	// } catch (Exception e) {
	// byte[] result = new String("error en crear archivo").getBytes();
	// return new Blob("error.txt", "text/plain", result);
	// }
	// }
	//
	// public List<Paciente> choices0DownloadAll5(final Paciente paciente) {
	//
	// return allMatches(QueryDefault.create(Paciente.class,
	// "traerPacientesActivos"));
	// }
	//
	// public List<Receta> choices1DownloadAll5(final Paciente paciente) {
	//
	// return allMatches(QueryDefault.create(Receta.class,
	// "traerRecetaPorPaciente", "paciente", paciente));
	// }

	@javax.inject.Inject
	DomainObjectContainer container;
}
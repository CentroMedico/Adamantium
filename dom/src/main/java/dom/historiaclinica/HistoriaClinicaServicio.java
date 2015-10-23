package dom.historiaclinica;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import dom.doctor.Doctor;
import dom.obrasocial.ObraSocial;
import dom.paciente.Paciente;
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
	public AdicionalesPaciente crearAdicionalesPaciente(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Estado Civil") final EstadoCivilEnum estadoCivil,
			@ParameterLayout(named = "Tabaja ?") final Boolean trabajo,
			@ParameterLayout(named = "Tiene Obra Social") final Boolean obraSocial,
			@ParameterLayout(named = "Educacion") final EducacionEnum educacion) {
		final AdicionalesPaciente adicionalPaciente = newTransientInstance(AdicionalesPaciente.class);

		adicionalPaciente.setPaciente(paciente);
		adicionalPaciente.setEstadoCivil(estadoCivil);
		adicionalPaciente.setTrabajo(trabajo);
		adicionalPaciente.setObraSocial(obraSocial);
		adicionalPaciente.setEducacion(educacion);

		persist(adicionalPaciente);
		container.flush();
		return adicionalPaciente;
	}

	@MemberOrder(name = "Historia Clinica", sequence = "1.2")
	public List<AdicionalesPaciente> listarAdicionalesPaciente() {
		return container.allInstances(AdicionalesPaciente.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "2")
	public AntecedentesPersonales crearAntecedentesPersonales(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Tabaquismo") final boolean tabaquismo,
			@ParameterLayout(named = "Edad que empezo a Fumar") final @Parameter(optionality = Optionality.OPTIONAL, regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) int edad,
			@ParameterLayout(named = "Cantidad de Cigarrillos") @Parameter(optionality = Optionality.OPTIONAL, regexPattern = dom.regex.RegexValidation.ValidaDoc.DOCUMENTO) final int cantidad,
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

		persist(antecedentes);
		container.flush();
		return antecedentes;
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
		persist(antecedentesfamiliares);
		container.flush();
		return antecedentesfamiliares;
	}

	@MemberOrder(name = "Historia Clinica", sequence = "3.1")
	public List<AntecedentesFamiliares> listarAntecedentesFamiliares() {
		return container.allInstances(AntecedentesFamiliares.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "4")
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

		persist(examen);
		container.flush();
		return examen;

	}

	@MemberOrder(name = "Historia Clinica", sequence = "4.1")
	public List<ExamenFisico> listarExamenFisico() {
		return container.allInstances(ExamenFisico.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "5.1")
	public Receta crearReceta(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Obra Social") final ObraSocial obraSocial,
			@ParameterLayout(named = "Medicamento") final Vademecum medicamento,
			@ParameterLayout(named = "Medicamento2") final Vademecum medicamento2,
			@ParameterLayout(named = "Doctor") final Doctor doctor) {
		final Receta receta = newTransientInstance(Receta.class);
		receta.setPaciente(paciente);
		receta.setObraSocial(obraSocial);
		receta.setMedicamento(medicamento);
		receta.setMedicamento2(medicamento2);
		receta.setDoctor(doctor);

		persist(receta);
		container.flush();
		return receta;

	}

	@MemberOrder(name = "Historia Clinica", sequence = "5.1")
	public List<Receta> listarReceta() {
		return container.allInstances(Receta.class);
	}

	@MemberOrder(name = "Historia Clinica", sequence = "6")
	public IndicacionesMedicas crearIndicacionesMedicas(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Medicamento") final Vademecum medicamento,
			@ParameterLayout(named = "Como Tomarlo") final String comoTomarlo,
			@ParameterLayout(named = "Doctor") final Doctor doctor)
	{
		final IndicacionesMedicas indicaciones =newTransientInstance(IndicacionesMedicas.class);
		
		indicaciones.setPaciente(paciente);
		indicaciones.setMedicamento(medicamento);
		indicaciones.setComotomarlo(comoTomarlo);
		indicaciones.setDoctor(doctor);
		
		persist(indicaciones);
		container.flush();
		return indicaciones;

	}

	@MemberOrder(name = "Historia Clinica", sequence = "6.1")
	public List<IndicacionesMedicas> listarIndicacionesMedicas() {
		return container.allInstances(IndicacionesMedicas.class);
	}
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
}

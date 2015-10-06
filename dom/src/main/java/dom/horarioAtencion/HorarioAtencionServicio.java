//package dom.horarioAtencion;
//
//import org.apache.isis.applib.AbstractFactoryAndRepository;
//import org.apache.isis.applib.DomainObjectContainer;
//import org.apache.isis.applib.annotation.DomainService;
//import org.apache.isis.applib.annotation.DomainServiceLayout;
//import org.apache.isis.applib.annotation.MemberOrder;
//import org.apache.isis.applib.annotation.Parameter;
//import org.apache.isis.applib.annotation.ParameterLayout;
//
//import dom.doctor.Doctor;
//
//@DomainService(repositoryFor = HorarioAtencion.class)
//@DomainServiceLayout(named = "HorarioAtencion", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "5")
//public class HorarioAtencionServicio extends AbstractFactoryAndRepository {
//
//	@MemberOrder(name = "HorarioAtencion", sequence = "5.1")
//	public HorarioAtencion crearHorarioAtencion(
//			@ParameterLayout(named = "Doctor") final Doctor doctor,
//			@ParameterLayout(named = "Rango Desde") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaRangoHorario.HORARIO) final String rangoDesde,
//			@ParameterLayout(named = "Rango Hasta") @Parameter(regexPattern = dom.regex.RegexValidation.ValidaRangoHorario.HORARIO) final String rangoHasta,
//			@ParameterLayout(named = "Dia") final DiaSemanaEnum dia) {
//
//		final HorarioAtencion horarioAtencion = newTransientInstance(HorarioAtencion.class);
//
//		horarioAtencion.setDoctor(doctor);
//		horarioAtencion.setRangoDesde(rangoDesde);
//		horarioAtencion.setRangoHasta(rangoHasta);
//		horarioAtencion.setDiaDeLaSemana(dia);
//		persist(horarioAtencion);
//		container.flush();
//		return horarioAtencion;
//	}
//
//	@javax.inject.Inject
//	DomainObjectContainer container;
//
// }
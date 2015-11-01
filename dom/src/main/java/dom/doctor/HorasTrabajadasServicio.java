package dom.doctor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;

@DomainService(repositoryFor = Doctor.class)
@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "7")
public class HorasTrabajadasServicio extends AbstractFactoryAndRepository {

	@MemberOrder(name = "Doctor", sequence = "4.1")
	@ActionLayout(cssClass = "boton")
	public String registrarIngreso(
			@ParameterLayout(named = "Doctor") final Doctor doctor) {

		final SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"dd MMMM YYYY HH:mm");
		Date ingreso = new Date();
		final HorasTrabajadas horas = newTransientInstance(HorasTrabajadas.class);
		horas.setDoctor(doctor);
		horas.setIngreso("Ingreso: " + ingreso);
		persist(horas);
		return formatoFecha.format(ingreso);

	}

	@MemberOrder(name = "Doctor", sequence = "4.2")
	@ActionLayout(cssClass = "boton")
	public String registrarEgreso(
			@ParameterLayout(named = "Doctor") final Doctor doctor) {
		final SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"dd MMMM YYYY HH:mm");
		Date egreso = new Date();
		final HorasTrabajadas horas = newTransientInstance(HorasTrabajadas.class);
		horas.setDoctor(doctor);
		horas.setIngreso("Egreso: " + egreso);
		persist(horas);
		return formatoFecha.format(egreso);

	}

}

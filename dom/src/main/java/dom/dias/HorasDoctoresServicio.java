package dom.dias;

//import javax.inject.Named;
//
import org.apache.isis.applib.AbstractFactoryAndRepository;
//import org.apache.isis.applib.DomainObjectContainer;
//import org.apache.isis.applib.annotation.DomainService;
//import org.apache.isis.applib.annotation.DomainServiceLayout;
//import org.apache.isis.applib.annotation.MemberOrder;
//import org.apache.isis.applib.annotation.ParameterLayout;
//
//import dom.doctor.Doctor;
//
//@DomainService(repositoryFor = Doctor.class)
//@DomainServiceLayout(named = "Doctor", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "3")
//@Named("Horas Doctor")

public class HorasDoctoresServicio extends AbstractFactoryAndRepository {

	// public String iconName() {
	// return "horas";
	// }
	//
	// @MemberOrder(name = "Doctor", sequence = "2.7")
	// public HorasDoctores crearTurnos(
	//
	// @ParameterLayout(named = "Doctor") final Doctor doctor,
	// @ParameterLayout(named = "Dias Desde") final DiasEnum diasDesde,
	// @ParameterLayout(named = "Dias Hasta") final DiasEnum diasHasta,
	// @ParameterLayout(named = "Horas Mañana Desde") final HorasMañanaEnum
	// horasMañanaDesde,
	// @ParameterLayout(named = "Horas Mañana Hasta") final HorasMañanaEnum
	// horasMañanaHasta,
	// @ParameterLayout(named = "Horas Tarde Desde") final HorasTardeEnum
	// horasTardeDesde,
	// @ParameterLayout(named = "Horas Tarde Hasta") final HorasTardeEnum
	// horasTardeHasta) {
	//
	// HorasDoctores horasDoctores = new HorasDoctores();
	// horasDoctores.setDoctor(doctor);
	// horasDoctores.setDiasDesde(diasDesde);
	// horasDoctores.setDiasHasta(diasHasta);
	// horasDoctores.setHorasMañanaDesde(horasMañanaDesde);
	// horasDoctores.setHorasMañanaHasta(horasMañanaHasta);
	// horasDoctores.setHorasTardeDesde(horasTardeDesde);
	// horasDoctores.setHorasTardeHasta(horasTardeHasta);
	// persist(horasDoctores);
	// container.flush();
	// return horasDoctores;
	// }
	//
	// // @MemberOrder(name = "Doctor", sequence = "2.7")
	// // public HorasDoctores crearHoras(
	// // @ParameterLayout(named = "Doctor") final Doctor doctor,
	// // @ParameterLayout(named = "7:00 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean siete,
	// // @ParameterLayout(named = "7:30 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean sieteTreinta,
	// // @ParameterLayout(named = "8:00 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean ocho,
	// // @ParameterLayout(named = "8:30 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean ochoTreinta,
	// // @ParameterLayout(named = "9:00 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean nueve,
	// // @ParameterLayout(named = "9:30 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean nueveTreinta,
	// // @ParameterLayout(named = "10:00 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean diez,
	// // @ParameterLayout(named = "10:30 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean diezTreinta,
	// // @ParameterLayout(named = "11:00 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean once,
	// // @ParameterLayout(named = "11:30 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean onceTreinta,
	// // @ParameterLayout(named = "12:00 am") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean doce,
	// // @ParameterLayout(named = "12:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean doceTreinta,
	// // @ParameterLayout(named = "13:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean trece,
	// // @ParameterLayout(named = "13:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean treceTreinta,
	// // @ParameterLayout(named = "14:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean catorce,
	// // @ParameterLayout(named = "14:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean catorceTreinta,
	// // @ParameterLayout(named = "15:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean quince,
	// // @ParameterLayout(named = "15:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean quinceTreinta,
	// // @ParameterLayout(named = "16:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean dieciseis,
	// // @ParameterLayout(named = "16:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean dieciseisTreinta,
	// // @ParameterLayout(named = "17:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean diecisiete,
	// // @ParameterLayout(named = "17:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean diecisieteTreinta,
	// // @ParameterLayout(named = "18:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean dieciocho,
	// // @ParameterLayout(named = "18:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean dieciochoTreinta,
	// // @ParameterLayout(named = "19:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean diecinueve,
	// // @ParameterLayout(named = "19:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean diecinueveTreinta,
	// // @ParameterLayout(named = "20:00 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean veinte,
	// // @ParameterLayout(named = "20:30 pm") @Parameter(optionality =
	// // Optionality.OPTIONAL) final Boolean veinteTreinta) {
	//
	// // HorasDoctores horasDoctores = new HorasDoctores();
	// // horasDoctores.setDoctor(doctor);
	// // int cont = 0;
	// // while (cont < 27) {
	// // if (siete == true) {
	// // horasDoctores.setSieteAm("7:00 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (sieteTreinta == true) {
	// // horasDoctores.setSieteTreintaAm("7:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (ocho == true) {
	// // horasDoctores.setOchoAm("8:00 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (ochoTreinta == true) {
	// // horasDoctores.setOchoTreintaAm("8:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (nueve == true) {
	// // horasDoctores.setNueveAm("9:00 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (nueveTreinta == true) {
	// // horasDoctores.setNueveTreintaAm("9:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (diez == true) {
	// // horasDoctores.setDiezAm("10:00 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (diezTreinta == true) {
	// // horasDoctores.setDiezTreintaAm("10:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (once == true) {
	// // horasDoctores.setOnceAm("11:00 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (onceTreinta == true) {
	// // horasDoctores.setOnceTreintaAm("11:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (doce == true) {
	// // horasDoctores.setDoceAm("12:00 am");
	// // cont++;
	// // } else
	// // cont++;
	// // if (doceTreinta == true) {
	// // horasDoctores.setDoceTreintaPm("12:30 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (trece == true) {
	// // horasDoctores.setUnaPm("13:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (treceTreinta == true) {
	// // horasDoctores.setUnaTreintaPm("13:30 pm");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (catorce == true) {
	// // horasDoctores.setDosPm("14:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (catorceTreinta == true) {
	// // horasDoctores.setDosTreintaPm("14:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (quince == true) {
	// // horasDoctores.setTresPm("15:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (quinceTreinta == true) {
	// // horasDoctores.setTresTreintaPm("15:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (dieciseis == true) {
	// // horasDoctores.setCuatroPm("16:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (dieciseisTreinta == true) {
	// // horasDoctores.setCuatroTreintaPm("16:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (diecisiete == true) {
	// // horasDoctores.setCincoPm("17:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (diecisieteTreinta == true) {
	// // horasDoctores.setCincoTreintaPm("17:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (dieciocho == true) {
	// // horasDoctores.setSeisPm("18:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (dieciochoTreinta == true) {
	// // horasDoctores.setSeisTreintaPm("18:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (diecinueve == true) {
	// // horasDoctores.setSietePm("19:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (diecinueveTreinta == true) {
	// // horasDoctores.setSieteTreintaPm("19:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// //
	// // if (veinte == true) {
	// // horasDoctores.setOchoPm("20:00 pm");
	// // cont++;
	// // } else
	// // cont++;
	// // if (veinteTreinta == true) {
	// // horasDoctores.setOchoTreintaPm("20:30 am");
	// // cont++;
	// // } else
	// // cont++;
	// // }
	//
	// // persist(horasDoctores);
	// // return horasDoctores;
	// // }
	//
	// @javax.inject.Inject
	// DomainObjectContainer container;

}
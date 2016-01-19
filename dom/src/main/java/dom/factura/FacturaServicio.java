package dom.factura;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import com.google.common.io.Resources;

import dom.paciente.Paciente;
import dom.vademecum.Vademecum;

@DomainService(repositoryFor = Factura.class)
@DomainServiceLayout(named = "Factura", menuBar = DomainServiceLayout.MenuBar.SECONDARY, menuOrder = "10")
public class FacturaServicio extends AbstractFactoryAndRepository {
	/**
	 * Constructor de la clase FacturaServicio
	 */
	public FacturaServicio() {
		// TODO Auto-generated constructor stub
	}

	@ActionLayout(cssClass = "boton")
	public Factura crearFactura(
			@ParameterLayout(named = "Paciente") final Paciente paciente,
			@ParameterLayout(named = "Cantidad") final int cant,
			@ParameterLayout(named = "Precio") final Double precio) {
		double precioTotal = 0;
		String nomb = "Coseguro-Atencion";
		final Factura factura = newTransientInstance(Factura.class);
		final ItemFactura item = newTransientInstance(ItemFactura.class);
		item.setCantidad(cant);
		item.setNombre(nomb);
		item.setPrecio(precio);
		persist(item);
		precioTotal = precioTotal + (cant * precio);

		factura.setPaciente(paciente);
		factura.addToItems(item);
		factura.setTotal(precioTotal);
		factura.setFechaHora(new Date());
		persist(factura);
		return factura;
	}

	// @MemberOrder(name = "Factura", sequence = "2.3")
	// public List<Factura> listarFactura() {
	// return container.allInstances(Factura.class);
	// }

	@ActionLayout(hidden = Where.EVERYWHERE)
	public List<Factura> autocompleteFactura(final String factura) {
		return allMatches(QueryDefault.create(Factura.class, "traerFacturas",
				factura.toUpperCase()));
	}

	/**
	 * // * Obtiene una lista de todas las facturas // * // * @return List
	 * <Factura> lista //
	 */
	@MemberOrder(name = "Factura", sequence = "3.2")
	public List<Factura> listarFactura() {
		final List<Factura> lista = allInstances(Factura.class);
		return lista;
	}

	private byte[] pdfAsBytes;

	@PostConstruct
	public void init() throws IOException {
		pdfAsBytes = Resources.toByteArray(Resources.getResource(
				this.getClass(), "plantilla.pdf"));
	}

	// @NotContributed(NotContributed.As.ASSOCIATION)
	// @NotInServiceMenu
	// @ActionSemantics(Of.SAFE)
	// @ActionLayout(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "3.3")
	public Blob imprimirFactura(final Factura _factura) throws Exception {

		try (PDDocument pdfDocument = cargarPlantilla(_factura)) {

			final ByteArrayOutputStream target = new ByteArrayOutputStream();
			pdfDocument.save(target);

			final String name = "Factura-" + _factura.getNumero() + ".pdf";
			final String mimeType = "application/pdf";
			final byte[] bytes = target.toByteArray();

			return new Blob(name, mimeType, bytes);
		}
	}

	private PDDocument cargarPlantilla(Factura _factura) throws Exception {
		PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(
				pdfAsBytes));

		PDAcroForm pdfForm = pdfDocument.getDocumentCatalog().getAcroForm();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy '-' HH:mm",
				new Locale("es_AR"));
		pdfForm.getField("fecha").setValue(
				formato.format(_factura.getFechaHora()));
		pdfForm.getField("numero").setValue(
				String.valueOf(_factura.getNumero()));
		pdfForm.getField("total").setValue(
				new DecimalFormat("#.00").format(_factura.getTotal()));

		int i = 1;
		Iterator<ItemFactura> iterador = _factura.getItems().iterator();
		while (i < 20 && iterador.hasNext()) {
			ItemFactura item = iterador.next();

			String txtDescripcion = "desc" + i;
			String txtPrecio = "precio" + i;

			pdfForm.getField(txtDescripcion).setValue(
					item.getCantidad() + " " + item.getNombre() + " ");

			pdfForm.getField(txtPrecio).setValue(
					new DecimalFormat("#.00").format(item.getPrecio()));

			i++;
		}
		return pdfDocument;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
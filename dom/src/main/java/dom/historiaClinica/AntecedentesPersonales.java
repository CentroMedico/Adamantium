package dom.historiaClinica;

import org.apache.isis.applib.annotation.MemberOrder;

public class AntecedentesPersonales {

	// {{ Alcohol (property)
	private String alcohol;

	@MemberOrder(sequence = "1")
	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(final String alcohol) {
		this.alcohol = alcohol;
	}

	// }}

	// {{ Tabaco (property)
	private String tabaco;

	@MemberOrder(sequence = "1")
	public String getTabaco() {
		return tabaco;
	}

	public void setTabaco(final String tabaco) {
		this.tabaco = tabaco;
	}

	// }}

	// {{ Drogas (property)
	private String drogas;

	@MemberOrder(sequence = "1")
	public String getDrogas() {
		return drogas;
	}

	public void setDrogas(final String drogas) {
		this.drogas = drogas;
	}

	// }}

	// {{ Infusiones (property)
	private String infusiones;

	@MemberOrder(sequence = "1")
	public String getInfusiones() {
		return infusiones;
	}

	public void setInfusiones(final String infusiones) {
		this.infusiones = infusiones;
	}

	// }}

	// {{ Alimentacion (property)
	private String alimentacion;

	@MemberOrder(sequence = "1")
	public String getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(final String alimentacion) {
		this.alimentacion = alimentacion;
	}

	// }}

	// {{ Dipsia (property)
	private String dipsia;

	@MemberOrder(sequence = "1")
	public String getDipsia() {
		return dipsia;
	}

	public void setDipsia(final String dipsia) {
		this.dipsia = dipsia;
	}

	// }}

	// {{ Diuresis (property)
	private String diuresis;

	@MemberOrder(sequence = "1")
	public String getDiuresis() {
		return diuresis;
	}

	public void setDiuresis(final String diuresis) {
		this.diuresis = diuresis;
	}

	// }}

	// {{ Catarsis (property)
	private String catarsis;

	@MemberOrder(sequence = "1")
	public String getCatarsis() {
		return catarsis;
	}

	public void setCatarsis(final String catarsis) {
		this.catarsis = catarsis;
	}

	// }}

	// {{ Somnia (property)
	private String somnia;

	@MemberOrder(sequence = "1")
	public String getSomnia() {
		return somnia;
	}

	public void setSomnia(final String somnia) {
		this.somnia = somnia;
	}

	// }}

	// {{ Infancia (property)
	private String infancia;

	@MemberOrder(sequence = "1")
	public String getInfancia() {
		return infancia;
	}

	public void setInfancia(final String infancia) {
		this.infancia = infancia;
	}
	// }}

}
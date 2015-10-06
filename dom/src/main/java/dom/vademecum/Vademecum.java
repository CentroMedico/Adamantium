/*
 Copyright 2015 Adamantium

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
package dom.vademecum;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;

@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "traerMedicamentos", language = "JDOQL", value = "SELECT "
				+ "FROM dom.vademecum.Vademecum WHERE producto == :nombre "
				+ " || producto.indexOf(:nombre) >= 0"),
		@javax.jdo.annotations.Query(name = "traerTodos", language = "JDOQL", value = "SELECT "
				+ " FROM dom.vademecum.Vademecum"), })
@DomainObject(autoCompleteRepository = VademecumServicio.class, autoCompleteAction = "autocompletarMedicamento")
@PersistenceCapable
public class Vademecum {
	/**
	 * Representa en UI el nombre "Doctor" en carga/modificacion.
	 */
	/*----------------------------------------------------*/
	public TranslatableString title() {
		return TranslatableString.tr("{nombre}", "nombre",
				" " + this.getProducto());
	}

	/**
	 * Obtiene el nombre del icono.
	 */
	/*----------------------------------------------------*/
	public String iconName() {
		return "vademecum";
	}

	// {{ CodProd (property)
	private String codProd;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(final String codProd) {
		this.codProd = codProd;
	}

	// }}

	// {{ Producto (property)
	private String producto;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getProducto() {
		return producto;
	}

	public void setProducto(final String producto) {
		this.producto = producto;
	}

	// }}

	// {{ Presentacion (property)
	private String presentacion;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(final String presentacion) {
		this.presentacion = presentacion;
	}

	// }}

	// {{ Tamaño (property)
	private String tamaño;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public String getTamaño() {
		return tamaño;
	}

	public void setTamaño(final String tamaño) {
		this.tamaño = tamaño;
	}

	// }}

	// {{ CodLab (property)
	private String codLab;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public String getCodLab() {
		return codLab;
	}

	public void setCodLab(final String codLab) {
		this.codLab = codLab;
	}

	// }}

	// {{ Laboratorio (property)
	private String laboratorio;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(final String laboratorio) {
		this.laboratorio = laboratorio;
	}

	// }}

	// {{ CodFamilia (property)
	private String codFamilia;

	@MemberOrder(sequence = "7")
	@Column(allowsNull = "false")
	public String getCodFamilia() {
		return codFamilia;
	}

	public void setCodFamilia(final String codFamilia) {
		this.codFamilia = codFamilia;
	}

	// }}

	// {{ Familia (property)
	private String familia;

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	public String getFamilia() {
		return familia;
	}

	public void setFamilia(final String familia) {
		this.familia = familia;
	}

	// }}

	// {{ CodPrincipioActivo (property)
	private String codPrincipioActivo;

	@MemberOrder(sequence = "9")
	@Column(allowsNull = "false")
	public String getCodPrincipioActivo() {
		return codPrincipioActivo;
	}

	public void setCodPrincipioActivo(final String codPrinActivo) {
		this.codPrincipioActivo = codPrinActivo;
	}

	// }}

	// {{ PrincipioActivo (property)
	private String principioActivo;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getPrincipioActivo() {
		return principioActivo;
	}

	public void setPrincipioActivo(final String principioActivo) {
		this.principioActivo = principioActivo;
	}

	// }}

	// {{ CodigodeBarras (property)
	private String codigodeBarras;

	@MemberOrder(sequence = "11")
	@Column(allowsNull = "false")
	public String getCodigodeBarras() {
		return codigodeBarras;
	}

	public void setCodigodeBarras(final String codBarras) {
		this.codigodeBarras = codBarras;
	}

	// }}

	// {{ Troquel (property)
	private String troquel;

	@MemberOrder(sequence = "12")
	@Column(allowsNull = "false")
	public String getTroquel() {
		return troquel;
	}

	public void setTroquel(final String troquel) {
		this.troquel = troquel;
	}

	// }}

	// {{ TipoReceta (property)
	private String tipoReceta;

	@MemberOrder(sequence = "13")
	@Column(allowsNull = "false")
	public String getTipoReceta() {
		return tipoReceta;
	}

	public void setTipoReceta(final String tipoReceta) {
		this.tipoReceta = tipoReceta;
	}
	// }}

}

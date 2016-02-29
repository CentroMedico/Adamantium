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
package dom.historiaclinica.vista;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RenderType;

import dom.historiaclinica.AdicionalesPaciente;
import dom.historiaclinica.AntecedentesFamiliares;
import dom.historiaclinica.AntecedentesPersonales;
import dom.historiaclinica.ExamenFisico;
import dom.historiaclinica.IndicacionesMedicas;
import dom.historiaclinica.Receta;

@MemberGroupLayout(columnSpans = { 6, 0, 6 }, left = { "lista" })
public class HistoriaView extends AbstractViewModel {

	/**
	 * Asigna el nombre al icono
	 * 
	 * @return String
	 */
	public String iconName() {
		return "Vista Historia Clinica";
	}

	/**
	 * Asigan el nombre al titulo
	 * 
	 * @return String
	 */
	public String title() {
		return "Vista Historia Clinica";
	}

	private String memento;

	@Override
	public String viewModelMemento() {
		// TODO Auto-generated method stub
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		// TODO Auto-generated method stub
		this.memento = memento;
	}

	/**
	 * Obtiene una Lista de Adicionales paciente
	 * 
	 * @return List<AdicionalesPaciente>
	 */
	@CollectionLayout(render = RenderType.EAGERLY)
	@MemberOrder(name = "izquierda", sequence = "1")
	public List<AdicionalesPaciente> getAdicionalesPaciente() {
		return servicioHistoriaView.listarAdicionalesPaciente();

	}

	/**
	 * Obtiene una Lista de Antecedentes Familiares
	 * 
	 * @return List<Antecedentes Familiares>
	 */

	@CollectionLayout(render = RenderType.EAGERLY)
	@MemberOrder(name = "izquierda", sequence = "1")
	public List<AntecedentesFamiliares> getAntecedentesFamiliares() {
		return servicioHistoriaView.listarAntecedentesFamiliares();
	}

	/**
	 * Obtiene una Lista de Antecedentes Personales
	 * 
	 * @return List<AntecedentesPersonales>
	 */

	@CollectionLayout(render = RenderType.EAGERLY)
	@MemberOrder(name = "izquierda", sequence = "1")
	public List<AntecedentesPersonales> getAntecedentesPersonales() {
		return servicioHistoriaView.listarAntecedentesPersonales();
	}

	/**
	 * Obtiene una Lista de Examen Fisico
	 * 
	 * @return List<ExamenFisico>
	 */

	@CollectionLayout(render = RenderType.EAGERLY)
	@MemberOrder(name = "izquierda", sequence = "1")
	public List<ExamenFisico> getExamenFisico() {
		return servicioHistoriaView.listarExamenFisico();
	}

	/**
	 * Obtiene una Lista de Receta
	 * 
	 * @return List<Receta>
	 */

	@CollectionLayout(render = RenderType.EAGERLY)
	@MemberOrder(name = "izquierda", sequence = "1")
	public List<Receta> getReceta() {
		return servicioHistoriaView.listarRecetas();
	}

	/**
	 * Obtiene una Lista de Indicaciones Medicas
	 * 
	 * @return List<IndicacionesMedicas>
	 */

	@CollectionLayout(render = RenderType.EAGERLY)
	@MemberOrder(name = "izquierda", sequence = "1")
	public List<IndicacionesMedicas> getIndicacionesMedicas() {
		return servicioHistoriaView.listarIndicacionesMedicas();
	}

	/**
	 * Inyeccion del servicio de HistoriaView
	 */
	@Inject
	private ServicioHistoriaView servicioHistoriaView;

}
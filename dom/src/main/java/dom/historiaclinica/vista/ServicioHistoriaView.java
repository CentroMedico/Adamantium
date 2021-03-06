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

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.Programmatic;

import dom.historiaclinica.AdicionalesPaciente;
import dom.historiaclinica.AntecedentesFamiliares;
import dom.historiaclinica.AntecedentesPersonales;
import dom.historiaclinica.ExamenFisico;
import dom.historiaclinica.HistoriaClinicaServicio;
import dom.historiaclinica.IndicacionesMedicas;
import dom.historiaclinica.Receta;

@DomainService
@DomainServiceLayout(named = "Historia Clinica", menuBar = DomainServiceLayout.MenuBar.PRIMARY, menuOrder = "100")
public class ServicioHistoriaView extends AbstractFactoryAndRepository {

	public HistoriaView mostrarHistoriaClinica() {
		return newViewModelInstance(HistoriaView.class, "historia");
	}

	/**
	 * Obtiene una Lista de todos los adicionales de un paciente
	 * 
	 * @return List<AdicionalesPaciente>
	 */

	@Programmatic
	public List<AdicionalesPaciente> listarAdicionalesPaciente() {
		return historiaClinicaServicio.listarAdicionalesPaciente();
	}

	/**
	 * Obtiene una Lista de todos los antecedentes familiares de un paciente
	 * 
	 * @return List<AntecedentesFamiliares>
	 */

	@Programmatic
	public List<AntecedentesFamiliares> listarAntecedentesFamiliares() {
		return historiaClinicaServicio.listarAntecedentesFamiliares();
	}

	/**
	 * Obtiene una Lista de todos los antecedentes personales de un paciente
	 * 
	 * @return List<AntecedentesPersonales>
	 */

	@Programmatic
	public List<AntecedentesPersonales> listarAntecedentesPersonales() {
		return historiaClinicaServicio.listarAntecedentesPersonales();
	}

	/**
	 * Obtiene una Lista de todos los examenes fisicos de un paciente
	 * 
	 * @return List<ExamenFisico>
	 */

	@Programmatic
	public List<ExamenFisico> listarExamenFisico() {
		return historiaClinicaServicio.listarExamenFisico();
	}

	/**
	 * Obtiene una Lista de todas las recetas de un paciente
	 * 
	 * @return List<Receta>
	 */

	@Programmatic
	public List<Receta> listarRecetas() {
		return historiaClinicaServicio.listarReceta();
	}

	/**
	 * Obtiene una Lista de todas las indicaciones medicas de un paciente
	 * 
	 * @return List<IndicacionesMedicas>
	 */

	@Programmatic
	public List<IndicacionesMedicas> listarIndicacionesMedicas() {
		return historiaClinicaServicio.listarIndicacionesMedicas();
	}

	@Inject
	private HistoriaClinicaServicio historiaClinicaServicio;

}
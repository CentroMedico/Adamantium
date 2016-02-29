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
package dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistoriaClinicaDataSource implements JRDataSource {

	private List<ReporteHistoriaClinica> listaHClinica = new ArrayList<ReporteHistoriaClinica>();
	private int indiceAgendaActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("paciente".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getPaciente();
		} else if ("estadoCivil".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getEstadoCivil();
		} else if ("trabajo".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTrabajo();
		} else if ("obraSocial".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getObraSocial();
		} else if ("educacion".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getEducacion();
		} else if ("tabaquismo".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTabaquismo1();
		} else if ("edadqueempezo".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getEdadqueempezo();
		} else if ("cantidaddeCigarrillos".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual)
					.getCantidaddeCigarrillos();
		} else if ("alchool".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAlchool();
		} else if ("criticas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCriticas();
		} else if ("tomaporlaMañana".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTomaporlaMañana();
		} else if ("drogas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getDrogas();
		} else if ("tipoDrogas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTipoDrogas();
		} else if ("actividad".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getActividad();
		} else if ("tipoActividad".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTipoActividad();
		} else if ("hta".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getHta();
		} else if ("diabetes".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getDiabetes();
		} else if ("coronaria".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCoronaria();
		} else if ("acv".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAcv();
		} else if ("epoc".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getEpoc();
		} else if ("alergias".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAlergias();
		} else if ("reumatica".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getReumatica();
		} else if ("oncologicas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getOncologicas();
		} else if ("tcb".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTbc();
		} else if ("hiv".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getHiv();
		} else if ("chagas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getChagas();
		} else if ("its".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getIts();
		} else if ("neurologicas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getNeurologicas();
		} else if ("transfuciones".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTransfuciones();
		} else if ("antecedentes".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAntecedentes();
		} else if ("hta1".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getHta1();
		} else if ("cardiopatias".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCardiopatias();
		} else if ("diabetes1".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getDiabetes1();
		} else if ("acv1".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAcv1();
		} else if ("cancerdeColon".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCancerdeColon();
		} else if ("cancerdePulmon".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCancerdePulmon();
		} else if ("cancerdeMama".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCancerdeMama();
		} else if ("consumodeDrogas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getConsumodeDrogas();
		}else if ("abusodeAlchool".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAbusodeAlchool();
		}else if ("depresion".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getDepresion();
		}else if ("piel".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getPiel();
		}else if ("utilizaLentes".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getUtilizalentes();
		}else if ("agudezaVisual".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAgudezaVisual();
		}else if ("oidos".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getOidos();
		}else if ("dentadura".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getDentadura();
		}else if ("pulmones".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getPulmones();
		}else if ("corazon".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getCorazon();
		}else if ("abdomen".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAbdomen();
		}else if ("genitales".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getGenitales();
		}else if ("mamas".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getMamas();
		}else if ("altura".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getAltura();
		}else if ("peso".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getPeso();
		}else if ("temperaturaCorporal".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTemperaturaCorporal();
		}else if ("frecuenciaCardiaca".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getFrecuenciaCardiaca();
		}else if ("frecuenciaRespiratoria".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getFrecuenciaRespiratoria();
		}else if ("tensionArterial".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getTensionArterial();
		}else if ("estadoGeneral".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getEstadoGeneral();
		}else if ("dni".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getDni();
		}else if ("fechanac".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getFechanac();
		}else if ("numCarnet".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getNumCarnet();
		}else if ("Sexo".equals(jrf.getName())) {
			valor = listaHClinica.get(indiceAgendaActual).getSexo();
		}

		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAgendaActual < listaHClinica.size();
	}

	public void addParticipante(ReporteHistoriaClinica historia) {
		this.listaHClinica.add(historia);
	}

}

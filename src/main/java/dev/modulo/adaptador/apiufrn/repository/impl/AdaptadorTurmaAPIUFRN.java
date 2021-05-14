package dev.modulo.adaptador.apiufrn.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import dev.modulo.adaptador.apiufrn.dto.DepartamentoDTO;
import dev.modulo.adaptador.apiufrn.dto.DocenteDTO;
import dev.modulo.adaptador.apiufrn.dto.TurmaDTO;
import dev.modulo.adaptador.apiufrn.repository.TurmaAPIUFRNRepository;

@Component
public class AdaptadorTurmaAPIUFRN extends AdaptadorAbstract implements TurmaAPIUFRNRepository {
	
	public AdaptadorTurmaAPIUFRN() {
		super();
	}
	
	@Override
	public List<TurmaDTO> retornaTurmasPorIdCentroAnoPeriodo(Integer idCentro, Integer ano, Integer periodo, String clientId, String clientSecret) {
		   setClientIdAndClientSecret(clientId, clientSecret);
		   /*
			 * ### Tipos de Unidade:
			 * [{"id-tipo-unidade":1,"descricao":"Departamento"},
			 * {"id-tipo-unidade":2,"descricao":"Escola"},
			 * {"id-tipo-unidade":3,"descricao":"Programa de Pós Graduação"},
			 * {"id-tipo-unidade":4,"descricao":"Centro"},
			 * {"id-tipo-unidade":5,"descricao":"Unidade Acadêmica Específica"},
			 * {"id-tipo-unidade":6,"descricao":"Coordenação de Curso"},
			 * {"id-tipo-unidade":7,"descricao":"Pró-Reitoria"},
			 * {"id-tipo-unidade":8,"descricao":"Coordencação de Curso Lato"},
			 * {"id-tipo-unidade":9,"descricao":"Residência Médica"},
			 * {"id-tipo-unidade":10,"descricao":"Coordenação de Pós-Graduação"}]
			 * */
			ResponseEntity<String> resposta = getRespostaJSON(getUrlBaseSistemas() + "unidade/"+ getVersao() +"/unidades?id-unidade-responsavel="+idCentro+"&id-tipo-unidade=1,2,3,5,8,10&limit=100&offset=0");
			System.out.println("### Departamentos: " + resposta.getBody());
			JSONArray jsonArrayDepartamentos = new JSONArray(resposta.getBody());
			Iterator<Object> iteratorArrayDepartamentos = jsonArrayDepartamentos.iterator();
			List<TurmaDTO> turmasSIGAA = new ArrayList<TurmaDTO>(0);
			while (iteratorArrayDepartamentos.hasNext()) {
				JSONObject jsonObjectDepartamento = (JSONObject) iteratorArrayDepartamentos.next();
				Integer idUnidade = (Integer) jsonObjectDepartamento.get("id-unidade");
				String nomeUnidade = (String) jsonObjectDepartamento.get("nome-unidade");
				System.out.println("### Id-Unidade:" + idUnidade + " - Unidade: " + nomeUnidade);
				/*
				 * ### Situações de Turma: 
				 * [{"id-situacao-turma":1,"descricao":"ABERTA"},
				 * {"id-situacao-turma":2,"descricao":"A DEFINIR DOCENTE"},
				 * {"id-situacao-turma":3,"descricao":"CONSOLIDADA"},
				 * {"id-situacao-turma":4,"descricao":"EXCLUÍDA"},
				 * {"id-situacao-turma":6,"descricao":"INTERROMPIDA"},
				 * {"id-situacao-turma":7,"descricao":"AGUARDANDO HOMOLOGAÇÃO"}]
				 * */
				resposta = getRespostaJSONPaginado(getUrlBaseSistemas() +"turma/"+ getVersao() +"/turmas?id-unidade="+idUnidade+"&ano="+ano+"&periodo="+periodo+"&id-situacao-turma=1,2");
				HttpHeaders httpHeadersTurmas = resposta.getHeaders();
				Double doubleTotalTurmas = new Double(httpHeadersTurmas.getFirst("x-total"));
				System.out.println("### Total turmas: " + doubleTotalTurmas);
				Double doubleLacos = doubleTotalTurmas/100;
				int inteiroLacos = doubleLacos.intValue();
				inteiroLacos++;
				int offSetTurmas = 0;
				for (int i = 0; i < inteiroLacos; i++) {
					System.out.println("### Laço das turmas ###");
					resposta = getRespostaJSON(getUrlBaseSistemas() + "turma/"+ getVersao() +"/turmas?id-unidade="+idUnidade+"&ano="+ano+"&periodo="+periodo+"&id-situacao-turma=1,2&limit=100&offset="+offSetTurmas);
					//System.out.println("### Turmas do Departamento: " + resposta.getBody());
					JSONArray jsonArrayTurmas = new JSONArray(resposta.getBody());
					Iterator<Object> iteratorArrayTurmas = jsonArrayTurmas.iterator();
					// id-turma,ano,periodo,codigo-turma,local,descricao-horario,id-componente,nome-componente,codigo-componente,sigla-nivel,id-unidade,id-situacao-turma,subturma
					while (iteratorArrayTurmas.hasNext()) {
						JSONObject jsonObjectTurma = (JSONObject) iteratorArrayTurmas.next();
						Integer idTurma = (Integer) jsonObjectTurma.get("id-turma");
						TurmaDTO turmaDTO = new TurmaDTO();
						turmaDTO.setAgrupadora(false);
						turmaDTO.setCapacidadeAluno((Integer) jsonObjectTurma.get("capacidade-aluno"));
						turmaDTO.setCargaHoraria(0);
						turmaDTO.setCodigo((String) jsonObjectTurma.get("codigo-turma"));
						turmaDTO.setCodigoComponente((String) jsonObjectTurma.get("codigo-componente"));
						
						try {
							turmaDTO.setDescricaoHorario((String) jsonObjectTurma.get("descricao-horario"));
						} catch (Exception e) {
							System.out.println("### Turma com horário NULO. Valor 'INDEFINIDO' atribuído para o horário");
							turmaDTO.setDescricaoHorario("INDEFINIDO");
						} 
						
						/*
						 * ### Tipos de Participantes: 
						 * [{"id-tipo-participante":1,"descricao":"DOCENTE"},
						 * {"id-tipo-participante":2,"descricao":"DOCENTE_ASSISTIDO"},
						 * {"id-tipo-participante":3,"descricao":"MONITOR"},
						 * {"id-tipo-participante":4,"descricao":"DISCENTE"},
						 * {"id-tipo-participante":5,"descricao":"DESCONHECIDO"}]
						 * */
						resposta = getRespostaJSON(getUrlBaseSistemas() + "turma/" + getVersao() + "/participantes?id-turma="+idTurma+"&id-tipo-participante=1");
						JSONArray jsonArrayDocentes = new JSONArray(resposta.getBody());
						Iterator<Object> iteratorArrayDocentes = jsonArrayDocentes.iterator();
						while (iteratorArrayDocentes.hasNext()) {
							JSONObject jsonObjectDocente = (JSONObject) iteratorArrayDocentes.next();
							DocenteDTO docenteDTO = new DocenteDTO();
							docenteDTO.setChDedicada(new Long(0));
							Integer idDocenteInteiro = (Integer) jsonObjectDocente.get("id-participante");
							docenteDTO.setIdServidor(idDocenteInteiro.longValue());
							Integer idTurmaInteiro = (Integer) jsonObjectDocente.get("id-turma");
							docenteDTO.setIdTurma(idTurmaInteiro.longValue());
							docenteDTO.setNome((String) jsonObjectDocente.get("nome"));
							turmaDTO.getDocentesList().add(docenteDTO);
						}
						// ---------------------------------------------
						Integer idLegal = (Integer) jsonObjectTurma.get("id-turma");
						if (idLegal.equals(57625574)) {
							//System.out.println("#### Ela é agrupadora. Como tah o id-turma-agrupadora: " + jsonObjectTurma.get("id-turma-agrupadora"));
						}
						// ---------------------------------------------
						
						turmaDTO.setId((Integer) jsonObjectTurma.get("id-turma"));
						Integer idTurmaAgrupadora = (Integer) jsonObjectTurma.get("id-turma-agrupadora");
						turmaDTO.setIdTurmaAgrupadora(idTurmaAgrupadora);
						if (idTurmaAgrupadora == null) {
							turmaDTO.setAgrupadora(true);
							//System.out.println("#### Achei uma turma agrupadora! " + turmaDTO.getId() + " - Código Componente: " + turmaDTO.getCodigoComponente());	
						}
						if (idTurmaAgrupadora > 0) {
							//System.out.println("#### Achei o ID de uma turma agrupadora! " + idTurmaAgrupadora + " - Código Componente: " + turmaDTO.getCodigoComponente());
							//System.out.println("#### Subturma deve ser true: " + jsonObjectTurma.get("subturma"));
						}
						//turmaDTO.setIdTurmaAgrupadora(idTurmaAgrupadora);
						turmaDTO.setIdUnidade(idUnidade);
						//turmaDTO.setLocal(local);
						turmaDTO.setNivel((String) jsonObjectTurma.get("sigla-nivel"));
						turmaDTO.setNomeComponente((String) jsonObjectTurma.get("nome-componente"));
						
						ResponseEntity<String> respostaQtdDiscentes = getRespostaJSONPaginado(getUrlBaseSistemas() + "turma/" + getVersao() + "/participantes?id-turma="+idTurma+"&id-tipo-participante=4");
						HttpHeaders httpHeaders = respostaQtdDiscentes.getHeaders();
						
						Double doubleTotalDiscentes = new Double(httpHeaders.getFirst("x-total"));
						turmaDTO.setQtdMatriculados(doubleTotalDiscentes.intValue());
										
						turmaDTO.setSituacao("");
						turmaDTO.setTotalSolicitacoes(0);
						turmaDTO.setUnidade(nomeUnidade);
						
						turmasSIGAA.add(turmaDTO);
						//##### DUVIDAS ####
						// Dois ids para Docente, porque?
					}
					
					offSetTurmas = offSetTurmas + 100;
				}
			}
	      return turmasSIGAA;
	   }
	   
		@Override
		public List<DepartamentoDTO> retornaUnidadesAcademicasPorIdCentro(Integer idCentro, String clientId, String clientSecret) {
		   setClientIdAndClientSecret(clientId, clientSecret);
		   	ResponseEntity<String> resposta = getRespostaJSONPaginado(getUrlBaseSistemas() + "unidade/"+ getVersao() +"/unidades?id-unidade-responsavel="+idCentro+"&id-tipo-unidade=1,2,3,5,6,8,10");
			HttpHeaders httpHeadersTurmas = resposta.getHeaders();
			// Obtendo o total de departamentos
			Double doubleTotalTurmas = new Double(httpHeadersTurmas.getFirst("x-total"));
			// Calculando a quantidade de páginas e de repetições no laço(for) seguinte
			Double doubleLacos = doubleTotalTurmas/100;
			int inteiroLacos = doubleLacos.intValue();
			inteiroLacos++;
			/*
			 * A variável offSet define a posição inicial da paginação
			 * Como a paginação é feita de 100 em 100 registros, então o offSet começa em 0, depois 100 (se tiver mais uma página), depois 200 (se tiver mais outra página), e assim por diante
			 */
			int offSetDepartamentos = 0;
			
			List<DepartamentoDTO> retorno = new ArrayList<DepartamentoDTO>(0);
			
			for (int i = 0; i < inteiroLacos; i++) {
				resposta = getRespostaJSON(getUrlBaseSistemas() + "unidade/"+ getVersao() +"/unidades?id-unidade-responsavel="+idCentro+"&id-tipo-unidade=1,2,3,5,6,8,10&limit=100&offset="+offSetDepartamentos);
				JSONArray jsonArrayDepartamentos = new JSONArray(resposta.getBody());
				Iterator<Object> iteratorArrayDepartamentos = jsonArrayDepartamentos.iterator();
				while (iteratorArrayDepartamentos.hasNext()) {
					JSONObject jsonObjectDepartamento = (JSONObject) iteratorArrayDepartamentos.next();
					DepartamentoDTO departamentoDTO = new DepartamentoDTO();
					departamentoDTO.setCodigoUnidade((Integer) jsonObjectDepartamento.get("codigo-unidade"));
					try {
						departamentoDTO.setEmail((String) jsonObjectDepartamento.get("email"));
					} catch (Exception e) {
						departamentoDTO.setEmail(new String(""));
					}
					departamentoDTO.setHierarquiaOrganizacional((String) jsonObjectDepartamento.get("hierarquia-organizacional"));
					try {
						departamentoDTO.setIdAmbienteOrganizacional((Integer) jsonObjectDepartamento.get("id-ambiente-organizacional"));
					} catch (Exception e) {
						departamentoDTO.setIdAmbienteOrganizacional(new Integer(0));
					}
					
					try {
						departamentoDTO.setIdAreaAtuacaoUnidade((Integer) jsonObjectDepartamento.get("id-area-atuacao-unidade"));
					} catch (Exception e) {
						departamentoDTO.setIdAreaAtuacaoUnidade(new Integer(0));
					}
					try {
						departamentoDTO.setIdClassificacaoUnidade((Integer) jsonObjectDepartamento.get("id-classificacao-unidade"));
					} catch (Exception e) {
						departamentoDTO.setIdClassificacaoUnidade(new Integer(0));
					}
					try {
						departamentoDTO.setIdMunicipio((Integer) jsonObjectDepartamento.get("id-municipio"));
					} catch (Exception e) {
						departamentoDTO.setIdMunicipio(new Integer(0));
					}
					//departamentoDTO.setIdNivelOrganizacional((Integer) jsonObjectDepartamento.get("id-nivel-organizacional"));
					//departamentoDTO.setIdTipoUnidadeOrganizacional((Integer) jsonObjectDepartamento.get("id-tipo-unidade-organizacional"));
					departamentoDTO.setIdUnidade((Integer) jsonObjectDepartamento.get("id-unidade"));
					departamentoDTO.setIdUnidadeGestora((Integer) jsonObjectDepartamento.get("id-unidade-gestora"));
					departamentoDTO.setNomeUnidade((String) jsonObjectDepartamento.get("nome-unidade"));
					departamentoDTO.setSigla((String) jsonObjectDepartamento.get("sigla"));
					//departamentoDTO.setTelefones((String) jsonObjectDepartamento.get("telefones"));
					departamentoDTO.setUnidadePatrimonial((Boolean) jsonObjectDepartamento.get("unidade-patrimonial"));
					retorno.add(departamentoDTO);
				}
				offSetDepartamentos = offSetDepartamentos + 100;
			}
			
		   return retorno;
	   }
	   
	   @Override
	   public List<TurmaDTO> retornaTurmasPorIdDepartamentoAnoPeriodo(Integer idDepartamento, Integer ano, Integer periodo, String clientId, String clientSecret) {
		   List<TurmaDTO> turmasSIGAA = new ArrayList<TurmaDTO>(0);
		   /*
			 * ### Situações de Turma: 
			 * [{"id-situacao-turma":1,"descricao":"ABERTA"},
			 * {"id-situacao-turma":2,"descricao":"A DEFINIR DOCENTE"},
			 * {"id-situacao-turma":3,"descricao":"CONSOLIDADA"},
			 * {"id-situacao-turma":4,"descricao":"EXCLUÍDA"},
			 * {"id-situacao-turma":6,"descricao":"INTERROMPIDA"},
			 * {"id-situacao-turma":7,"descricao":"AGUARDANDO HOMOLOGAÇÃO"}]
			 * */
		   	ResponseEntity<String> resposta = getRespostaJSONPaginado(getUrlBaseSistemas() +"turma/"+ getVersao() +"/turmas?id-unidade="+idDepartamento+"&ano="+ano+"&periodo="+periodo+"&id-situacao-turma=1,2");
			HttpHeaders httpHeadersTurmas = resposta.getHeaders();
			Double doubleTotalTurmas = new Double(httpHeadersTurmas.getFirst("x-total"));
			System.out.println("### Total turmas: " + doubleTotalTurmas);
			Double doubleLacos = doubleTotalTurmas/100;
			int inteiroLacos = doubleLacos.intValue();
			inteiroLacos++;
			int offSetTurmas = 0;
			for (int i = 0; i < inteiroLacos; i++) {
				resposta = getRespostaJSON(getUrlBaseSistemas() + "turma/"+ getVersao() +"/turmas?id-unidade="+idDepartamento+"&ano="+ano+"&periodo="+periodo+"&id-situacao-turma=1,2&limit=100&offset="+offSetTurmas);
				//System.out.println("### Turmas do Departamento: " + resposta.getBody());
				JSONArray jsonArrayTurmas = new JSONArray(resposta.getBody());
				Iterator<Object> iteratorArrayTurmas = jsonArrayTurmas.iterator();
				// id-turma,ano,periodo,codigo-turma,local,descricao-horario,id-componente,nome-componente,codigo-componente,sigla-nivel,id-unidade,id-situacao-turma,subturma
				while (iteratorArrayTurmas.hasNext()) {
					JSONObject jsonObjectTurma = (JSONObject) iteratorArrayTurmas.next();
					Integer idTurma = (Integer) jsonObjectTurma.get("id-turma");
					TurmaDTO turmaDTO = new TurmaDTO();
					turmaDTO.setAgrupadora(false);
					turmaDTO.setCapacidadeAluno((Integer) jsonObjectTurma.get("capacidade-aluno"));
					turmaDTO.setCargaHoraria(0);
					turmaDTO.setCodigo((String) jsonObjectTurma.get("codigo-turma"));
					turmaDTO.setCodigoComponente((String) jsonObjectTurma.get("codigo-componente"));
					
					try {
						turmaDTO.setDescricaoHorario((String) jsonObjectTurma.get("descricao-horario"));
					} catch (Exception e) {
						turmaDTO.setDescricaoHorario("INDEFINIDO");
					} 
					
					/*
					 * ### Tipos de Participantes: 
					 * [{"id-tipo-participante":1,"descricao":"DOCENTE"},
					 * {"id-tipo-participante":2,"descricao":"DOCENTE_ASSISTIDO"},
					 * {"id-tipo-participante":3,"descricao":"MONITOR"},
					 * {"id-tipo-participante":4,"descricao":"DISCENTE"},
					 * {"id-tipo-participante":5,"descricao":"DESCONHECIDO"}]
					 * */
					resposta = getRespostaJSON(getUrlBaseSistemas() + "turma/" + getVersao() + "/participantes?id-turma="+idTurma+"&id-tipo-participante=1");
					JSONArray jsonArrayDocentes = new JSONArray(resposta.getBody());
					Iterator<Object> iteratorArrayDocentes = jsonArrayDocentes.iterator();
					while (iteratorArrayDocentes.hasNext()) {
						JSONObject jsonObjectDocente = (JSONObject) iteratorArrayDocentes.next();
						DocenteDTO docenteDTO = new DocenteDTO();
						docenteDTO.setChDedicada(new Long(0));
						Integer idDocenteInteiro = (Integer) jsonObjectDocente.get("id-participante");
						docenteDTO.setIdServidor(idDocenteInteiro.longValue());
						Integer idTurmaInteiro = (Integer) jsonObjectDocente.get("id-turma");
						docenteDTO.setIdTurma(idTurmaInteiro.longValue());
						docenteDTO.setNome((String) jsonObjectDocente.get("nome"));
						turmaDTO.getDocentesList().add(docenteDTO);
					}
					// ---------------------------------------------
					Integer idLegal = (Integer) jsonObjectTurma.get("id-turma");
					if (idLegal.equals(57625574)) {
						//System.out.println("#### Ela é agrupadora. Como tah o id-turma-agrupadora: " + jsonObjectTurma.get("id-turma-agrupadora"));
					}
					// ---------------------------------------------
					
					turmaDTO.setId((Integer) jsonObjectTurma.get("id-turma"));
					Integer idTurmaAgrupadora = (Integer) jsonObjectTurma.get("id-turma-agrupadora");
					turmaDTO.setIdTurmaAgrupadora(idTurmaAgrupadora);
					if (idTurmaAgrupadora == null) {
						turmaDTO.setAgrupadora(true);
						//System.out.println("#### Achei uma turma agrupadora! " + turmaDTO.getId() + " - Código Componente: " + turmaDTO.getCodigoComponente());	
					}
					if (idTurmaAgrupadora > 0) {
						//System.out.println("#### Achei o ID de uma turma agrupadora! " + idTurmaAgrupadora + " - Código Componente: " + turmaDTO.getCodigoComponente());
						//System.out.println("#### Subturma deve ser true: " + jsonObjectTurma.get("subturma"));
					}
					//turmaDTO.setIdTurmaAgrupadora(idTurmaAgrupadora);
					turmaDTO.setIdUnidade(idDepartamento);
					//turmaDTO.setLocal(local);
					turmaDTO.setNivel((String) jsonObjectTurma.get("sigla-nivel"));
					turmaDTO.setNomeComponente((String) jsonObjectTurma.get("nome-componente"));
					
					ResponseEntity<String> respostaQtdDiscentes = getRespostaJSONPaginado(getUrlBaseSistemas() + "turma/" + getVersao() + "/participantes?id-turma="+idTurma+"&id-tipo-participante=4");
					HttpHeaders httpHeaders = respostaQtdDiscentes.getHeaders();
					
					Double doubleTotalDiscentes = new Double(httpHeaders.getFirst("x-total"));
					turmaDTO.setQtdMatriculados(doubleTotalDiscentes.intValue());
									
					turmaDTO.setSituacao("");
					turmaDTO.setTotalSolicitacoes(0);
					//turmaDTO.setUnidade(nomeUnidade);
					
					turmasSIGAA.add(turmaDTO);
					//##### DUVIDAS ####
					// Dois ids para Docente, porque?
				}
				
				offSetTurmas = offSetTurmas + 100;
			}
			return turmasSIGAA;
		}

}

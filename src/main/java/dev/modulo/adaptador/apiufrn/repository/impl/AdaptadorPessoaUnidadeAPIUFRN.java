package dev.modulo.adaptador.apiufrn.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import dev.modulo.adaptador.apiufrn.conversor.Conversor;
import dev.modulo.adaptador.apiufrn.dto.DepartamentoDTO;
import dev.modulo.adaptador.apiufrn.dto.LocalizacaoServidorDTO;
import dev.modulo.adaptador.apiufrn.dto.ServidorDTO;
import dev.modulo.adaptador.apiufrn.repository.PessoaUnidadeAPIUFRNRepository;

@Component
public class AdaptadorPessoaUnidadeAPIUFRN extends AdaptadorAbstract implements PessoaUnidadeAPIUFRNRepository {

	public AdaptadorPessoaUnidadeAPIUFRN() {
		super();
	}

	   /**
	    * <p>Retorna todos os departamentos vinculados a um Centro.</p>
	    *
	    *
	    * @param doisPrimeirosDigitosCodigoCentro uma string com os dois primeiros dígitos do código do Centro
	    * @param clientId string correspondente ao clientId
	    * @param clientSecret string correspondente ao clientSecret
	    * @return lista de departamentos vinculados a um Centro.
	    * @since 1.0-SNAPSHOT
	    */
	   @Override
	   public List<DepartamentoDTO> retornaTodosDepartamentosPorDoisPrimeirosDigitosCodigoCentro(String doisPrimeirosDigitosCodigoCentro, String clientId, String clientSecret) {
		   setClientIdAndClientSecret(clientId, clientSecret);
		   	ResponseEntity<String> resposta = getRespostaJSONPaginado(getUrlBaseSistemas() + "unidade/"+ getVersao() +"/unidades?codigo-unidade="+doisPrimeirosDigitosCodigoCentro+"%");
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
				resposta = getRespostaJSON(getUrlBaseSistemas() + "unidade/"+ getVersao() +"/unidades?codigo-unidade="+doisPrimeirosDigitosCodigoCentro+"%&limit=100&offset="+offSetDepartamentos);
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
					try {
						departamentoDTO.setTelefones((String) jsonObjectDepartamento.get("telefones"));	
					} catch (Exception e) {
						departamentoDTO.setTelefones("");
					}
					departamentoDTO.setUnidadePatrimonial((Boolean) jsonObjectDepartamento.get("unidade-patrimonial"));
					retorno.add(departamentoDTO);
				}
				offSetDepartamentos = offSetDepartamentos + 100;
			}
			
		   return retorno;
	   }
	   
	   /**
	    * <p>Retorna todos os servidores ativos de uma unidade.</p>
	    *
	    *
	    * @param idUnidade o identificador da unidade
	    * @param clientId string correspondente ao clientId
	    * @param clientSecret string correspondente ao clientSecret
	    * @return lista de servidores ativos de uma unidade.
	    * @since 1.0-SNAPSHOT
	    */
	   @Override
	   public List<ServidorDTO> retornaServidoresAtivosPorIdUnidadeLotacao(Integer idUnidadeLotacao, String clientId, String clientSecret) { 
		   setClientIdAndClientSecret(clientId, clientSecret);
		   List<ServidorDTO> retorno = new ArrayList<ServidorDTO>(0);
		   //[{"id-categoria":1,"descricao":"Docente"},
		   // {"id-categoria":2,"descricao":"Técnico Administrativo"},
		   // {"id-categoria":3,"descricao":"Não especificado"},
		   // {"id-categoria":4,"descricao":"Estagiário"},
		   // {"id-categoria":6,"descricao":"Médico Residente"}]
		  
		   // O parametro 'ativo' igual a 'true' é utilizado para os registros por servidor não virem duplicados.
		   // Se, por exemplo, um servidor que antes era técnico em TI passar em outro concurso para Analista de TI, terá 2 registros/vínculos no sistema, mas apenas um desses registros estará 'ativo'
		   ResponseEntity<String> resposta = getRespostaJSONPaginado(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/servidores?ativo=true&id-lotacao="+idUnidadeLotacao);
		   
		   HttpHeaders httpHeadersServidores = resposta.getHeaders();
		   Double doubleTotalServidores = new Double(httpHeadersServidores.getFirst("x-total"));
		   Double doubleLacos = doubleTotalServidores/100;
		   int inteiroLacos = doubleLacos.intValue();
		   inteiroLacos++;
		   int offSetServidores = 0;
		   for (int i = 0; i < inteiroLacos; i++) {
			   	resposta = getRespostaJSON(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/servidores?ativo=true&id-lotacao="+idUnidadeLotacao+"&limit=100&offset="+offSetServidores);
			   	JSONArray jsonArrayServidores = new JSONArray(resposta.getBody());
				Iterator<Object> iteratorArrayServidores = jsonArrayServidores.iterator();
				while (iteratorArrayServidores.hasNext()) {
					JSONObject jsonObjectServidor = (JSONObject) iteratorArrayServidores.next();
					retorno.add(Conversor.convertJSONObjectToServidorDTO(jsonObjectServidor));
				}
				offSetServidores = offSetServidores + 100;
		   }
		   
		   return retorno;
	   }
	   
	   @Override
	   public List<LocalizacaoServidorDTO> retornaLocalizacoesDeServidoresAtivosPorIdUnidadeLocalizacao(Integer idUnidadeLocalizacao, String clientId, String clientSecret) {
		   setClientIdAndClientSecret(clientId, clientSecret);
		   List<LocalizacaoServidorDTO> retorno = new ArrayList<LocalizacaoServidorDTO>(0);
		   ResponseEntity<String> resposta = getRespostaJSONPaginado(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/localizacoes-servidores?ativo=true&id-unidade-localizacao="+idUnidadeLocalizacao);
		   HttpHeaders httpHeadersServidores = resposta.getHeaders();
		   Double doubleTotalServidores = new Double(httpHeadersServidores.getFirst("x-total"));
		   Double doubleLacos = doubleTotalServidores/100;
		   int inteiroLacos = doubleLacos.intValue();
		   inteiroLacos++;
		   int offSetLocalizacoesServidores = 0;
		   for (int i = 0; i < inteiroLacos; i++) {
			   resposta = getRespostaJSON(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/localizacoes-servidores?ativo=true&id-unidade-localizacao="+idUnidadeLocalizacao+"&limit=100&offset="+offSetLocalizacoesServidores);
			   JSONArray jsonArrayLocalizacoes = new JSONArray(resposta.getBody());
			   Iterator<Object> iteratorArrayLocalizacoes = jsonArrayLocalizacoes.iterator();
			   while (iteratorArrayLocalizacoes.hasNext()) {
				   JSONObject jsonObjectLocalizacaoServidor = (JSONObject) iteratorArrayLocalizacoes.next();
				   retorno.add(Conversor.convertJSONObjectToLocalizacaoServidorDTO(jsonObjectLocalizacaoServidor));
			   }
			   offSetLocalizacoesServidores = offSetLocalizacoesServidores + 100;
		   }

		   return retorno;
		}
	   
	   @Override
	   public ServidorDTO retornaServidorPorId(Integer idServidor, String clientId, String clientSecret) {
		   setClientIdAndClientSecret(clientId, clientSecret);
		   ResponseEntity<String> resposta = getRespostaJSON(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/servidores/"+idServidor);
		   JSONObject jsonObjectServidor = new JSONObject(resposta.getBody());
		   return Conversor.convertJSONObjectToServidorDTO(jsonObjectServidor);
	   }

	@Override
	public List<LocalizacaoServidorDTO> retornaLocalizacoesDeServidoresAtivosPorIdServidor(Integer idServidor, String clientId, String clientSecret) {
		setClientIdAndClientSecret(clientId, clientSecret);
		   List<LocalizacaoServidorDTO> retorno = new ArrayList<LocalizacaoServidorDTO>(0);
		   ResponseEntity<String> resposta = getRespostaJSONPaginado(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/localizacoes-servidores?ativo=true&id-servidor="+idServidor);
		   HttpHeaders httpHeadersServidores = resposta.getHeaders();
		   Double doubleTotalServidores = new Double(httpHeadersServidores.getFirst("x-total"));
		   Double doubleLacos = doubleTotalServidores/100;
		   int inteiroLacos = doubleLacos.intValue();
		   inteiroLacos++;
		   int offSetLocalizacoesServidores = 0;
		   for (int i = 0; i < inteiroLacos; i++) {
			   resposta = getRespostaJSON(getUrlBaseSistemas() +"pessoa/"+ getVersao() +"/localizacoes-servidores?ativo=true&id-servidor="+idServidor+"&limit=100&offset="+offSetLocalizacoesServidores);
			   JSONArray jsonArrayLocalizacoes = new JSONArray(resposta.getBody());
			   Iterator<Object> iteratorArrayLocalizacoes = jsonArrayLocalizacoes.iterator();
			   while (iteratorArrayLocalizacoes.hasNext()) {
				   JSONObject jsonObjectLocalizacaoServidor = (JSONObject) iteratorArrayLocalizacoes.next();
				   retorno.add(Conversor.convertJSONObjectToLocalizacaoServidorDTO(jsonObjectLocalizacaoServidor));
			   }
			   offSetLocalizacoesServidores = offSetLocalizacoesServidores + 100;
		   }

		   return retorno;
	}
	   
	   // ABAIXO, exemplos de consultas que podem ser uteis no futuro
	   
	   // Como obter os dados de uma pessoa fisica (aluno, servidor, professor) pelo seu CPF
	   //ResponseEntity<String> resposta  = getRespostaJSON(urlBaseSistemas +"pessoa/"+ versao +"/pessoas/7617017456/detalhe");
	   //System.out.println("### Quem é o cantor(2): " + resposta.getBody());
	   
	   // Como obter os dados de um discente pela sua matricula
	   //ResponseEntity<String> resposta = getRespostaJSON(urlBaseSistemas +"discente/"+ versao +"/discentes?matricula=2016096884");
	   //System.out.println("### Quem é o cantor(3): " + resposta.getBody());
	
}

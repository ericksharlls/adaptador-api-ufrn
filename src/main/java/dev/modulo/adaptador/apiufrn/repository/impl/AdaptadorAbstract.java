package dev.modulo.adaptador.apiufrn.repository.impl;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class AdaptadorAbstract {

	// URL para o Ambiente de Produção
	private String urlBaseAutenticacao = "https://autenticacao.ufrn.br/";

	// URL Base de Autenticacao para o Ambiente de Testes
	//private String urlBaseAutenticacaoAmbienteTestes = "https://autenticacao.info.ufrn.br/";
	
	// URL Base da API para o Ambiente de Produção
	private String urlBaseSistemas = "https://api.ufrn.br/";
	
	// URL Base da API para o Ambiente de Testes
	//private String urlBaseSistemasTestes = "https://api.info.ufrn.br/";
	
	// private String versao = "v0.1"; - Antiga versao
	private String versao = "v1";

	private String clientId, clientSecret;

	/*
	 * xpiKey para ambiente de Testes
	 */
	//private String xApiKey = "dyqK8NiMhOwQJxVH8SBF1iYjC5ou74YSMtd4HB1m";

	/*
	 * xpiKey para ambiente de Produção
	 */
	private String xApiKey = "6U3N5Ki6yP2xFRtRdfhJ9ty7IGmZBNXxHHf9aSh5";

	public AdaptadorAbstract() {
		this.clientId = new String();
		this.clientSecret = new String();
	}

	protected void setClientIdAndClientSecret(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	protected ResponseEntity<String> getRespostaJSON(String url) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> responseEntity = template.exchange(getURLAutenticacao(), HttpMethod.POST, null,
				String.class);
		JSONObject jsonObject = new JSONObject(responseEntity.getBody());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "bearer " + jsonObject.get("access_token"));
		headers.add("x-api-key", xApiKey);
		HttpEntity<String> requestEntity = new HttpEntity<String>("parameters", headers);
		return template.exchange(url, HttpMethod.GET, requestEntity, String.class);
	}

	protected ResponseEntity<String> getRespostaJSONPaginado(String url) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> responseEntity = template.exchange(getURLAutenticacao(), HttpMethod.POST, null,
				String.class);
		JSONObject jsonObject = new JSONObject(responseEntity.getBody());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "bearer " + jsonObject.get("access_token"));
		headers.add("x-api-key", xApiKey);
		headers.add("paginado", "true");
		HttpEntity<String> requestEntity = new HttpEntity<String>("parameters", headers);
		return template.exchange(url, HttpMethod.GET, requestEntity, String.class);
	}

	private String getURLAutenticacao() {
		return urlBaseAutenticacao + "authz-server/oauth/token?client_id=" + clientId + "&client_secret=" + clientSecret
				+ "&grant_type=client_credentials";
	}

	protected String getUrlBaseSistemas() {
		return urlBaseSistemas;
	}

	protected String getVersao() {
		return versao;
	}

}

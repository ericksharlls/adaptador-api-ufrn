package dev.modulo.adaptador.apiufrn.dto;

import java.io.Serializable;

public class DepartamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idUnidade, codigoUnidade;
	private String nomeUnidade, sigla, hierarquiaOrganizacional;
	private Integer idUnidadeGestora, idNivelOrganizacional, idClassificacaoUnidade;
	private Boolean unidadePatrimonial;
	private String email, telefones;
	private Integer idMunicipio, idAmbienteOrganizacional, idTipoUnidadeOrganizacional, idAreaAtuacaoUnidade;

	public DepartamentoDTO() {
		
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Integer getCodigoUnidade() {
		return codigoUnidade;
	}

	public void setCodigoUnidade(Integer codigoUnidade) {
		this.codigoUnidade = codigoUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getHierarquiaOrganizacional() {
		return hierarquiaOrganizacional;
	}

	public void setHierarquiaOrganizacional(String hierarquiaOrganizacional) {
		this.hierarquiaOrganizacional = hierarquiaOrganizacional;
	}

	public Integer getIdUnidadeGestora() {
		return idUnidadeGestora;
	}

	public void setIdUnidadeGestora(Integer idUnidadeGestora) {
		this.idUnidadeGestora = idUnidadeGestora;
	}

	public Integer getIdNivelOrganizacional() {
		return idNivelOrganizacional;
	}

	public void setIdNivelOrganizacional(Integer idNivelOrganizacional) {
		this.idNivelOrganizacional = idNivelOrganizacional;
	}

	public Integer getIdClassificacaoUnidade() {
		return idClassificacaoUnidade;
	}

	public void setIdClassificacaoUnidade(Integer idClassificacaoUnidade) {
		this.idClassificacaoUnidade = idClassificacaoUnidade;
	}

	public Boolean getUnidadePatrimonial() {
		return unidadePatrimonial;
	}

	public void setUnidadePatrimonial(Boolean unidadePatrimonial) {
		this.unidadePatrimonial = unidadePatrimonial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdAmbienteOrganizacional() {
		return idAmbienteOrganizacional;
	}

	public void setIdAmbienteOrganizacional(Integer idAmbienteOrganizacional) {
		this.idAmbienteOrganizacional = idAmbienteOrganizacional;
	}

	public Integer getIdTipoUnidadeOrganizacional() {
		return idTipoUnidadeOrganizacional;
	}

	public void setIdTipoUnidadeOrganizacional(Integer idTipoUnidadeOrganizacional) {
		this.idTipoUnidadeOrganizacional = idTipoUnidadeOrganizacional;
	}

	public Integer getIdAreaAtuacaoUnidade() {
		return idAreaAtuacaoUnidade;
	}

	public void setIdAreaAtuacaoUnidade(Integer idAreaAtuacaoUnidade) {
		this.idAreaAtuacaoUnidade = idAreaAtuacaoUnidade;
	}

}

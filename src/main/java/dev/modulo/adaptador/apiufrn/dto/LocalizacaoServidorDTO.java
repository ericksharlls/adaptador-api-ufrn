package dev.modulo.adaptador.apiufrn.dto;

import java.io.Serializable;

public class LocalizacaoServidorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idServidor;
	private Integer idLocalizacaoServidor;
	private Integer idUnidadeLocalizacao;
	private String unidadeLocalizacao;
	
	public LocalizacaoServidorDTO() {
		
	}

	public Integer getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(Integer idServidor) {
		this.idServidor = idServidor;
	}

	public Integer getIdLocalizacaoServidor() {
		return idLocalizacaoServidor;
	}

	public void setIdLocalizacaoServidor(Integer idLocalizacaoServidor) {
		this.idLocalizacaoServidor = idLocalizacaoServidor;
	}

	public Integer getIdUnidadeLocalizacao() {
		return idUnidadeLocalizacao;
	}

	public void setIdUnidadeLocalizacao(Integer idUnidadeLocalizacao) {
		this.idUnidadeLocalizacao = idUnidadeLocalizacao;
	}

	public String getUnidadeLocalizacao() {
		return unidadeLocalizacao;
	}

	public void setUnidadeLocalizacao(String unidadeLocalizacao) {
		this.unidadeLocalizacao = unidadeLocalizacao;
	}

}

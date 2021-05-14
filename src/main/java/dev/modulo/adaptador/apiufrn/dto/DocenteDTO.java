package dev.modulo.adaptador.apiufrn.dto;

import java.io.Serializable;

public class DocenteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Long idTurma, idServidor, chDedicada;

	public DocenteDTO() {
		super();
	}

	/**
	 * Recupera o valor do atributo nome
	 * 
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atribui o novo valor de nome
	 * 
	 * @param nome nome que ser� atribu�do
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Recupera o valor do atributo idTurma
	 * 
	 * @return o idTurma
	 */
	public Long getIdTurma() {
		return idTurma;
	}

	/**
	 * Atribui o novo valor de idTurma
	 * 
	 * @param idTurma idTurma que ser� atribu�do
	 */
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	/**
	 * Recupera o valor do atributo idServidor
	 * 
	 * @return o idServidor
	 */
	public Long getIdServidor() {
		return idServidor;
	}

	/**
	 * Atribui o novo valor de idServidor
	 * 
	 * @param idServidor idServidor que ser� atribu�do
	 */
	public void setIdServidor(Long idServidor) {
		this.idServidor = idServidor;
	}

	/**
	 * Recupera o valor do atributo chDedicada
	 * 
	 * @return o chDedicada
	 */
	public Long getChDedicada() {
		return chDedicada;
	}

	/**
	 * Atribui o novo valor de chDedicada
	 * 
	 * @param chDedicada chDedicada que ser� atribu�do
	 */
	public void setChDedicada(Long chDedicada) {
		this.chDedicada = chDedicada;
	}

}

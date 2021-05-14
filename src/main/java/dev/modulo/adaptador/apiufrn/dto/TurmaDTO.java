package dev.modulo.adaptador.apiufrn.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TurmaDTO implements Serializable {

	   private static final long serialVersionUID = 1L;

	   private Integer id, idTurmaAgrupadora, idUnidade, cargaHoraria, capacidadeAluno, totalSolicitacoes, qtdMatriculados;
	   private String unidade, codigoComponente, nomeComponente, codigo, descricaoHorario, local, situacao, nivel;
	   private Boolean agrupadora;
	   private List<DocenteDTO> docentesList;

	   public TurmaDTO() {
	      super();
	      this.docentesList = new ArrayList<DocenteDTO>(0);
	   }

	   /**
	    * Recupera o valor do atributo id
	    * @return o id
	    */
	   public Integer getId() {
	      return id;
	   }

	   /**
	    * Atribui o novo valor de id
	    * @param id id que será atribuído
	    */
	   public void setId(Integer id) {
	      this.id = id;
	   }

	   /**
	    * Recupera o valor do atributo idTurmaAgrupadora
	    * @return o idTurmaAgrupadora
	    */
	   public Integer getIdTurmaAgrupadora() {
	      return idTurmaAgrupadora;
	   }

	   /**
	    * Atribui o novo valor de idTurmaAgrupadora
	    * @param idTurmaAgrupadora idTurmaAgrupadora que será atribuído
	    */
	   public void setIdTurmaAgrupadora(Integer idTurmaAgrupadora) {
	      this.idTurmaAgrupadora = idTurmaAgrupadora;
	   }

	   /**
	    * Recupera o valor do atributo idUnidade
	    * @return o idUnidade
	    */
	   public Integer getIdUnidade() {
	      return idUnidade;
	   }

	   /**
	    * Atribui o novo valor de idUnidade
	    * @param idUnidade idUnidade que será atribuído
	    */
	   public void setIdUnidade(Integer idUnidade) {
	      this.idUnidade = idUnidade;
	   }

	   /**
	    * Recupera o valor do atributo cargaHoraria
	    * @return o cargaHoraria
	    */
	   public Integer getCargaHoraria() {
	      return cargaHoraria;
	   }

	   /**
	    * Atribui o novo valor de cargaHoraria
	    * @param cargaHoraria cargaHoraria que será atribuído
	    */
	   public void setCargaHoraria(Integer cargaHoraria) {
	      this.cargaHoraria = cargaHoraria;
	   }

	   /**
	    * Recupera o valor do atributo capacidadeAluno
	    * @return o capacidadeAluno
	    */
	   public Integer getCapacidadeAluno() {
	      return capacidadeAluno;
	   }

	   /**
	    * Atribui o novo valor de capacidadeAluno
	    * @param capacidadeAluno capacidadeAluno que será atribuído
	    */
	   public void setCapacidadeAluno(Integer capacidadeAluno) {
	      this.capacidadeAluno = capacidadeAluno;
	   }

	   /**
	    * Recupera o valor do atributo totalSolicitacoes
	    * @return o totalSolicitacoes
	    */
	   public Integer getTotalSolicitacoes() {
	      return totalSolicitacoes;
	   }

	   /**
	    * Atribui o novo valor de totalSolicitacoes
	    * @param totalSolicitacoes totalSolicitacoes que será atribuído
	    */
	   public void setTotalSolicitacoes(Integer totalSolicitacoes) {
	      this.totalSolicitacoes = totalSolicitacoes;
	   }

	   /**
	    * Recupera o valor do atributo qtdMatriculados
	    * @return o qtdMatriculados
	    */
	   public Integer getQtdMatriculados() {
	      return qtdMatriculados;
	   }

	   /**
	    * Atribui o novo valor de qtdMatriculados
	    * @param qtdMatriculados qtdMatriculados que será atribuído
	    */
	   public void setQtdMatriculados(Integer qtdMatriculados) {
	      this.qtdMatriculados = qtdMatriculados;
	   }

	   /**
	    * Recupera o valor do atributo unidade
	    * @return o unidade
	    */
	   public String getUnidade() {
	      return unidade;
	   }

	   /**
	    * Atribui o novo valor de unidade
	    * @param unidade unidade que será atribuído
	    */
	   public void setUnidade(String unidade) {
	      this.unidade = unidade;
	   }

	   /**
	    * Recupera o valor do atributo codigoComponente
	    * @return o codigoComponente
	    */
	   public String getCodigoComponente() {
	      return codigoComponente;
	   }

	   /**
	    * Atribui o novo valor de codigoComponente
	    * @param codigoComponente codigoComponente que será atribuído
	    */
	   public void setCodigoComponente(String codigoComponente) {
	      this.codigoComponente = codigoComponente;
	   }

	   /**
	    * Recupera o valor do atributo nomeComponente
	    * @return o nomeComponente
	    */
	   public String getNomeComponente() {
	      return nomeComponente;
	   }

	   /**
	    * Atribui o novo valor de nomeComponente
	    * @param nomeComponente nomeComponente que será atribuído
	    */
	   public void setNomeComponente(String nomeComponente) {
	      this.nomeComponente = nomeComponente;
	   }

	   /**
	    * Recupera o valor do atributo codigo
	    * @return o codigo
	    */
	   public String getCodigo() {
	      return codigo;
	   }

	   /**
	    * Atribui o novo valor de codigo
	    * @param codigo codigo que será atribuído
	    */
	   public void setCodigo(String codigo) {
	      this.codigo = codigo;
	   }

	   /**
	    * Recupera o valor do atributo descricaoHorario
	    * @return o descricaoHorario
	    */
	   public String getDescricaoHorario() {
	      return descricaoHorario;
	   }

	   /**
	    * Atribui o novo valor de descricaoHorario
	    * @param descricaoHorario descricaoHorario que será atribuído
	    */
	   public void setDescricaoHorario(String descricaoHorario) {
	      this.descricaoHorario = descricaoHorario;
	   }

	   /**
	    * Recupera o valor do atributo local
	    * @return o local
	    */
	   public String getLocal() {
	      return local;
	   }

	   /**
	    * Atribui o novo valor de local
	    * @param local local que será atribuído
	    */
	   public void setLocal(String local) {
	      this.local = local;
	   }

	   /**
	    * Recupera o valor do atributo situacao
	    * @return o situacao
	    */
	   public String getSituacao() {
	      return situacao;
	   }

	   /**
	    * Atribui o novo valor de situacao
	    * @param situacao situacao que será atribuído
	    */
	   public void setSituacao(String situacao) {
	      this.situacao = situacao;
	   }

	   /**
	    * Recupera o valor do atributo nivel
	    * @return o nivel
	    */
	   public String getNivel() {
	      return nivel;
	   }

	   /**
	    * Atribui o novo valor de nivel
	    * @param nivel nivel que será atribuído
	    */
	   public void setNivel(String nivel) {
	      this.nivel = nivel;
	   }

	   /**
	    * Recupera o valor do atributo agrupadora
	    * @return o agrupadora
	    */
	   public Boolean getAgrupadora() {
	      return agrupadora;
	   }

	   /**
	    * Atribui o novo valor de agrupadora
	    * @param agrupadora agrupadora que será atribuído
	    */
	   public void setAgrupadora(Boolean agrupadora) {
	      this.agrupadora = agrupadora;
	   }

	   /**
	    * Recupera o valor do atributo docentesList
	    * @return o docentesList
	    */
	   public List<DocenteDTO> getDocentesList() {
	      return docentesList;
	   }

	   /**
	    * Atribui o novo valor de docentesList
	    * @param docentesList docentesList que será atribuído
	    */
	   public void setDocentesList(List<DocenteDTO> docentesList) {
	      this.docentesList = docentesList;
	   }

	}

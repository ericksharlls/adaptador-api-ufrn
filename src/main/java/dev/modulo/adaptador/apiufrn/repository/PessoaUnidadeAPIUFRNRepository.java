package dev.modulo.adaptador.apiufrn.repository;

import java.util.List;

import dev.modulo.adaptador.apiufrn.dto.DepartamentoDTO;
import dev.modulo.adaptador.apiufrn.dto.LocalizacaoServidorDTO;
import dev.modulo.adaptador.apiufrn.dto.ServidorDTO;

public interface PessoaUnidadeAPIUFRNRepository {
	
	/**
	 * <p>
	 * Retorna todos os departamentos vinculados a um Centro.
	 * </p>
	 *
	 *
	 * @param doisPrimeirosDigitosCodigoCentro uma string com os dois primeiros dígitos do código do Centro
	 * @param clientId                         string correspondente ao clientId
	 * @param clientSecret                     string correspondente ao clientSecret
	 *                                         
	 * @return lista de departamentos vinculados a um Centro.
	 * @since 1.0-SNAPSHOT
	 */
	public List<DepartamentoDTO> retornaTodosDepartamentosPorDoisPrimeirosDigitosCodigoCentro(String doisPrimeirosDigitosCodigoCentro, String clientId, String clientSecret);

	/**
	 * <p>
	 * Retorna todos os servidores ativos de uma unidade.
	 * </p>
	 *
	 *
	 * @param idUnidade    identificador da unidade
	 * @param clientId     string correspondente ao clientId
	 * @param clientSecret string correspondente ao clientSecret
	 * @return lista de servidores ativos de uma unidade.
	 * @since 1.0-SNAPSHOT
	 */
	public List<ServidorDTO> retornaServidoresAtivosPorIdUnidadeLotacao(Integer idUnidadeLotacao, String clientId, String clientSecret);
	
	public List<LocalizacaoServidorDTO> retornaLocalizacoesDeServidoresAtivosPorIdUnidadeLocalizacao(Integer idUnidadeLocalizacao, String clientId, String clientSecret);
	
	public List<LocalizacaoServidorDTO> retornaLocalizacoesDeServidoresAtivosPorIdServidor(Integer idServidor, String clientId, String clientSecret);
	
	public ServidorDTO retornaServidorPorId(Integer idServidor, String clientId, String clientSecret);

}

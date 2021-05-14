package dev.modulo.adaptador.apiufrn.repository;

import java.util.List;

import dev.modulo.adaptador.apiufrn.dto.DepartamentoDTO;
import dev.modulo.adaptador.apiufrn.dto.TurmaDTO;

public interface TurmaAPIUFRNRepository {

	public List<TurmaDTO> retornaTurmasPorIdCentroAnoPeriodo(Integer idCentro, Integer ano, Integer periodo, String clientId, String clientSecret);

	public List<TurmaDTO> retornaTurmasPorIdDepartamentoAnoPeriodo(Integer idDepartamento, Integer ano, Integer periodo, String clientId, String clientSecret);

	/**
	 * <p>
	 * Retorna os departamentos acadêmicos vinculados a um Centro.
	 * </p>
	 *
	 *
	 * @param idCentro 						   inteiro correspondente ao id do Centro
	 * @param clientId                         string correspondente ao clientId
	 * @param clientSecret                     string correspondente ao clientSecret
	 *                                         
	 * @return lista dos departamentos acadêmicos vinculados a um Centro.
	 * @since 1.0-SNAPSHOT
	 * 
	 */
	public List<DepartamentoDTO> retornaUnidadesAcademicasPorIdCentro(Integer idCentro, String clientId, String clientSecret);

}

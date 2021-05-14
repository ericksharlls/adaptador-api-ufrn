package dev.modulo.adaptador.apiufrn.conversor;

import dev.modulo.adaptador.apiufrn.dto.LocalizacaoServidorDTO;
import dev.modulo.adaptador.apiufrn.dto.ServidorDTO;

import org.json.JSONObject;

public class Conversor {
	
	public static LocalizacaoServidorDTO convertJSONObjectToLocalizacaoServidorDTO(JSONObject jSONObject) {
		LocalizacaoServidorDTO localizacaoServidorDTO = new LocalizacaoServidorDTO();
		localizacaoServidorDTO.setIdServidor((Integer) jSONObject.get("id-servidor"));
		localizacaoServidorDTO.setIdLocalizacaoServidor((Integer) jSONObject.get("id-localizacao-servidor"));
		localizacaoServidorDTO.setIdUnidadeLocalizacao((Integer) jSONObject.get("id-unidade-localizacao"));
		localizacaoServidorDTO.setUnidadeLocalizacao((String) jSONObject.get("unidade-localizacao"));
		return localizacaoServidorDTO;
	}
	
	public static ServidorDTO convertJSONObjectToServidorDTO(JSONObject jSONObject) {
		ServidorDTO servidorDTO = new ServidorDTO();
		servidorDTO.setCpf((String) jSONObject.get("cpf"));
		servidorDTO.setNome((String) jSONObject.get("nome"));
		servidorDTO.setSexo((String) jSONObject.get("sexo"));
		servidorDTO.setIdServidor((Integer) jSONObject.get("id-servidor"));
		Integer siapeInteiro = (Integer) jSONObject.get("siape");
		servidorDTO.setSiape(String.valueOf(siapeInteiro));
		servidorDTO.setIdCategoria((Integer) jSONObject.get("id-categoria"));
		servidorDTO.setEmail((String) jSONObject.get("email"));
		return servidorDTO;
	}

}

package com.mobdev.apimobdevrm.converter;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import com.mobdev.apimobdevrm.model.Origin;
import com.mobdev.apimobdevrm.model.ResponseRm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class RmConverter {

	private static Logger logger = LogManager.getLogger(RmConverter.class);

	// with the result of the ricky morty api I build the final response
	public ResponseRm responseRmConverter(String character, String location) {

		ResponseRm response = new ResponseRm();
		try {
			JSONObject jsonCharacter = new JSONObject(character);
			JSONObject jsonLocation = new JSONObject(location);
			response.setId(jsonCharacter.getInt("id"));
			response.setName(jsonCharacter.getString("name"));
			response.setStatus(jsonCharacter.getString("status"));
			response.setSpecies(jsonCharacter.getString("species"));
			response.setType(jsonCharacter.getString("type"));
			JSONArray arrJsonEpisodes = jsonCharacter.getJSONArray("episode");
			response.setEpisode_count(arrJsonEpisodes.length() - 1);
			Origin origin = new Origin();
			origin.setName(jsonLocation.getString("name"));
			origin.setUrl(jsonLocation.getString("url"));
			origin.setDimension(jsonLocation.getString("dimension"));
			JSONArray arrJsonResidentes = jsonLocation.getJSONArray("residents");
			String[] arrResidents = new String[arrJsonResidentes.length()];
			for (int i = 0; i < arrJsonResidentes.length(); i++)
				arrResidents[i] = arrJsonResidentes.getString(i);
			origin.setResidents(arrResidents);
			response.setOrigin(origin);
		} catch (Exception e) {
			logger.error("Error converting data " + e.toString());
			return null;
		}
		return response;

	}
}

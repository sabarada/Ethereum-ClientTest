package org.BlockChainService.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Convert ObjectInputData to JsonString
	 * @param gethInputVO is Object InputData
	 * @return JsonString
	 * @throws JsonProcessingException
	 */
	public static String getJsonString(Object object)
	{
		try {
			return  objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

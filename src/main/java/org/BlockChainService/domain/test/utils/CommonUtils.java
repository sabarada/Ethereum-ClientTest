package org.BlockChainService.domain.test.utils;

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
	public static String getJsonString(Object object) throws JsonProcessingException
	{
		
		String payload = objectMapper.writeValueAsString(object);
		System.out.println(payload);
		return payload;
	}
	
}

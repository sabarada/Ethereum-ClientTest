package personal.blockchain.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private CommonUtils() {}
	
	/**
	 * Convert ObjectInputData to JsonString
	 * @param gethInputVO is Object InputData
	 * @return JsonString
	 * @throws JsonProcessingException
	 */
	public static String getJsonString(Object object)
	{
		if(object == null)
		{
			throw new NullPointerException("parameter object is required but null");
		}
			
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.debug("CommonUtils", e);
		}
		
		return jsonString;
	}
	
	/**
	 * bytes array to hex string
	 * @param input
	 * @param offset
	 * @param length
	 * @param withPrefix
	 * @return
	 */
	public static String toHexString(byte[] input, int offset, int length, boolean withPrefix)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if(withPrefix) stringBuilder.append("0x");
		
		for(int i = offset; i < offset + length; i++)
		{
			stringBuilder.append(String.format("%02x", input[i] & 0xff));
		}
		
		return stringBuilder.toString();
	}
}

package org.BlockChainService.domain.dto;

import java.util.List;

import org.BlockChainService.domain.dto.type.Address;

/**
 * 
 * @author kwanghoyeom
 * This class is referred to here.
 * https://github.com/web3j/web3j
 */
public class FunctionEncoder {

	private FunctionEncoder() {}
	
	/*
	 * 
	 */
	public static String encode(Function function)
	{
		List<Object> objects = function.getInputParameters();
		String signature = buildMethodSignature(function.getName(), objects);
		return buildMethodHash(signature);
	}
	
	/*
	 * 
	 */
	private static String buildMethodSignature(String methodName, List<Object> objects)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(methodName)
					 .append("(");
		
		int count = 0;
		for(Object object : objects)
		{
			if(object instanceof Integer) stringBuilder.append("uint");
			if(object instanceof String) stringBuilder.append("string");
			if(object instanceof Byte) stringBuilder.append("byte");
			if(object instanceof Address) stringBuilder.append("address");
			
			if(count++ != objects.size() - 1) stringBuilder.append(",");
		}
		 
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}
	
	/*
	 * 
	 */
	private static String buildMethodHash(String methodSignature)
	{
		byte[] input = methodSignature.getBytes();
		byte[] hash = Hash.sha3(input);
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < hash.length; i++) 
		{
            stringBuilder.append(String.format("%02x", hash[i] & 0xFF));
        }
		
		return "0x" + stringBuilder.toString().substring(0,8);
	}
}

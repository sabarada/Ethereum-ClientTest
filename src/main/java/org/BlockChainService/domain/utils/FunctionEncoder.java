package org.BlockChainService.domain.utils;

import java.util.List;

import org.BlockChainService.domain.dto.Function;
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
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> objects = function.getInputParameters();
		
		stringBuilder.append(buildMethodHash(buildMethodSignature(function.getName(), objects)))
					 .append(encodeParameters(objects));
		
		return stringBuilder.toString();
	}
	
	public static String encodeParameters(List<Object> objects)
	{
		StringBuilder dynamicData = new StringBuilder();
		
		for(Object object : objects)
		{
			String encodedValue = ObjectEncoder.encode(object);
			dynamicData.append(encodedValue);
		}
		
		return dynamicData.toString();
	}
	
	/*
	 * 
	 */
	public static String buildMethodSignature(String methodName, List<Object> objects)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(methodName)
					 .append("(");
		
		int count = 0;
		for(Object object : objects)
		{
			if(object instanceof Integer) stringBuilder.append("uint256");
			if(object instanceof String) stringBuilder.append("string");
			if(object instanceof Byte) stringBuilder.append("bytes1");
			if(object instanceof Address) stringBuilder.append("address");
			
			if(count++ != objects.size() - 1) stringBuilder.append(",");
		}
		 
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}
	
	/*
	 * 
	 */
	public static String buildMethodHash(String methodSignature)
	{
		byte[] input = methodSignature.getBytes();
		byte[] hash = Hash.sha3(input);
		
		return "0x" + CommonUtils.toHexString(hash, 0, hash.length, true).substring(2,10);
	}
}

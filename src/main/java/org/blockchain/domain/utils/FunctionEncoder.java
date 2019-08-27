package org.blockchain.domain.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.blockchain.domain.dto.Function;
import org.blockchain.domain.dto.type.Address;

/**
 * 
 * @author kwanghoyeom
 * This class is referred to here.
 * https://github.com/web3j/web3j
 */
public final class FunctionEncoder {

	private FunctionEncoder() {}
	
	/*
	 * 
	 */
	public static String encode(Function function)
	{
		final StringBuilder stringBuilder = new StringBuilder();
		final List<Object> objects = function.getInputParameters();
		
		stringBuilder.append(buildMethodHash(buildMethodSignature(function.getName(), objects)));
		
		if(objects != null) {
			stringBuilder.append(encodeParameters(objects));
		}
		
		return stringBuilder.toString();
	}
	
	public static String encodeParameters(List<Object> objects)
	{
		final StringBuilder dynamicData = new StringBuilder();
		
		for(Object object : objects)
		{
			dynamicData.append(ObjectEncoder.encode(object));
		}
		
		return dynamicData.toString();
	}
	
	/*
	 * 
	 */
	public static String buildMethodSignature(String methodName, List<Object> objects) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(methodName)
					 .append('(');

		if(objects == null)
		{
			stringBuilder.append(')');
			return stringBuilder.toString();
		}

		stringBuilder.append(objects.stream()
									.map(FunctionEncoder::convertObject)
									.collect(Collectors.joining(",")));
		stringBuilder.append(')');

		return stringBuilder.toString();
	}

	private static String convertObject(Object object)
	{
		if(object instanceof Integer) {
			return "uint256";
		}
		else if(object instanceof String) {
			return "string";
		}
		else if(object instanceof Byte) {
			return "byte1";
		}
		else if(object instanceof Address) {
			return "address";
		}
		else
		{
			return "null";
		}
	}

	/*
	 * 
	 */
	public static String buildMethodHash(String methodSignature)
	{
		final byte[] input = methodSignature.getBytes();
		final byte[] hash = Hash.sha3(input);
		
		return "0x" + CommonUtils.toHexString(hash, 0, hash.length, true).substring(2,10);
	}
}

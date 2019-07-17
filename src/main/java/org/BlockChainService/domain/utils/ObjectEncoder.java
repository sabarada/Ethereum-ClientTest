package org.BlockChainService.domain.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.BlockChainService.domain.com.dto.type.Address;

public class ObjectEncoder {
	
	private ObjectEncoder() {}
	
	private final static int MAX_BYTE_VALUE = 32;
	private final static int MAX_BIT_VALUE = 256;
	
	public static String encode(Object parameter)
	{
		if(parameter instanceof Address) return encodeAddress((Address)parameter);
		else if(parameter instanceof Integer) return encodeInteger((Integer)parameter);
		else if(parameter instanceof String) return encodeString((String)parameter);
		else if(parameter instanceof Byte) {
			byte[] bytes = new byte[1];
			bytes[0] = (Byte)parameter;
			return encodeBytes(bytes);
		}
		else
		{
			throw new UnsupportedOperationException("Type cannot be encoded : " + parameter.getClass());
		}
	}
	
	static String encodeAddress(Address Address)
	{
		return null;
	}
	
	static String encodeInteger(Integer integer)
	{
		byte[] rawValue = toByteArray(integer);
		byte paddingValue = getPaddingValue(integer);
		byte[] paddedRawValue = new byte[MAX_BYTE_VALUE];
		if(paddingValue != 0)
		{
			for(int i = 0 ; i < paddedRawValue.length; i++)
			{
				paddedRawValue[i] = paddingValue;
			}
		}
		
		System.arraycopy(rawValue, 0, paddedRawValue, MAX_BYTE_VALUE - rawValue.length, rawValue.length);
		return CommonUtils.toHexString(paddedRawValue, 0, paddedRawValue.length, false);
	}
	
	public static String encodeString(String string)
	{
		byte[] utfEncoded = string.getBytes(StandardCharsets.UTF_8);
		String encodedLength = encode(Integer.valueOf(utfEncoded.length));
		String encodedValue = encodeBytes(utfEncoded);
	
		return encodedLength + encodedValue;
	}
	
	static String encodeBytes(byte[] bytes)
	{
		int length = bytes.length;
		int mod = length % MAX_BYTE_VALUE;
		
		byte[] dest;
		
		if(mod != 0)
		{
			int padding = MAX_BYTE_VALUE - mod;
			dest = new byte[padding + length];
			System.arraycopy(bytes, 0, dest, padding, length);
		}
		else
		{
			dest = bytes;
		}
		
		return CommonUtils.toHexString(dest, 0, dest.length, false);
	}
	
	private static byte getPaddingValue(Integer integer)
	{
		if(integer.intValue() < 0) return (byte)0xff;
		else return 0;
	}
	
	private static byte[] toByteArray(Integer integer)
	{
		BigInteger value =BigInteger.valueOf(integer.intValue());
		return value.toByteArray();
	}
}

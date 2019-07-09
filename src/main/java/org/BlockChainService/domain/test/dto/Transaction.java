package org.BlockChainService.domain.test.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
	public static final BigInteger DEFAULT_GAS = BigInteger.valueOf(9000);
	
	private String from;
    private String to;
    private String gas;
    private String gasPrice;
    private String value;
    private String data;
    private BigInteger nonce;  // nonce field is not present on eth_call/eth_estimateGas
	
    
    
    public Transaction(String from, String to, String gas, String gasPrice, String value, String data,
			BigInteger nonce) {
		this.from = from;
		this.to = to;
		this.gas = gas;
		this.gasPrice = gasPrice;
		this.value = value;
		this.data = data;
		this.nonce = nonce;
	}
    
    public Transaction(String from, String to, String gas, String gasPrice, String value) {
		this(from, to, gas, gasPrice, value, null, null);
	}
    
    public Transaction(String from, String to, String value)
    {
    	this(from, to, null, null, value);
    }
    
    
    
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BigInteger getNonce() {
		return nonce;
	}
	public void setNonce(BigInteger nonce) {
		this.nonce = nonce;
	}
    
    
}

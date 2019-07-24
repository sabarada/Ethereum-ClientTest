package org.BlockChainService.domain.com.dto;

import java.math.BigInteger;

import org.BlockChainService.domain.com.dto.type.Parameter;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Parameter{
	public static final BigInteger DEFAULT_GAS = BigInteger.valueOf(9000);
	
	private String from;
    private String to;
    private String gas;
    private String gasPrice;
    private String value;
    private String data;
    private BigInteger nonce;  // nonce field is not present on eth_call/eth_estimateGas
	
    
    public Transaction(Builder builder) {
		this.from = builder.from;
		this.to = builder.to;
		this.gas = builder.gas;
		this.gasPrice = builder.gasPrice;
		this.value = builder.value;
		this.data = builder.data;
		this.nonce = builder.nonce;
	}
 
    
    /*
     * use builder pattern
     * Transaction parameters are optional
     */
    public static class Builder
    {
    	private String from = null;
        private String to = null;
        private String gas = null;
        private String gasPrice = null;
        private String value = null;
        private String data = null;
        private BigInteger nonce = null;
        
        public Builder addFrom(String from)
        {
        	this.from = from;
        	return this;
        }
        
        public Builder addTo(String to)
        {
        	this.to = to;
        	return this;
        }
        
        public Builder addGas(String gas)
        {
        	this.gas = gas;
        	return this;
        }
        
        public Builder addGasPrice(String gasPrice)
        {
        	this.gasPrice = gasPrice;
        	return this;
        }
        
        public Builder addvalue(String value)
        {
        	this.value = value;
        	return this;
        }
        
        public Builder addData(String data)
        {
        	this.data = data;
        	return this;
        }
        
        public Transaction build()
        {
        	return new Transaction(this);
        }
    }	
}

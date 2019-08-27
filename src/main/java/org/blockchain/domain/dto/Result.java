package org.blockchain.domain.dto;

import lombok.Data;

@Data
public class Result
{
	private String removed;
	private String blockNumber;
	private String blockHash;
	private String transactionHash;
	private String transactionIndex;
	private String address;
	private String data;
	private String[] topics;
	private String logIndex;
//	private String 
	
}
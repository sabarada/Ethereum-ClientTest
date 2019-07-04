package org.BlockChainService.domain.test.dto;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

@Repository
public class GethResultVO_Event {

    private String jsonrpc;
    private String id;
    private Results[] result;

    public String getJsonrpc() {
        return jsonrpc;
    }
    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Results[] getResult() {
		return result;
	}
	public void setResult(Results[] result) {
		this.result = result;
	}
	
	@Override
    public String toString() {
        return "\nGethResultVO{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result=" + Arrays.toString(result) + '\'' +
                '}';
    }
}

class Results
{
	private String blockNumber;
	private String blockHash;
	private String transactionHash;
	private String transactionIndex;
	private String address;
	private String data;
	private String[] topics;
	private String logIndex;
	
	public String getLogIndex() {
		return logIndex;
	}
	public void setLogIndex(String logIndex) {
		this.logIndex = logIndex;
	}
	public String getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}
	public String getBlockHash() {
		return blockHash;
	}
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	public String getTransactionHash() {
		return transactionHash;
	}
	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}
	public String getTransactionIndex() {
		return transactionIndex;
	}
	public void setTransactionIndex(String transactionIndex) {
		this.transactionIndex = transactionIndex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	@Override
	public String toString() {
		return " [blockNumber=" + blockNumber + ", blockHash=" + blockHash + ", transactionHash="
				+ transactionHash + ", transactionIndex=" + transactionIndex + ", address=" + address + ", data=" + data
				+ ", topics=" + Arrays.toString(topics) + ", logIndex=" + logIndex + "]";
	}
	
	
	
}

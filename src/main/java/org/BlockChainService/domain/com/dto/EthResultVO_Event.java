package org.BlockChainService.domain.com.dto;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

@Repository
public class EthResultVO_Event implements EthResultInterface{

    private String jsonrpc;
    private String id;
    private Result[] result;

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
    public Result[] getResult() {
		return result;
	}
	public void setResult(Result[] result) {
		this.result = result;
	}
	
	@Override
    public String toString() {
        return "\nGethResultVO{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result=" + Arrays.toString(result)+ '\'' +
                '}';
    }
}
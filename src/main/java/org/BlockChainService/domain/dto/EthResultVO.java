package org.BlockChainService.domain.dto;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class EthResultVO implements EthResultInterface{

    private String jsonrpc;
    private String id;
    private String result;
    
    @Override
    public String toString() {
        return "\nGethResultVO{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

package personal.blockchain.domain.dto;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class EthResultVOEvent implements EthResultInterface{

    private String jsonrpc;
    private String id;
    private Result[] result;

	@Override
    public String toString() {
        return "\nGethResultVO{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result=" + Arrays.toString(result)+ '\'' +
                '}';
    }
}
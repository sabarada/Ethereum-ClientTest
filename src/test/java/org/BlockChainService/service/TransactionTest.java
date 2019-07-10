package org.BlockChainService.service;

import static org.web3j.utils.Convert.toWei;

import org.BlockChainService.domain.dto.GethInputVO;
import org.BlockChainService.domain.dto.GethResultVO;
import org.BlockChainService.domain.dto.Transaction;
import org.junit.Test;
import org.web3j.utils.Convert;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TransactionTest extends GethClientTest{

	//{"jsonrpc": "2.0", "method" : "eth_sendTransaction", "params"  : [{ "from" : from, "to" : to, "gas" : gas, "gasPrice" : gasPrice, "value" : value}], "id" : 1|
    @Test
    public void sendTransaction() throws JsonProcessingException {
        String from = "0x7d324dbc8fc704881d302da3b264e2243007bdba";
        String to = "0x93b80734c3d1263c787d4869240f4912909c4807";
        String gas = "0x" + toWei("90000", Convert.Unit.WEI).toBigInteger().toString(16);
        String gasPrice = "0x" + toWei("20000000000", Convert.Unit.WEI).toBigInteger().toString(16);
        String value = "0x" +  toWei("1", Convert.Unit.ETHER).toBigInteger().toString(16);
        
        Transaction transaction = new Transaction.Builder()
        							.addFrom(from)
        							.addTo(to)
        							.addGas(gas)
        							.addGasPrice(gasPrice)
        							.addvalue(value)
        							.build();
        
        GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_sendTransaction", java.util.Arrays.asList(transaction), GethResultVO.class);
        
        send(gethInputVO, TransactionTest.class);
    	
    }
    
  //{"jsonrpc": "2.0", "method" : "eth_call", "params" : [{"from" : account, "to" : to, "data" : data}, "latest"],"id": 1}
    @Test
    public void ethCall() throws JsonProcessingException
    {
    	String from = "0xF76c9B7012c0A3870801eaAddB93B6352c8893DB";
    	String to = "0x6A637B1081322573749e89c1d5Af4e953162338f";
    	String data = "0x893d20e8";
    	
    	Transaction transaction = new Transaction.Builder()
									.addFrom(from)
									.addTo(to)
									.addData(data)
									.build();
    	
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
    }
}

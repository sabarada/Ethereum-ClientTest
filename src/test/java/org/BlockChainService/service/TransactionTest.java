package org.BlockChainService.service;

import static org.web3j.utils.Convert.toWei;

import org.BlockChainService.domain.test.dto.GethInputVO;
import org.BlockChainService.domain.test.dto.GethResultVO;
import org.BlockChainService.domain.test.dto.Transaction;
import org.junit.Test;
import org.web3j.utils.Convert;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TransactionTest extends GethClientTest{

	//{"jsonrpc": "2.0", "method" : "eth_sendTransaction", "params"  : [{ "from" : from, "to" : to, "gas" : gas, "gasPrice" : gasPrice, "value" : value}], "id" : 1|
    @Test
    public void sendTransaction() throws JsonProcessingException {
    	System.out.println("-----------------------sendTransaction------------------");
        String from = "0x7d324dbc8fc704881d302da3b264e2243007bdba";
        String to = "0x93b80734c3d1263c787d4869240f4912909c4807";
        String gas = "0x" + toWei("90000", Convert.Unit.WEI).toBigInteger().toString(16);
        String gasPrice = "0x" + toWei("20000000000", Convert.Unit.WEI).toBigInteger().toString(16);
        String value = "0x" +  toWei("1", Convert.Unit.ETHER).toBigInteger().toString(16);
        
        Transaction transaction = new Transaction(from, to, gas, gasPrice, value);
        
        GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_sendTransaction", java.util.Arrays.asList(transaction), GethResultVO.class);
        
        send(gethInputVO, TransactionTest.class);
    	
    }
    
  //{"jsonrpc": "2.0", "method" : "eth_call", "params" : [{"from" : account, "to" : to, "data" : data}, "latest"],"id": 1}
    @Test
    public void ethCall() throws JsonProcessingException
    {
    	String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";
    	String to = "0xd01768a74cff578cc54647fb0e465b7084562a12";
    	String data = "0x8da9b772"; //
    	
    	Transaction transaction = new Transaction(account, to, data);
    	
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_getBalance", java.util.Arrays.asList(transaction), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
    }
}

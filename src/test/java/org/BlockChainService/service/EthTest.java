package org.BlockChainService.service;

import java.io.IOException;
import java.util.Collections;

import org.BlockChainService.domain.test.dto.GethInputVO;
import org.BlockChainService.domain.test.dto.GethResultVO;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class EthTest extends GethClientTest{
	
	//{"jsonrpc":"2.0","method":"web3_clientVersion","params":[],"id":67}
    @Test
    public void testGetClientVersion() throws IOException {        
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("web3_clientVersion", Collections.<String>emptyList(), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
    }

    //{"jsonrpc": "2.0", "method":"eth_accounts", "params":[], "id":1 }
    @Test
    public void getAccount() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_accounts", Collections.<String>emptyList(), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
    }

	//{ "jsonrpc": "2.0", "method" : "personal_unlockAccount", "params"  : [ account, passwd ], "id" : 67 }
    @Test
    public void unlockAccount() throws JsonProcessingException {
    	String account = "";
    	String passwd = "";
        
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("personal_unlockAccount", java.util.Arrays.asList(account, passwd), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
    }

  //{"jsonrpc": "2.0", "method" : "miner_start", "params"  : [], "id" : 74}
    @Test
    public void minerStart() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("miner_start", Collections.<String>emptyList(), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
    }
    
    //{"jsonrpc": "2.0", "method" : "miner_stop", "params"  : [], "id" : 74}
    @Test
    public void minerStop() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("miner_stop", Collections.<String>emptyList(), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);   
    }


    //{"jsonrpc": "2.0","method" : "eth_getBalance","params"  : [account,"latest"],"id": 1 }
    @Test
    public void getBalance() throws JsonProcessingException {
    	String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";
    	
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_getBalance", java.util.Arrays.asList(account, "latest"), GethResultVO.class);
    	send(gethInputVO, GethResultVO.class);
        
    	//System.out.println(account + "'s balance is " + Convert.fromWei(String.valueOf(Hex2Decimal.hex2Decimal(result.getResult().substring(2))), Convert.Unit.ETHER));
    }
}

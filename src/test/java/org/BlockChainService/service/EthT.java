package org.BlockChainService.service;

import java.io.IOException;
import java.util.Collections;

import org.BlockChainService.domain.com.dto.EthInputVO;
import org.BlockChainService.domain.com.dto.EthResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthT extends GethClientT{
	
	//{"jsonrpc":"2.0","method":"web3_clientVersion","params":[],"id":67}
    @Test
    public void testGetClientVersion() throws IOException {        
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("web3_clientVersion", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }

    //{"jsonrpc": "2.0", "method":"eth_accounts", "params":[], "id":1 }
    @Test
    public void getAccount() throws JsonProcessingException {
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_accounts", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }

	//{ "jsonrpc": "2.0", "method" : "personal_unlockAccount", "params"  : [ account, passwd ], "id" : 67 }
    @Test
    public void unlockAccount() throws JsonProcessingException {
    	String account = "";
    	String passwd = "";
        
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("personal_unlockAccount", java.util.Arrays.asList(account, passwd), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }

  //{"jsonrpc": "2.0", "method" : "miner_start", "params"  : [], "id" : 74}
    @Test
    public void minerStart() throws JsonProcessingException {
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("miner_start", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }
    
    //{"jsonrpc": "2.0", "method" : "miner_stop", "params"  : [], "id" : 74}
    @Test
    public void minerStop() throws JsonProcessingException {
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("miner_stop", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);   
    }


    //{"jsonrpc": "2.0","method" : "eth_getBalance","params"  : [account,"latest"],"id": 1 }
    @Test
    public void getBalance() throws JsonProcessingException {
    	String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";
    	
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_getBalance", java.util.Arrays.asList(account, "latest"), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
        
    	//System.out.println(account + "'s balance is " + Convert.fromWei(String.valueOf(Hex2Decimal.hex2Decimal(result.getResult().substring(2))), Convert.Unit.ETHER));
    }
}

package org.blockchain.service;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Collections;

import org.blockchain.domain.dto.EthInputVO;
import org.blockchain.domain.dto.EthResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthT extends GethClientT{

    @Test
    public void testGetClientVersion() throws IOException {        
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("web3_clientVersion", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }
    
    @Test
    public void GetClientVersion() throws IOException
    {
    	String jsonInput = "{\"jsonrpc\":\"2.0\",\"method\":\"web3_clientVersion\",\"params\":[],\"id\":67}";
    	EthResultVO result = httpService.callEthFunction(jsonInput, EthResultVO.class);
    	System.out.println(result);
    }

    @Test
    public void getAccount() throws JsonProcessingException {
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_accounts", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }

    @Test
    public void unlockAccount() throws JsonProcessingException {
    	String account = "";
    	String passwd = "";
        
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("personal_unlockAccount", java.util.Arrays.asList(account, passwd), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }

    @Test
    public void minerStart() throws JsonProcessingException {
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("miner_start", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }

    @Test
    public void minerStop() throws JsonProcessingException {
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("miner_stop", Collections.<String>emptyList(), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);   
    }

    @Test
    public void getBalance() throws JsonProcessingException {
    	String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";
    	
    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_getBalance", java.util.Arrays.asList(account, "latest"), EthResultVO.class);
    	send(gethInputVO, EthResultVO.class);
    }
}

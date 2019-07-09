package org.BlockChainService.service;

import static org.web3j.utils.Convert.toWei;

import java.io.IOException;
import java.util.Collections;

import org.BlockChainService.domain.test.dto.Filter;
import org.BlockChainService.domain.test.dto.GethInputVO;
import org.BlockChainService.domain.test.dto.GethResultVO;
import org.BlockChainService.domain.test.dto.GethResultVO_Event;
import org.BlockChainService.domain.test.dto.Transaction;
import org.BlockChainService.domain.test.service.HttpService;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.utils.Convert;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GethwithmavenApplicationTests {

    @Autowired
    private HttpService web3jSampleService;
       
    
    //{"jsonrpc":"2.0","method":"web3_clientVersion","params":[],"id":67}
    @Test
    public void testGetClientVersion() throws IOException {        
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("web3_clientVersion", Collections.<String>emptyList(), GethResultVO.class);
    	System.out.println(web3jSampleService.callGethFunction(web3jSampleService.getJsonString(gethInputVO), GethResultVO.class));
    }

    //{"jsonrpc": "2.0", "method":"eth_accounts", "params":[], "id":1 }
    @Test
    public void getAccount() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_accounts", Collections.<String>emptyList(), GethResultVO.class);
    	System.out.println(web3jSampleService.callGethFunction(web3jSampleService.getJsonString(gethInputVO), GethResultVO.class));
    }

	//{ "jsonrpc": "2.0", "method" : "personal_unlockAccount", "params"  : [ account, passwd ], "id" : 67 }
    @Test
    public void unlockAccount() throws JsonProcessingException {
    	String account = "";
    	String passwd = "";
        
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("personal_unlockAccount", java.util.Arrays.asList(account, passwd), GethResultVO.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        web3jSampleService.callGethFunction(JSONInput, GethResultVO.class);
    }

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
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));
    }

    //{"jsonrpc": "2.0", "method" : "miner_start", "params"  : [], "id" : 74}
    @Test
    public void minerStart() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("miner_start", Collections.<String>emptyList(), GethResultVO.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));
    }
    
    //{"jsonrpc": "2.0", "method" : "miner_stop", "params"  : [], "id" : 74}
    @Test
    public void minerStop() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("miner_stop", Collections.<String>emptyList(), GethResultVO.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));
    }


    //{"jsonrpc": "2.0","method" : "eth_getBalance","params"  : [account,"latest"],"id": 1 }
    @Test
    public void getBalance() throws JsonProcessingException {
    	String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";
    	
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_getBalance", java.util.Arrays.asList(account, "latest"), GethResultVO.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));
        
//        System.out.println(account + "'s balance is " + Convert.fromWei(String.valueOf(Hex2Decimal.hex2Decimal(result.getResult().substring(2))), Convert.Unit.ETHER));
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
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));
    }

    //{"jsonrpc":"2.0","method":"eth_newFilter","params":["topics":topics]"id":7}
    @Test
    public void eth_newFilter() throws IOException
    {
    	String topicAddress = "";
	    Filter filter = new Filter().addSingleTopic(topicAddress);
	    GethInputVO<?, GethResultVO_Event> gethInputVO = new GethInputVO<>("eth_newFilter", java.util.Arrays.asList(filter), GethResultVO_Event.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
		System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));
    }
    
    //{"jsonrpc":"2.0","method":"eth_getFilterChanges","params":[filterID],"id":73}
    @Test
    public void eth_getFilterChanges() throws IOException
    {
    	String filterID = "";
    
    	GethInputVO<?, GethResultVO_Event> gethInputVO = new GethInputVO<>("eth_newFilter", java.util.Arrays.asList(filterID), GethResultVO_Event.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
		System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO.class));	
    }
}


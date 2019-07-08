package org.BlockChainService.service;

import static org.web3j.utils.Convert.toWei;

import java.io.IOException;
import java.util.Collections;

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
       
    
    //"{\"jsonrpc\":\"2.0\",\"method\":\"web3_clientVersion\",\"params\":[],\"id\":67}";
    @Test
    public void testGetClientVersion() throws IOException {        
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("web3_clientVersion", Collections.<String>emptyList(), GethResultVO.class);
    	System.out.println(web3jSampleService.callGethFunction(web3jSampleService.getJsonString(gethInputVO), GethResultVO.class));
    }

    //"{\"jsonrpc\": \"2.0\", \"method\":\"eth_accounts\", \"params\":[], \"id\":1 }";
    @Test
    public void getAccount() throws JsonProcessingException {
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("eth_accounts", Collections.<String>emptyList(), GethResultVO.class);
    	System.out.println(web3jSampleService.callGethFunction(web3jSampleService.getJsonString(gethInputVO), GethResultVO.class));
    }

	//  "{\n" +
	//  "    \"jsonrpc\": \"2.0\", \n" +
	//  "    \"method\" : \"personal_unlockAccount\", \n" +
	//  "    \"params\"  : [\n" +
	//  "       \"" + account + "\", \n" +
	//  "       \""+ passwd + "\", \n" +
	//  "       0\n" +
	//  "    ], \n" +
	//  "    \"id\" : 67 \n" +
	//  "} ";
    @Test
    public void unlockAccount() throws JsonProcessingException {
    	String account = "";
    	String passwd = "";
        
    	GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("personal_unlockAccount", java.util.Arrays.asList(account, passwd), GethResultVO.class);
    	String JSONInput = web3jSampleService.getJsonString(gethInputVO);
        web3jSampleService.callGethFunction(JSONInput, GethResultVO.class);
    }

	//  "{\n" +
	//  "    \"jsonrpc\": \"2.0\", \n" +
	//  "    \"method\" : \"eth_sendTransaction\", \n" +
	//  "    \"params\"  : [{ \n" +
	//  "       \"from\"       : \"" + from + "\", \n" +
	//  "       \"to\"         : \"" + to + "\", \n" +
	//  "       \"gas\"        : \"" + gas + "\", \n" +
	//  "       \"gasPrice\"   : \"" + gasPrice + "\", \n" +
	//  "       \"value\"      : \"" + value + "\" \n" +
	//  "    }], \n" +
	//  "    \"id\" : 1 \n" +
	//  "} ";
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

    @Test
    public void minerStart() {
    	String JSONInput = "{\n" +
                "    \"jsonrpc\": \"2.0\", \n" +
                "    \"method\" : \"miner_start\", \n" +
                "    \"params\"  : [], \n" +
                "    \"id\" : 74 \n" +
                "} ";

        System.out.println(JSONInput);
        web3jSampleService.callGethFunction(JSONInput, GethResultVO.class);
    }

    @Test
    public void minerStop() {
    	String JSONInput = "{\n" +
                "    \"jsonrpc\": \"2.0\", \n" +
                "    \"method\" : \"miner_stop\", \n" +
                "    \"params\"  : [], \n" +
                "    \"id\" : 74 \n" +
                "} ";

        System.out.println(JSONInput);
        web3jSampleService.callGethFunction(JSONInput, GethResultVO.class);
    }


    @Test
    public void getBalance() {
    	System.out.println("-----------------------getBalance------------------");
        String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";

        String JSONInput = "{\n" +
                "    \"jsonrpc\": \"2.0\", \n" +
                "    \"method\" : \"eth_getBalance\", \n" +
                "    \"params\"  : [\n" +
                "       \"" + account + "\", \n" +
                "       \"latest\" \n" +
                "    ], \n" +
                "    \"id\" : 1 \n" +
                "} ";

        System.out.println(JSONInput);
        GethResultVO result = web3jSampleService.callGethFunction(JSONInput, GethResultVO.class);
        System.out.println(result);
//        System.out.println(result.getResult());
//        System.out.println(Hex2Decimal.hex2Decimal("1f2bba5d853536ca00"));
//        System.out.println(Hex2Decimal.hex2Decimal("0x1f2bba5d853536ca00"));
//        System.out.println(account + "'s balance is " + Convert.fromWei(String.valueOf(Hex2Decimal.hex2Decimal(result.getResult().substring(2))), Convert.Unit.ETHER));
    }
    
    @Test
    public void ethCall()
    {
    	String account = "0x8bb666d92c17f586793a8d0cfbb1ad2f31470002";
    	String to = "0xd01768a74cff578cc54647fb0e465b7084562a12";
    	String data = "0x8da9b772";
    	
    	String JSONInput = "{\n" +
    			" 	\"jsonrpc\": \"2.0\", \n" + 
    			" 	\"method\":\"eth_call\", \n" +
    			" 	\"params\" : [{\n" + 
    			"   	\"from\" : \""+ account +"\", \n" +
    			"   	\"to\" : \"" + to +"\", \n"  +
    			"   	\"data\" : \"" + data + "\" \n}," +
    			"   \"latest\" "+
    			" 		], \n" +
    			" 	\"id\": 1 \n" +
    			"} ";
    	
    	System.out.println(JSONInput);
        web3jSampleService.callGethFunction(JSONInput, GethResultVO.class);
    }

    @Test
    public void eth_newFilter() throws IOException
    {
	    String topics = "0xd5d55c8a68912e9a110618df8d5e2e83b8d83211c57a8ddd1203df92885dc881";
	    
    	String JsonInput = "{\n" +
		" 	\"jsonrpc\": \"2.0\", \n" + 
		" 	\"method\":\"eth_newFilter\", \n" +
		" 	\"params\" : [{\n" + 
		"   	\"topics\" : \"" + topics + "\" \n}," +
		"   \"latest\" "+
		" 		], \n" +
		" 	\"id\": 73 \n" +
		"} ";
    	
    	System.out.println(JsonInput);
    	System.out.println(web3jSampleService.callGethFunction(JsonInput, GethResultVO.class));
    }
    
    @Test
    public void eth_getFilterChanges() throws IOException
    {
    	String filterID = "";
    	
    	String JSONInput = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getFilterChanges\",\"params\":[\"" + filterID +  "\"],\"id\":73}";
	
    	System.out.println(JSONInput);
    	System.out.println(web3jSampleService.callGethFunction(JSONInput, GethResultVO_Event.class));
    }
}


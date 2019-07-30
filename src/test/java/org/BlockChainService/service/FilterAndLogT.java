package org.BlockChainService.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.web3j.utils.Convert.toWei;

import java.io.IOException;
import java.util.Arrays;

import org.BlockChainService.domain.dto.EthInputVO;
import org.BlockChainService.domain.dto.EthResultVO;
import org.BlockChainService.domain.dto.EthResultVO_Event;
import org.BlockChainService.domain.dto.Filter;
import org.BlockChainService.domain.dto.Result;
import org.BlockChainService.domain.lottery.service.LotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.utils.Convert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterAndLogT extends GethClientT{
	
	@Autowired
	LotteryService lotteryService;

	// filter & Log confirm
	// transaction & receipt
	@Test
	public void filterAndLog() throws InterruptedException
	{
		// given
		String topicAddress = LotteryService.BET;
		String from  = "0x7d324dbc8fc704881d302da3b264e2243007bdba";
		String value = "0x" +  toWei("5", Convert.Unit.FINNEY).toBigInteger().toString(16);
		byte challenges = 0x01;
		
		// when [filter]
		Filter filter = new Filter().addSingleTopic(topicAddress);
    	EthInputVO<?, EthResultVO> eth_newFilterInput = new EthInputVO<>("eth_newFilter", java.util.Arrays.asList(filter), EthResultVO.class);
    	String filterID = send(eth_newFilterInput, EthResultVO.class).getResult();

    	// then
    	assertThat(filterID, is(notNullValue()));
    	System.out.println(filterID);
    
    	
    	// waiting for transcation commit in ethereum!!
    	Thread.sleep(5000);
    	
    	// when [log]
    	EthInputVO<?, EthResultVO_Event> eth_getFilterChangesInput = new EthInputVO<>("eth_getTransactionReceipt", java.util.Arrays.asList(filterID), EthResultVO_Event.class);
    	Result[] results = send(eth_getFilterChangesInput, EthResultVO_Event.class).getResult();
//    	
//    	// then ()
    	System.out.println(Arrays.toString(results));
    	assertThat(results.length, is(not(0)));
	}
	
	
	
	//{"jsonrpc":"2.0","method":"eth_newFilter","params":["topics":topics]"id":7}
    @Test
    public void eth_newFilter() throws IOException
    {
    	// given
    	String topicAddress = LotteryService.BET;
    	
    	// when
    	Filter filter = new Filter().addSingleTopic(topicAddress);
    	EthInputVO<?, EthResultVO> ethInputVO = new EthInputVO<>("eth_newFilter", java.util.Arrays.asList(filter), EthResultVO.class);
    	
    	// then
    	assertThat(send(ethInputVO, EthResultVO.class).getResult(), is(notNullValue()));
    }
    
    //{"jsonrpc":"2.0","method":"eth_getFilterChanges","params":[filterID],"id":73}
    @Test
    public void eth_getFilterChanges() throws IOException
    {
    	// given
    	String filterID = "0xee1af041eb8145475b5155e2d4f6da48";
    
    	
    	// when
    	EthInputVO<?, EthResultVO_Event> ethInputVO = new EthInputVO<>("eth_getFilterChanges", java.util.Arrays.asList(filterID), EthResultVO_Event.class);
    	
    	// then
    	assertThat(send(ethInputVO, EthResultVO_Event.class).getResult(), is(notNullValue()));
    }
	
}

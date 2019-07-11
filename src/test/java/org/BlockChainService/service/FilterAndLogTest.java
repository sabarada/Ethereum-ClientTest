package org.BlockChainService.service;

import java.io.IOException;

import org.BlockChainService.domain.dto.Filter;
import org.BlockChainService.domain.dto.EthInputVO;
import org.BlockChainService.domain.dto.EthResultVO_Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterAndLogTest extends GethClientTest{

	//{"jsonrpc":"2.0","method":"eth_newFilter","params":["topics":topics]"id":7}
    @Test
    public void eth_newFilter() throws IOException
    {
    	String topicAddress = "";
	    Filter filter = new Filter().addSingleTopic(topicAddress);
	    EthInputVO<?, EthResultVO_Event> gethInputVO = new EthInputVO<>("eth_newFilter", java.util.Arrays.asList(filter), EthResultVO_Event.class);
    	send(gethInputVO, EthResultVO_Event.class);
    }
    
    //{"jsonrpc":"2.0","method":"eth_getFilterChanges","params":[filterID],"id":73}
    @Test
    public void eth_getFilterChanges() throws IOException
    {
    	String filterID = "";
    
    	EthInputVO<?, EthResultVO_Event> gethInputVO = new EthInputVO<>("eth_newFilter", java.util.Arrays.asList(filterID), EthResultVO_Event.class);
    	// is Working
    }
	
}

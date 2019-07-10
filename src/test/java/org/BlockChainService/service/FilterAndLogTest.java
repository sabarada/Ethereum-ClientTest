package org.BlockChainService.service;

import java.io.IOException;

import org.BlockChainService.domain.dto.Filter;
import org.BlockChainService.domain.dto.GethInputVO;
import org.BlockChainService.domain.dto.GethResultVO_Event;
import org.junit.Test;

public class FilterAndLogTest extends GethClientTest{

	//{"jsonrpc":"2.0","method":"eth_newFilter","params":["topics":topics]"id":7}
    @Test
    public void eth_newFilter() throws IOException
    {
    	String topicAddress = "";
	    Filter filter = new Filter().addSingleTopic(topicAddress);
	    GethInputVO<?, GethResultVO_Event> gethInputVO = new GethInputVO<>("eth_newFilter", java.util.Arrays.asList(filter), GethResultVO_Event.class);
    	send(gethInputVO, GethResultVO_Event.class);
    }
    
    //{"jsonrpc":"2.0","method":"eth_getFilterChanges","params":[filterID],"id":73}
    @Test
    public void eth_getFilterChanges() throws IOException
    {
    	String filterID = "";
    
    	GethInputVO<?, GethResultVO_Event> gethInputVO = new GethInputVO<>("eth_newFilter", java.util.Arrays.asList(filterID), GethResultVO_Event.class);
    	// is Working
    }
	
}

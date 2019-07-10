package org.BlockChainService.service;

import org.BlockChainService.domain.test.dto.GethInputVO;
import org.BlockChainService.domain.test.service.HttpService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class GethClientTest {

    @Autowired
    protected HttpService web3jSampleService;
    
    @Autowired
    protected ObjectMapper objectMapper = new ObjectMapper();
    
    
    public <T> T send(GethInputVO<?, ?> input, Class<T> type)
    {
    	String JSONInput = null;
    	
		try {
			JSONInput = objectMapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return (T)(web3jSampleService.callGethFunction(JSONInput, type));
    }
}


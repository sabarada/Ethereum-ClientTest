package org.BlockChainService.service;

import org.BlockChainService.domain.dto.GethInputVO;
import org.BlockChainService.domain.service.HttpService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class GethClientTest {

    protected HttpService web3jSampleService;
    
    protected ObjectMapper objectMapper;
    
    @Before
    public void setup()
    {
    	web3jSampleService = new HttpService();
    	objectMapper = new ObjectMapper();
    }
    
    
    public <T> T send(GethInputVO<?, ?> input, Class<T> type)
    {
    	String JSONInput = null;
    	
		try {
			JSONInput = objectMapper.writeValueAsString(input);
			System.out.println(JSONInput);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return (T)(web3jSampleService.callGethFunction(JSONInput, type));
    }
}


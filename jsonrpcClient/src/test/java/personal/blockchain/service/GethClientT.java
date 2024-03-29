package personal.blockchain.service;

import personal.blockchain.domain.dto.EthInputVO;
import personal.blockchain.domain.service.HttpService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class GethClientT {

    protected HttpService httpService;
    
    protected ObjectMapper objectMapper;
    
    @Before
    public void setup()
    {
    	httpService = new HttpService();
    	objectMapper = new ObjectMapper();
    }
    
    
    public <T> T send(EthInputVO<?, ?> input, Class<T> type)
    {
    	String JSONInput = null;
    	
		try {
			JSONInput = objectMapper.writeValueAsString(input);
			System.out.println(JSONInput);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return httpService.callEthFunction(JSONInput, type);
    }
}


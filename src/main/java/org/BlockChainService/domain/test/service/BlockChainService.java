package org.BlockChainService.domain.test.service;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author kwanghoyeom
 * The JSON-RPC client event Service.
 */
@Service
public class BlockChainService {

	
	private static final String LOCAL = "http://127.0.0.1:8545";
	
	/**
	 * service to communicate with ethereum
	 * @param <T>
	 * @param JSONInput is data, send to ethereum (json format)
	 * @param classes decide result format.
	 * @return json data transferred from ethereum
	 */
	@SuppressWarnings("unchecked")
	public <T> T callGethFunction(String JSONInput, Class<T> classes) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    @SuppressWarnings("rawtypes")
	HttpEntity param= new HttpEntity(JSONInput, headers);
    
    RestTemplate restTemplate = new RestTemplate();
    return (T) restTemplate.postForObject(LOCAL, param, classes);
    }
}

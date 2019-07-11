package org.BlockChainService.domain.service;

import org.BlockChainService.domain.dto.EthInputVO;
import org.BlockChainService.domain.dto.EthResultInterface;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.web3j.protocol.ObjectMapperFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author kwanghoyeom
 * The JSON-RPC client event Service.
 */
@Service
public class HttpService {

	
	private static final String LOCAL = "http://127.0.0.1:8545";
	
	/**
	 * service to communicate with ethereum
	 * @param <T>
	 * @param JSONInput is data, send to ethereum (json format)
	 * @param classes decide result format.
	 * @return json data transferred from ethereum
	 */	
	public <T> T callGethFunction(String JSONInput, Class<T> classes) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
	HttpEntity<String> param= new HttpEntity<String>(JSONInput, headers);
    
    RestTemplate restTemplate = new RestTemplate();
    return (T) restTemplate.postForObject(LOCAL, param, classes);
    }
}

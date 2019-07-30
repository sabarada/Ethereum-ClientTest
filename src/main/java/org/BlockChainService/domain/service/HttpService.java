package org.BlockChainService.domain.service;

import org.BlockChainService.domain.dto.EthInputVO;
import org.BlockChainService.domain.dto.EthResultInterface;
import org.BlockChainService.domain.dto.EthResultVO;
import org.BlockChainService.domain.dto.EthResultVO_Event;
import org.BlockChainService.domain.dto.Result;
import org.BlockChainService.domain.dto.Transaction;
import org.BlockChainService.domain.dto.type.Method;
import org.BlockChainService.domain.dto.type.Parameter;
import org.BlockChainService.domain.utils.CommonUtils;
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
	
	public String etherCallReturnString(Parameter transaction, Method method)
	{
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>(method.getMethod(), java.util.Arrays.asList(transaction), EthResultVO.class);
		System.out.println(CommonUtils.getJsonString(gethInputVO));
		return this.callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
	}
	
	public Result[] etherCallReturnResultArray(Parameter transaction, Method method)
	{
		EthInputVO<?, EthResultVO_Event> gethInputVO = new EthInputVO<>(method.getMethod(), java.util.Arrays.asList(transaction), EthResultVO_Event.class);
		System.out.println(CommonUtils.getJsonString(gethInputVO));
		return this.callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO_Event.class).getResult();
	}
	
	public Result[] etherCallReturnResultArray(String[] transaction, Method method)
	{
		EthInputVO<?, EthResultVO_Event> gethInputVO = new EthInputVO<>(method.getMethod(), java.util.Arrays.asList(transaction), EthResultVO_Event.class);
		System.out.println(CommonUtils.getJsonString(gethInputVO));
		return this.callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO_Event.class).getResult();
	}
	
	/**
	 * service to communicate with ethereum
	 * @param <T>
	 * @param JSONInput is data, send to ethereum (json format)
	 * @param classes decide result format.
	 * @return json data transferred from ethereum
	 */	
	public <T> T callEthFunction(String JSONInput, Class<T> classes) 
	{
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
	HttpEntity<String> param= new HttpEntity<String>(JSONInput, headers);
    
    RestTemplate restTemplate = new RestTemplate();
    return (T) restTemplate.postForObject(LOCAL, param, classes);
    }
}

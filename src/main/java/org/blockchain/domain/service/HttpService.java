package org.blockchain.domain.service;

import java.util.Arrays;

import org.blockchain.domain.dto.EthInputVO;
import org.blockchain.domain.dto.EthResultVO;
import org.blockchain.domain.dto.EthResultVOEvent;
import org.blockchain.domain.dto.Result;
import org.blockchain.domain.dto.type.Method;
import org.blockchain.domain.dto.type.Parameter;
import org.blockchain.domain.utils.CommonUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author kwanghoyeom
 * The JSON-RPC client event Service.
 */
@Slf4j
@Service
public class HttpService {

	private static final String LOCAL = "http://127.0.0.1:8545";
	
	public String etherCallReturnString(Parameter transaction, Method method)
	{
		final EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>(method.getMethod(), Arrays.asList(transaction), EthResultVO.class);
		log.debug(CommonUtils.getJsonString(gethInputVO));
		return callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
	}
	
	public Result[] etherCallReturnResultArray(Parameter transaction, Method method)
	{
		final EthInputVO<?, EthResultVOEvent> gethInputVO = new EthInputVO<>(method.getMethod(), Arrays.asList(transaction), EthResultVOEvent.class);
		log.debug(CommonUtils.getJsonString(gethInputVO));
		return callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVOEvent.class).getResult();
	}
	
	public Result[] etherCallReturnResultArray(String[] transaction, Method method)
	{
		final EthInputVO<?, EthResultVOEvent> gethInputVO = new EthInputVO<>(method.getMethod(), Arrays.asList(transaction), EthResultVOEvent.class);
		log.debug(CommonUtils.getJsonString(gethInputVO));
		return callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVOEvent.class).getResult();
	}
	
	/**
	 * service to communicate with ethereum
	 * @param <T> is return type CLass
	 * @param jsonInput is data, send to ethereum (json format)
	 * @param clazz decide result format.
	 * @return json data transferred from ethereum
	 */	
	public <T> T callEthFunction(String jsonInput, Class<T> clazz)
	{
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

	final HttpEntity<String> param= new HttpEntity<>(jsonInput, headers);
    final RestTemplate restTemplate = new RestTemplate();

    return restTemplate.postForObject(LOCAL, param, clazz);
    }
}

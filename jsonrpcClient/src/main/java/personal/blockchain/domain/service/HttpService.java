package personal.blockchain.domain.service;

import java.util.Arrays;

import personal.blockchain.domain.dto.EthInputVO;
import personal.blockchain.domain.dto.EthResultVO;
import personal.blockchain.domain.dto.EthResultVOEvent;
import personal.blockchain.domain.dto.Result;
import personal.blockchain.domain.dto.type.Method;
import personal.blockchain.domain.dto.type.Parameter;
import personal.blockchain.domain.utils.CommonUtils;
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
		log.info(CommonUtils.getJsonString(gethInputVO));
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

	/*
	'{"jsonrpc" : "2.0", "method" : "eth_getFilterChanges", "params" :["0x2"],"id" : 74}'
	 */
// 0x80b5b80c0000000000000000000000000000000000000000000000000000000000000005
	/*
	{
	"jsonrpc":"2.0",
	"method":"eth_sendTransaction",
	"params":[
		{
			"from":"0xF76c9B7012c0A3870801eaAddB93B6352c8893DB",
			"to":"0xdb8fbe7f1e56eF59cFC4a6f9a358403E610126Bd",
			"data":"0x403c9fa8"
		}],
		"id":76
}
	 */
	/**
	 * service to communicate with ethereum
	 * @param <T> is return type CLass
	 * @param jsonInput is data, send to ethereum (json format)
	 * @param clazz decide result format.
	 * @return json data transferred from ethereum
	 */	
	public <T> T callEthFunction(String jsonInput, Class<T> clazz)
	{
		System.out.println("jsonInput : " + jsonInput);

		final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

	final HttpEntity<String> param= new HttpEntity<>(jsonInput, headers);
    final RestTemplate restTemplate = new RestTemplate();

    return restTemplate.postForObject(LOCAL, param, clazz);
    }
}

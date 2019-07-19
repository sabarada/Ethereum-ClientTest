package org.BlockChainService.domain.service;

import java.util.Collections;

import org.BlockChainService.domain.com.dto.EthInputVO;
import org.BlockChainService.domain.com.dto.EthResultVO;
import org.BlockChainService.domain.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EthereumService extends HttpService{

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	HttpService httpService;
	
	/**
	 * get go-ethereum version
	 */
	public String getVersion()
	{
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("web3_clientVersion", Collections.<String>emptyList(), EthResultVO.class);
		return httpService.callEthFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
	}
	
}

package org.BlockChainService.domain.service;

import java.util.Collections;

import org.BlockChainService.domain.dto.GethInputVO;
import org.BlockChainService.domain.dto.GethResultVO;
import org.BlockChainService.domain.utils.CommonUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EthereumService extends HttpService{

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * get go-ethereum version
	 */
	public String getVersion()
	{
		GethInputVO<?, GethResultVO> gethInputVO = new GethInputVO<>("web3_clientVersion", Collections.<String>emptyList(), GethResultVO.class);
		return callGethFunction(CommonUtils.getJsonString(gethInputVO), GethResultVO.class).getResult();
	}
	
}

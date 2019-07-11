package org.BlockChainService.domain.lottery.service;

import org.BlockChainService.domain.dto.EthInputVO;
import org.BlockChainService.domain.dto.EthResultVO;
import org.BlockChainService.domain.dto.Transaction;
import org.BlockChainService.domain.service.HttpService;
import org.BlockChainService.domain.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {

	private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
	private String contractAddress = "0x6A637B1081322573749e89c1d5Af4e953162338f";
	
	@Autowired
	private HttpService httpService;
	
	public String getOwner()
	{
		String functionHash = "0x893d20e8";
		
		Transaction transaction = new Transaction.Builder()
				.addTo(contractAddress)
				.addData(functionHash)
				.build();
		
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);
		return httpService.callGethFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult().substring(26);
	}
	
	public String bet()
	{
		return null;
	}

	public String distribute()
	{
		return null;
	}

	public String isMatch()
	{
		return null;
	}


	public String getBlockStatus()
	{
		return null;
	}

	// 0x79141f80 
	// getBetInfo(uint256)
	// 0xNumber;
	public String getBetInfo(int index)
	{
		String functionHash = "0x893d20e8";
		
		
		Transaction transaction = new Transaction.Builder()
				.addTo(contractAddress)
				.addData(functionHash)
				.build();
		
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);
		return httpService.callGethFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult().substring(26);
	}

}

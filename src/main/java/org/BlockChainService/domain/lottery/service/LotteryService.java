package org.BlockChainService.domain.lottery.service;

import static org.web3j.utils.Convert.toWei;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.BlockChainService.domain.dto.EthInputVO;
import org.BlockChainService.domain.dto.EthResultVO;
import org.BlockChainService.domain.dto.Function;
import org.BlockChainService.domain.dto.Transaction;
import org.BlockChainService.domain.service.HttpService;
import org.BlockChainService.domain.utils.CommonUtils;
import org.BlockChainService.domain.utils.FunctionEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.utils.Convert;

@Service
public class LotteryService {

	private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
	private String contractAddress = "0xe8aCCB4253bF6b4eCb81340082e16111c31d0954";
	
	@Autowired
	private HttpService httpService;
	
	/**
	 * get the Contract owner address
	 * @return
	 */
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
	

	/**
	 * bet(byte challenges) is contract function
	 * @param challenges check the + 3 block hash 
	 * @param from is the account Value, use this contract
	 * @return
	 */
	public String bet(byte challenges, String from, String value)
	{
		List<Object> list = new ArrayList<>();
		list.add(challenges);
		
		Function function = new Function("bet", list);
		String functionHash = FunctionEncoder.encode(function);
	
		Transaction transaction = new Transaction.Builder()
				.addTo(contractAddress)
				.addFrom(from)
				.addvalue(value)
				.addData(functionHash)
				.build();
		
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);
		return httpService.callGethFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
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
//		List<Object> list = new ArrayList<>();
//		list.add(challenges);
//		
//		Function function = new Function("bet", list);
//		String functionHash = FunctionEncoder.encode(function);
		
		// need refactoring 
//		byte[] bytearray = new byte[32];
//		bytearray[31] = challenges;
//		String parameterHash = CommonUtils.toHexString(bytearray, 0, bytearray.length, false);
		
//		String functionHash = "0x57720d71";
//		String parameterHash = "";
//		
//		Transaction transaction = new Transaction.Builder()
//				.addTo(contractAddress)
//				.addData(functionHash + parameterHash)
//				.build();
//		
//		System.out.println(transaction.toString());
//		
//		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);
//		return httpService.callGethFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
		
		return null;
	}

	/**
	 * 
	 * @param index is number you wanted know
	 * @return
	 */
	public String getBetInfo(int index)
	{
		List<Object> list = new ArrayList<>();
		list.add(index);
		
		Function function = new Function("getBetInfo", list);
		String functionHash = FunctionEncoder.encode(function);
		
		Transaction transaction = new Transaction.Builder()
				.addTo(contractAddress)
				.addData(functionHash)
				.build();
		
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);	
		return httpService.callGethFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
	}
}

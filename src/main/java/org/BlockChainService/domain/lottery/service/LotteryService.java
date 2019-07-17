package org.BlockChainService.domain.lottery.service;

import java.util.ArrayList;
import java.util.List;

import org.BlockChainService.domain.com.dto.EthInputVO;
import org.BlockChainService.domain.com.dto.EthResultVO;
import org.BlockChainService.domain.com.dto.Function;
import org.BlockChainService.domain.com.dto.Transaction;
import org.BlockChainService.domain.service.HttpService;
import org.BlockChainService.domain.utils.CommonUtils;
import org.BlockChainService.domain.utils.FunctionEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {

	@Value("${ethereum.contract.address}")
	private String contractAddress;
	
	@Autowired
	private HttpService httpService;
	
	/**
	 * get the Contract owner address
	 * @return
	 */
	public String getOwner()
	{
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("getOwner")))
				.build());
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
		
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("bet", list)))
				.addFrom(from)
				.addvalue(value)
				.build());
	}

	// distribute()
	/**
	 * distribute 
	 * @return
	 */
	public String distribute()
	{
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("distribute")))
				.build());
	}

	
	//betAndDistribute(byte challenges)
	/**
	 * 배팅 후 즉시 결과를 확인한다.
	 * @param challenges
	 * @param from
	 * @param value
	 * @return
	 */
	public String betAndDistribute(byte challenges, String from, String value)
	{
		List<Object> list = new ArrayList<>();
		list.add(challenges);
			
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("betAndDistribute", list)))
				.addFrom(from)
				.addvalue(value)
				.build());
	}
	
	/**
	 * 
	 * @param answer
	 * @return
	 */
	public String setAnswerForTest(byte answer)
	{
		List<Object> list = new ArrayList<>();
		list.add(answer);
		
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("setAnswerForTest", list)))
				.build());
	}
	
	// getPot()
	/**
	 * get the pot money
	 * @return 
	 */
	public String getPot()
	{
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("getPot")))
				.build());
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
		
		return etherCall(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("getBetInfo")))
				.build());
	}

	
	private String etherCall(Transaction transaction)
	{	
		EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_call", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);	
		return httpService.callGethFunction(CommonUtils.getJsonString(gethInputVO), EthResultVO.class).getResult();
	}
}

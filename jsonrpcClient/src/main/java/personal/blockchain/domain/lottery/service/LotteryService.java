package personal.blockchain.domain.lottery.service;

import java.util.ArrayList;
import java.util.List;

import personal.blockchain.domain.dto.Filter;
import personal.blockchain.domain.dto.Function;
import personal.blockchain.domain.dto.Result;
import personal.blockchain.domain.dto.Transaction;
import personal.blockchain.domain.dto.type.Method;
import personal.blockchain.domain.service.HttpService;
import personal.blockchain.domain.utils.FunctionEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {

	@Value("${ethereum.contract.address}")
	private String contractAddress;
	
	@Autowired
	private HttpService httpService;

	public static final String BET = "0x100791de9f40bf2d56ffa6dc5597d2fd0b2703ea70bc7548cd74c04f5d215ab7";
	public static final String WIN = "0x8219079e2d6c1192fb0ff7f78e6faaf5528ad6687e69749205d87bd4b156912b";
	public static final String LOSE = "0x857de42f5f3a8f5de6cedde05ecd2843e3d33c293b4a75322a56e345f8bc75b4";
	public static final String DRAW = "0x72ec2e949e4fad9380f9d5db3e2ed0e71cf22c51d8d66424508bdc761a3f4b0e";
	public static final String REFUND = "0x59c0185881271a0f53d43e6ab9310091408f9e0ff9ae2512613de800f26b8de4";
	
	/**
	 * get the Contract owner address
	 * @return return owner address value
	 */
	public String getOwner()
	{
		return httpService.etherCallReturnString(
                new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("getOwner")))
				.build(), Method.ETH_CALL);
	}
	

	/**
	 * bet(byte challenges) is contract function
	 * @param challenges check the + 3 block hash 
	 * @param from is the account Value, use this contract
	 * @return betting on a block
	 */
	public String bet(byte challenges, String from, String value)
	{
		final List<Object> list = new ArrayList<>();
		list.add(challenges);
		
		return httpService.etherCallReturnString(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("bet", list)))
				.addFrom(from)
				.addTo(contractAddress)
				.addvalue(value)
				.addGas("0x12888")
				.build(), Method.ETH_SENDTRANSACTION);
	}

	// distribute()
	/**
	 * distribute 
	 * @return check the lottery
	 */
	public String distribute()
	{
		return httpService.etherCallReturnString(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("distribute")))
				.build(), Method.ETH_CALL);
	}

	
	//betAndDistribute(byte challenges)
	/**
	 * betting and distribte Immediately
	 * @param challenges your choose byte
	 * @param from  better address
	 * @param value betting money
	 * @return check the lottery
	 */
	public String betAndDistribute(byte challenges, String from, String value)
	{
		final List<Object> list = new ArrayList<>();
		list.add(challenges);
			
		return httpService.etherCallReturnString(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("betAndDistribute", list)))
				.addFrom(from)
				.addvalue(value)
				.build(), Method.ETH_CALL);
	}
	
	/**
	 * 
	 * @param answer set the answer for check the process
	 * @return result values
	 */
	public Result[] setAnswerForTest(byte answer)
	{
		final List<Object> list = new ArrayList<>();
		list.add(answer);
		
		return httpService.etherCallReturnResultArray(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("setAnswerForTest", list)))
				.build(), Method.ETH_SENDTRANSACTION);
	}
	
	// getPot()
	/**
	 * get the pot money
	 * @return get the pot Stored
	 */
	public String getPot()
	{
		return httpService.etherCallReturnString(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("getPot")))
				.build(), Method.ETH_CALL);
	}

	/**
	 * 
	 * @param index is number you wanted know
	 * @return get bet info currently
	 */
	public String getBetInfo(int index)
	{
		final List<Object> list = new ArrayList<>();
		list.add(index);
		
		return httpService.etherCallReturnString(
				new Transaction.Builder()
				.addTo(contractAddress)
				.addData(FunctionEncoder.encode(new Function("getBetInfo")))
				.build(), Method.ETH_CALL);
	}

	/**
	 * create new filter
	 * @return topic address
	 */
	public String newFilter()
	{	
	    return httpService.etherCallReturnString(
	    		new Filter().addSingleTopic(BET)
//	    					.addSingleTopic(WIN)
//	    					.addSingleTopic(DRAW)
//	    					.addSingleTopic(LOSE)
//	    					.addSingleTopic(REFUND)
	    					,
	    					Method.ETH_NEWFILTER);
	    
	}
	
	
	public Result[] getLog(String[] filterId)
	{
		return httpService.etherCallReturnResultArray(
						filterId,
						Method.ETH_GETFILTERCHANGES);
	}
}

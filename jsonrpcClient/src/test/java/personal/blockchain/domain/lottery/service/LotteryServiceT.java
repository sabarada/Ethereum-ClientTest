package personal.blockchain.domain.lottery.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.web3j.utils.Convert.toWei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import personal.blockchain.domain.dto.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.utils.Convert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryServiceT {

	@Autowired
	private LotteryService lotteryService;
	
	@Test
	public void getOwnerT()
	{
		// given
		String owner = "0xf76c9b7012c0a3870801eaaddb93b6352c8893db";
		
		// then
		assertThat(lotteryService.getOwner(), is(owner));
	}
	
	@Test
	public void betT()
	{
		// given
		String from  = "0x7d324dbc8fc704881d302da3b264e2243007bdba";
		String value = "0x" +  toWei("5", Convert.Unit.FINNEY).toBigInteger().toString(16);
		byte challenges = 0x1b;
		
		// then
		assertThat(lotteryService.bet(challenges, from, value), is(notNullValue()));		
	}

	@Test
	public void betAndDistributeT()
	{
		// given
		String from  = "0x7d324dbc8fc704881d302da3b264e2243007bdba";
		String result = "0x0000000000000000000000000000000000000000000000000000000000000001";
		String value = "0x" +  toWei("5", Convert.Unit.FINNEY).toBigInteger().toString(16);
		byte challenges = 0x1b;
		
		// then
		assertThat(lotteryService.betAndDistribute(challenges, from, value), is(result));		
		
		// given_exception
		value = "0x" +  toWei("1", Convert.Unit.FINNEY).toBigInteger().toString(16);
		
		// then_exception
		assertThat(lotteryService.betAndDistribute(challenges, from, value), is(nullValue()));
	}

	@Test
	public void distributeT()
	{
		assertThat(lotteryService.distribute(), is(nullValue()) );
	}
	
	@Test
	public void setAnswerForT()
	{
		//given
		byte callenges = 0x1b;
		
		//when & then
		assertThat(lotteryService.setAnswerForTest(callenges), is(notNullValue()) );
	}
	
	@Test
	public void getPotT()
	{
		// when & then
		assertThat(lotteryService.getPot(), is("0x0000000000000000000000000000000000000000000000000000000000000000"));
	}
	
	@Test
	public void newFilterT()
	{
		// when
		String result = lotteryService.newFilter();
		
		// then
		System.out.println(result);
		assertThat(result, is(notNullValue()));
	}
	
	@Test
	public void getLogT()
	{
		List<String> filterId = new ArrayList<>();
		filterId.add("0x564b595c733aa4700c00ca03549cef17");
		
		
		// when
		Result[] result = lotteryService.getLog(filterId.toArray(new String[0]));
		
		// then
		System.out.println(Arrays.toString(result));
		
		assertThat(result, is(notNullValue()));
	}
	
	
	@Test
	public void getBetInfoT()
	{
		// then
		String result = "0x0000000000000000000000000000000000000000000000000000000000000000"
				        + "0000000000000000000000000000000000000000000000000000000000000000"
				        + "0000000000000000000000000000000000000000000000000000000000000000";
		
		
		assertThat(lotteryService.getBetInfo(5), is(result));
	}
}

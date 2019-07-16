package org.BlockChainService.domain.lottery.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.web3j.utils.Convert.toWei;

import org.BlockChainService.domain.lottery.service.LotteryService;
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
		String owner = "0xF76c9B7012c0A3870801eaAddB93B6352c8893DB";
		
		// then
		assertThat(lotteryService.getOwner(), is(owner));
	}
	
	@Test
	public void betT()
	{
		String from  = "0xF76c9B7012c0A3870801eaAddB93B6352c8893DB";
		String result = "0x0000000000000000000000000000000000000000000000000000000000000001";
		String value = "0x" +  toWei("5", Convert.Unit.FINNEY).toBigInteger().toString(16);
		byte challenges = 0x1b;
		
		
		//correct_test
		assertThat(lotteryService.bet(challenges, from, value), is(result));		
		
		//exception_test
		value = "0x" +  toWei("1", Convert.Unit.FINNEY).toBigInteger().toString(16);
		assertThat(lotteryService.bet(challenges, from, value), is(nullValue()));
	}

	@Test
	public void distributeT()
	{

	}

	@Test
	public void isMatchT()
	{

	}

	@Test
	public void getBlockStatusT()
	{
		
	}
				
	@Test
	public void getBetInfoT()
	{
		String result = "0x000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		assertThat(lotteryService.getBetInfo(5), is(result));
	}
}

package org.BlockChainService.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.BlockChainService.domain.lottery.service.LotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryTest {

	@Autowired
	private LotteryService lotteryService;
	
	@Test
	public void encodingTest()
	{
		
	}
	
	@Test
	public void getOwner()
	{
		// given
		String owner = "0xF76c9B7012c0A3870801eaAddB93B6352c8893DB";
		
		// then
		assertEquals(owner, lotteryService.getOwner());
	}
	
	@Test
	public void bet()
	{
		// given
		
		// when
		
		// then
		
	}

	@Test
	public void distribute()
	{
		// given
		// when
		// then	
	}

	@Test
	public void isMatch()
	{
		// given
		// when
		// then
	}

	@Test
	public void getBlockStatus()
	{
		// given
		// when
		// then
	}

	@Test
	public void getBetInfo()
	{
		// given
		// when
		// then
	}
}

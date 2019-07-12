package org.BlockChainService.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.BlockChainService.domain.dto.Function;
import org.BlockChainService.domain.dto.FunctionEncoder;
import org.BlockChainService.domain.lottery.service.LotteryService;
import org.assertj.core.util.Arrays;
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
		// given
		Function function = new Function("test", java.util.Arrays.asList(55, "hello", new Byte((byte) 'a')));
		ArrayList<String> arrayList = mock(ArrayList.class);
		
		
		// when
		when(arrayList.get(0)).thenReturn("test(uint,string,byte)");
		when(arrayList.get(1)).thenReturn("0x47806171");
		
		// then
//		assertThat(FunctionEncoder.buildMethodSignature(function.getName(), function.getInputParameters()), is(arrayList.get(0)));
//		assertThat(FunctionEncoder.buildMethodHash("test(uint,string,byte)"), is(arrayList.get(1)));
		assertThat(FunctionEncoder.encode(function), is(arrayList.get(1)));
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

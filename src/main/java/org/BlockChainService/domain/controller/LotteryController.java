package org.BlockChainService.domain.controller;

import org.BlockChainService.domain.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("Lottery")
public class LotteryController {

	@Autowired
	private LotteryService lotteryService;
	
	@ApiOperation(value="version", httpMethod = "GET", notes= "get Lottery Smart-Contract Owner")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server Error"),
			@ApiResponse(code = 400, message = "invalid input"),
			@ApiResponse(code = 200, message = "OK")
	})
	@RequestMapping(value = "/owner", method=RequestMethod.GET)
	public String getVersion()
	{
		return lotteryService.getOwner();
	}
	
}

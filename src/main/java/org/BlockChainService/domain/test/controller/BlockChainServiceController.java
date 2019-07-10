package org.BlockChainService.domain.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
public class BlockChainServiceController {
	
	
	@ApiOperation(value="테스트", httpMethod = "GET", notes= "hello test")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "invalid input"),
			@ApiResponse(code = 200, message = "OK")
	})
	@RequestMapping(value = "/board", method=RequestMethod.GET)
	public String getValue()
	{
		return "Hello";
	}
}

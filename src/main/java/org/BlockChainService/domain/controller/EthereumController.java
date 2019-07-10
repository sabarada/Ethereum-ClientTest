package org.BlockChainService.domain.controller;

import org.BlockChainService.domain.service.EthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ethereum")
public class EthereumController {
	
	@Autowired
	private EthereumService ethereumService;
	
	@ApiOperation(value="version", httpMethod = "GET", notes= "Confirm Ethereum Network Version")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server Error"),
			@ApiResponse(code = 400, message = "invalid input"),
			@ApiResponse(code = 200, message = "OK")
	})
	@RequestMapping(value = "/version", method=RequestMethod.GET)
	public String getVersion()
	{
		return ethereumService.getVersion();
	}
}

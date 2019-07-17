package org.BlockChainService.domain.com.dto;

import java.util.List;

public class Function {

	private String name;
	
	private List<Object> inputParameters;

	public Function(String name)
	{
		this(name, null);
	}
	
	public Function(String name, List<Object> inputParameters)
	{
		this.name = name;
		this.inputParameters = inputParameters;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public List<Object> getInputParameters()
	{
		return this.inputParameters;
	}
}

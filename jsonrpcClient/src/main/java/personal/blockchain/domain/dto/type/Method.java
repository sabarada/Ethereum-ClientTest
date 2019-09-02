package personal.blockchain.domain.dto.type;

public enum Method{
	
	ETH_CALL("eth_call"),
	ETH_SENDTRANSACTION("eth_sendTransaction"),
	ETH_NEWFILTER("eth_newFilter"),
	ETH_GETFILTERCHANGES("eth_getFilterChanges");
	
	private String method;
	
	Method(String method)
	{
		this.method = method;
	}
	
	public String getMethod()
	{
		return this.method;
	}

}
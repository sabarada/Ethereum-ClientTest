package org.BlockChainService.domain.test.dto;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class GethInputVO <S, T extends GethResultInterface>{

	private static AtomicLong nextId = new AtomicLong(0);
	
	private String jsonrpc = "2.0";
	private String method;
	private List<S> params;
	private long id;
	
	private Class<T> responseType;
	
	public GethInputVO()
	{
		
	}

	public GethInputVO(String method, List<S> params, Class<T> responseType) {
		this.method = method;
		this.params = params;
		this.id = nextId.getAndIncrement();
		this.responseType = responseType;
	}

	public static AtomicLong getNextId() {
		return nextId;
	}

	public static void setNextId(AtomicLong nextId) {
		GethInputVO.nextId = nextId;
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<S> getParams() {
		return params;
	}

	public void setParams(List<S> params) {
		this.params = params;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Class<T> getResponseType() {
		return responseType;
	}

	public void setResponseType(Class<T> responseType) {
		this.responseType = responseType;
	}
}

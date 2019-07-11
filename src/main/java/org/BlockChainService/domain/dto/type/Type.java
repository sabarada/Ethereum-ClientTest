package org.BlockChainService.domain.dto.type;

public interface Type<T> {
	T getValue();
	String getTypeAsString();
}

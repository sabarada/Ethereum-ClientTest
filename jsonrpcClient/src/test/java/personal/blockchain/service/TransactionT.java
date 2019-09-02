package personal.blockchain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.web3j.utils.Convert.toWei;

import java.util.ArrayList;
import java.util.List;

import personal.blockchain.domain.dto.EthInputVO;
import personal.blockchain.domain.dto.EthResultVO;
import personal.blockchain.domain.dto.Function;
import personal.blockchain.domain.dto.Transaction;
import personal.blockchain.domain.utils.FunctionEncoder;
import personal.blockchain.domain.utils.ObjectEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.utils.Convert;

import com.fasterxml.jackson.core.JsonProcessingException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionT extends GethClientT{

	//{"jsonrpc": "2.0", "method" : "eth_sendTransaction", "params"  : [{ "from" : from, "to" : to, "gas" : gas, "gasPrice" : gasPrice, "value" : value}], "id" : 1|
    @Test
    public void sendTransaction() throws JsonProcessingException {
        
    	// given
    	String from = "0x7d324dbc8fc704881d302da3b264e2243007bdba";
        String to = "0x53b79CAF2Ae829BB9977127d8061b838b5374971";
        String gas = "0x" + toWei("90000", Convert.Unit.WEI).toBigInteger().toString(16);
        String gasPrice = "0x" + toWei("20000000000", Convert.Unit.WEI).toBigInteger().toString(16);
        String value = "0x" +  toWei("1", Convert.Unit.ETHER).toBigInteger().toString(16);

		System.out.println("gas : " + gas);
// 0xd869d22c561820ebaffee1c0950b0b27
        // when
        Transaction transaction = new Transaction.Builder()
        							.addFrom(from)
        							.addTo(to)
        							.addGas(gas)
        							.addGasPrice(gasPrice)
        							.addvalue(value)
									.addData("0xfc5636580000000000000000000000000000000000000000000000000000000000000005")
        							.build();
        
        EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_sendTransaction", java.util.Arrays.asList(transaction), EthResultVO.class);
		System.out.println(send(gethInputVO, EthResultVO.class));
    	
    }
    
  //{"jsonrpc": "2.0", "method" : "eth_call", "params" : [{"from" : account, "to" : to, "data" : data}, "latest"],"id": 1}
    @Test
    public void ethCall() throws JsonProcessingException
    {
    	//given
    	String from = "0xF76c9B7012c0A3870801eaAddB93B6352c8893DB";
    	String to = "0x53b79CAF2Ae829BB9977127d8061b838b5374971";
//    	String data = "0xfc563658000000000000000000000000000000000000000000000000000000000000000a";

		System.out.println(ObjectEncoder.encode(5));
		List<Object> list = new ArrayList<>();
		list.add(5);

		Function function = new Function("getNumber", list);
		String encode = FunctionEncoder.encode(function);

		System.out.println(encode);

    	Transaction transaction = new Transaction.Builder()
									.addFrom(from)
									.addTo(to)
									.addData(encode)
									.build();

    	EthInputVO<?, EthResultVO> gethInputVO = new EthInputVO<>("eth_sendTransaction", java.util.Arrays.asList(transaction, "latest"), EthResultVO.class);
		System.out.println(send(gethInputVO, EthResultVO.class));
    }
    
    @Test
    public void makeTransactionData() throws JsonProcessingException
    {
    	// given
		Function function = new Function("test", java.util.Arrays.asList(55, "hello", new Byte((byte) 'a')));
		ArrayList<String> arrayList = mock(ArrayList.class);
		
		// when
		when(arrayList.get(0)).thenReturn("test(uint256,string,byte1)");
		when(arrayList.get(1)).thenReturn("0x47806171");
		when(arrayList.get(2)).thenReturn("0000000000000000000000000000000000000000000000000000000000000005");
		when(arrayList.get(3)).thenReturn("1c8aff950685c2ed4bc3174f3472287b56d9517b9c948127319a09a7a36deac8");
		when(arrayList.get(4)).thenReturn("000000000000000000000000000000000000000000000000000000000000000a");
		
		// then
    	assertThat(FunctionEncoder.buildMethodSignature(function.getName(), function.getInputParameters()), is(arrayList.get(0)));
    	assertThat(FunctionEncoder.buildMethodHash("test(uint,string,byte)"), is(arrayList.get(1)));
		assertThat(FunctionEncoder.encodeParameters(function.getInputParameters()), is("0x" + arrayList.get(2) + arrayList.get(3) + arrayList.get(4)));

		
		// assertThat(FunctionEncoder.encode(function), is(arrayList.get(1)));
    }
}

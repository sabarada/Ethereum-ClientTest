package personal.blockchain.domain.basic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
public class BasicService{

    private EthereumService ethereumService;

    public BasicService(EthereumService ethereumService)
    {
        this.ethereumService = ethereumService;
    }

    public String getPot() throws IOException, ExecutionException, InterruptedException {

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("getPot",
                                         Collections.emptyList(),
                                         Arrays.asList(new TypeReference<Uint256>() {}));

        // 2. ethereum을 function 변수로 통해 호출
        return ethereumService.ethCall(function);
    }

    public void getNumber(int num)
    {
        // this method implement next posting
    }

    public void getOwner()
    {
        // this method implement next posting
    }

    public void setPot(int num) throws IOException, ExecutionException, InterruptedException {
        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("setPot",
                                         Arrays.asList(new Uint256(num)),
                                         Collections.emptyList());

        // 2. sendTransaction
        String txHash = ethereumService.ethSendTransaction(function);

        // 3. getReceipt
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

}

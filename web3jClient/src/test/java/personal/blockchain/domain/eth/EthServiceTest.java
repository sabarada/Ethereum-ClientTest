package personal.blockchain.domain.eth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthServiceTest {

    @Test
    public void getEthClientVersionSync() throws Exception
    {
        HttpService httpService = new HttpService();

        Web3j web3j = Web3j.build(new HttpService());
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        System.out.println(web3ClientVersion.getWeb3ClientVersion());
    }


    @Test
    public void getEthClientVersionASync() throws Exception
    {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        System.out.println(web3ClientVersion.getWeb3ClientVersion());
    }

    @Test
    public void getEthClientVersionRx() throws Exception
    {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        web3.web3ClientVersion().flowable().subscribe(x -> {
            System.out.println(x.getWeb3ClientVersion());
        });

        Thread.sleep(5000);
    }
}

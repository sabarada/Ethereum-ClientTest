package personal.blockchain.domain.basic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthereumServiceTest {

    @Autowired
    EthereumService ethereumService;

    @Test
    public void ethereumConnect() throws IOException, ExecutionException, InterruptedException {

        ethereumService.ethereumConnectAsUser();

    }
}
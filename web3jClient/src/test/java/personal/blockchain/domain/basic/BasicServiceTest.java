package personal.blockchain.domain.basic;

import static org.hamcrest.CoreMatchers.is;
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
public class BasicServiceTest {

    @Autowired
    BasicService basicService;

    @Test
    public void getPot() throws InterruptedException, ExecutionException, IOException {
        assertThat(basicService.getPot(), is(0));;
    }

    @Test
    public void setPot() throws InterruptedException, ExecutionException, IOException {
        basicService.setPot(100);
    }
}
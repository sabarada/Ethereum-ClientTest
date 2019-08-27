package org.blockchain.domain.utils;

import java.util.ArrayList;
import java.util.List;

import org.blockchain.domain.dto.type.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionEncoderTest {


    @Test
    public void buildMethodSignature() {
        List<Object> objectList = new ArrayList<>();

        objectList.add(1);
        objectList.add(2);
        objectList.add("String");
        objectList.add(new Address("0x12312jdslkjaskfhk"));

        System.out.println(FunctionEncoder.buildMethodSignature("hello", objectList));
    }

}

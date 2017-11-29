package com.application.lotto.Mytest;

import com.application.lotto.repository.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Numbertests {
    @Autowired
    DbService dbService;

    @Test
    public void testNumbers() {
        System.out.println(dbService.getNumbersOfAllDraws());
    }
}

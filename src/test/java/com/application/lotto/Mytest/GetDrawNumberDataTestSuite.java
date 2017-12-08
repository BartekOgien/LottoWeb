package com.application.lotto.Mytest;

import com.application.lotto.model.DrawNumber;
import com.application.lotto.repository.DrawNumbersDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetDrawNumberDataTestSuite {
    @Autowired
    DrawNumbersDao drawNumbersDao;

    @Test
    public void shouldGetData() {
        DrawNumber drawNumber = drawNumbersDao.findDrawNumberByDrawId(4);
        System.out.println(drawNumber.getDateOfDraw());
        System.out.println(drawNumber.getDrawId());
        System.out.println(drawNumber.getNumbers());
    }
}

package com.application.lotto;

import com.application.lotto.model.DrawNumber;
import com.application.lotto.repository.DrawNumbersDao;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LottoApplicationTests {

    @Autowired
    private DrawNumbersDao drawNumbersDao;

	@Test
	public void contextLoads() {
	    //given
        DrawNumber drawNumber = new DrawNumber();
        drawNumber.setDrawId(new Random().nextInt());
        drawNumber.setNumbers(Lists.newArrayList(1,2,3,4,5));

        //when
        drawNumbersDao.save(drawNumber);

        //then
        DrawNumber drawNumberFromDb = drawNumbersDao.findOne(drawNumber.getId());


	}

}

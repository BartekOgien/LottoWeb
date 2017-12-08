package com.application.lotto.controller;

import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawNumbersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/v1/lotto")
public class LottoController {
    private int[] howManyNumbersGet = new int[4];
    List<Integer> drawNumbers = new ArrayList<>();

    @Autowired
    DbService dbService;

    @Autowired
    DrawNumbersDao drawNumbersDao;

    @RequestMapping(method = RequestMethod.GET, value = "compare")
    public int[] compareNumbers(@RequestParam int... numbers) {
        Arrays.fill(howManyNumbersGet, 0);
        long start = 0;
        long end = 0;
        int numbersWon = 0;
        int numberOfDraws = dbService.getNumbersOfAllDraws();
        start = System.currentTimeMillis();

        for(int i = 1; i <= numberOfDraws; i++) {
            drawNumbers = drawNumbersDao.findDrawNumberByDrawId(i).getNumbers();

                numbersWon = (int)IntStream.range(0, 6)
                        .filter(n -> drawNumbers.contains(numbers[n]))
                        .count();

            if(numbersWon >=3) {
                howManyNumbersGet[numbersWon-3] ++;
            }

            numbersWon = 0;
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);
    return howManyNumbersGet;
    }
}

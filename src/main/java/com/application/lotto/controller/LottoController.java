package com.application.lotto.controller;

import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawNumbersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/lotto")
public class LottoController {
    private int[] howManyNumbersGet = {0, 0, 0, 0};

    @Autowired
    DbService dbService;

    @Autowired
    DrawNumbersDao drawNumbersDao;

    @RequestMapping(method = RequestMethod.GET, value = "compare")
    public int[] compareNumbers(@RequestParam int... numbers) {
        int numbersWon = 0;

        for(int i = 1; i <= dbService.getNumbersOfAllDraws(); i++) {

            for(int k = 0; k <= numbers.length - 1; k++) {

                if(drawNumbersDao.findDrawNumberByDrawId(i).getNumbers().contains(numbers[k])) {
                    numbersWon ++;
                }
            }

            if(numbersWon >=3) {
                howManyNumbersGet[numbersWon-3] ++;
            }

            numbersWon = 0;
        }
    return howManyNumbersGet;
    }
}

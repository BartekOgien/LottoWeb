package com.application.lotto.controller;

import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawCalculator;
import com.application.lotto.repository.DrawNumbersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;



@RestController
@RequestMapping("/v1/lotto")
public class LottoController {

    @Autowired
    DbService dbService;

    @Autowired
    DrawNumbersDao drawNumbersDao;

    @Autowired
    DrawCalculator drawCalculator;

    @RequestMapping(method = RequestMethod.GET, value = "compare")
    public List<Integer> compareNumbers(@RequestParam List<Integer> numbers) {
        return drawCalculator.compareNumbers(numbers);
    }

    @RequestMapping(method = RequestMethod.GET, value = "drawCounts")
    public int getDrawCounts() {
        return dbService.getNumbersOfAllDraws();
    }

    @RequestMapping(method = RequestMethod.GET, value = "drawCost")
    public BigDecimal getDrawCost() {
        return drawCalculator.calculateCostOfAllDraws();
    }

    @RequestMapping(method = RequestMethod.GET, value = "howManyWon")
    public int howManyWon(@RequestParam List<Integer> wonNumbers) {
        return drawCalculator.wonCash(wonNumbers);
    }
}

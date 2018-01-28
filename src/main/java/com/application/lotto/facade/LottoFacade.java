package com.application.lotto.facade;

import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LottoFacade {

    @Autowired
    private DrawCalculator drawCalculator;

    @Autowired
    private DbService dbService;

    public List<Integer> facadeCompareNumbers(List<Integer> numbers) {
        List<Integer> numbersWon = drawCalculator.compareNumbers(numbers);
        List<Integer> resultList = new ArrayList<>(numbersWon);
        resultList.add(facadeHowManyWon(numbersWon));
        return resultList;
    }

    public int getFacadeDrawCounts() {
        return dbService.getNumbersOfAllDraws();
    }

    public int getFacadeDrawCost() {
        return drawCalculator.calculateCostOfAllDraws();
    }

    public int facadeHowManyWon(List<Integer> wonNumbers) {
        return drawCalculator.wonCash(wonNumbers);
    }

}

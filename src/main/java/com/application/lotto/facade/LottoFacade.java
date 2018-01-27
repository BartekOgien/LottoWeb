package com.application.lotto.facade;

import com.application.lotto.model.YourNumber;
import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LottoFacade {

    @Autowired
    private DrawCalculator drawCalculator;

    @Autowired
    private DbService dbService;

    @Autowired
    private YourNumber yourNumber;

    public List<Integer> facadeCompareNumbers(List<Integer> numbers) {
        List<Integer> numbersWon = drawCalculator.compareNumbers(numbers);
        List<Integer> resultList = new ArrayList<>();
        Collections.copy(resultList, numbersWon);
        resultList.add(facadeHowManyWon(numbersWon));
        resultList.add(calculateProfit(numbersWon));
        return resultList;
    }

    public int getFacadeDrawCounts() {
        return dbService.getNumbersOfAllDraws();
    }

    public int calculateProfit(List<Integer> wonNumbers) {
        return drawCalculator.wonCash(wonNumbers) - getFacadeDrawCost();
    }

    public int getFacadeDrawCost() {
        return drawCalculator.calculateCostOfAllDraws();
    }

    public int facadeHowManyWon(List<Integer> wonNumbers) {
        return drawCalculator.wonCash(wonNumbers);
    }

    public String yourChooseNumbers() {
        return yourNumber.getStringYourNumbers();
    }

    public void addChoosenNumber(int number) {
        yourNumber.addYourNumber(number);
    }

    public void resetNumbers() {
        yourNumber.resetYourNumbers();
    }
}

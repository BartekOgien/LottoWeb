package com.application.lotto.facade;

import com.application.lotto.model.YourNumber;
import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class LottoFacade {

    @Autowired
    private DrawCalculator drawCalculator;

    @Autowired
    private DbService dbService;

    @Autowired
    private YourNumber yourNumber;

    public List<Integer> facadeCompareNumbers(List<Integer> numbers) {
        return drawCalculator.compareNumbers(numbers);
    }

    public int getFacadeDrawCounts() {
        return dbService.getNumbersOfAllDraws();
    }

    public BigDecimal getFacadeDrawCost() {
        return drawCalculator.calculateCostOfAllDraws();
    }

    public int facadeHowManyWon(List<Integer> wonNumbers) {
        return drawCalculator.wonCash(wonNumbers);
    }

    public Set<Integer> yourChooseNumbers() {
        return yourNumber.getYourNumbers();
    }

    public void addChoosenNumber(int number) {
        yourNumber.addYourNumber(number);
    }

    public void resetNumbers() {
        yourNumber.resetYourNumbers();
    }
}

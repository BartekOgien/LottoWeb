package com.application.lotto.facade;

import com.application.lotto.model.YourNumber;
import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LottoFacade {

    @Autowired
    private DrawCalculator drawCalculator;

    @Autowired
    private DbService dbService;

    @Autowired
    private YourNumber yourNumber;

    public List<Integer> facadeCompareNumbers() {
        return drawCalculator.compareNumbers();
    }

    public int getFacadeDrawCounts() {
        return dbService.getNumbersOfAllDraws();
    }

    public int calculateProfit() {
        return drawCalculator.getHowManyWon() - getFacadeDrawCost();
    }

    public int getFacadeDrawCost() {
        return drawCalculator.calculateCostOfAllDraws();
    }

    public int facadeHowManyWon() {
        return drawCalculator.wonCash();
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

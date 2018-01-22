package com.application.lotto.repository;

import com.application.lotto.model.DrawNumber;
import com.application.lotto.model.YourNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DrawCalculator {
    private static final int COST_PER_DRAW = 3;
    private static final int[] CASH_PER_NUMBER = {24, 200, 5800, 2000000};
    private int howManyWon = 0;

    @Autowired
    DbService dbService;

    @Autowired
    DrawNumbersDao drawNumbersDao;

    @Autowired
    YourNumber yourNumber;

    public int calculateCostOfAllDraws() {
        return dbService.getNumbersOfAllDraws() * COST_PER_DRAW;
    }

    public List<Integer> compareNumbers() {
        Set<Integer> numbers = yourNumber.getYourNumbers();
        if (numbers.size() != 6) {
            throw new RuntimeException();
        }

        List<DrawNumber> drawNumbers = drawNumbersDao.findAll();

        List<Set<Integer>> resultsNumbersInEachDrawNumbersCollection = new ArrayList<>();
        for (Integer integer : numbers) {
            Set<Integer> drawNumberIds = drawNumbers.stream()
                    .filter(drawNumber -> drawNumber.getNumbers().contains(integer))
                    .map(DrawNumber::getId)
                    .collect(Collectors.toSet());
            resultsNumbersInEachDrawNumbersCollection.add(drawNumberIds);
        }

        List<Integer> resultsPerDrawNumber = new ArrayList<>();
        for(DrawNumber drawNumber : drawNumbers){

            Integer predicted = (int)resultsNumbersInEachDrawNumbersCollection.stream().filter(set -> set.contains(drawNumber.getId())).count();
            resultsPerDrawNumber.add(predicted);
        }

        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i <= numbers.size() ; i++){
            int iCopy = i;
            int count = (int)resultsPerDrawNumber.stream().filter(predicted -> predicted == iCopy).count();
            ans.add(count);
        }

        ans.remove(0);
        ans.remove(0);
        ans.remove(0);
        return ans;
    }

    public int wonCash() {
        List<Integer> wonNumbers = compareNumbers();
        int wonCash = 0;
        if(wonNumbers.size() == 4) {
            for (int i = 0; i < 4; i++) {
                wonCash += wonNumbers.get(i) * CASH_PER_NUMBER[i];
            }
        }
        this.howManyWon = wonCash;
        return wonCash;
    }

    public int getHowManyWon() {
        return howManyWon;
    }
}

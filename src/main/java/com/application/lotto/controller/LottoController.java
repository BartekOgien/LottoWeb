package com.application.lotto.controller;

import com.application.lotto.model.DrawNumber;
import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawNumbersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/lotto")
public class LottoController {


    @Autowired
    DbService dbService;

    @Autowired
    DrawNumbersDao drawNumbersDao;


    @RequestMapping(method = RequestMethod.GET, value = "compare0")
    public int[] compareNumbers0(@RequestParam int... numbers) {

        int[] howManyNumbersGet = {0, 0, 0, 0};
        int numbersWon = 0;

        for (int i = 1; i <= dbService.getNumbersOfAllDraws(); i++) {

            for (int k = 0; k <= numbers.length - 1; k++) {

                if (drawNumbersDao.findDrawNumberByDrawId(i).getNumbers().contains(numbers[k])) {
                    numbersWon++;
                }
            }

            if (numbersWon >= 3) {
                howManyNumbersGet[numbersWon - 3]++;
            }

            numbersWon = 0;
        }
        return howManyNumbersGet;
    }

    @RequestMapping(method = RequestMethod.GET, value = "compare1")
    public List<Integer> compareNumbers(@RequestParam List<Integer> numbers) {

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

}

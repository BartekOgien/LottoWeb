package com.application.lotto.controller;

import com.application.lotto.facade.LottoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/lotto")
public class LottoController {

    @Autowired
    LottoFacade lottoFacade;

    @RequestMapping(method = RequestMethod.GET, value = "compare")
    public List<Integer> compareNumbers(@RequestParam List<Integer> numbers) {
        return lottoFacade.facadeCompareNumbers(numbers);
    }

    @RequestMapping(method = RequestMethod.GET, value = "drawCounts")
    public int getDrawCounts() {
        return lottoFacade.getFacadeDrawCounts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "drawCost")
    public int getDrawCost() {
        return lottoFacade.getFacadeDrawCost();
    }

    @RequestMapping(method = RequestMethod.GET, value = "howManyWon")
    public int howManyWon() {
        return lottoFacade.facadeHowManyWon();
    }

    @RequestMapping(method = RequestMethod.GET, value = "yourNumbers")
    public String getYourChoosenNumbers() {
        return lottoFacade.yourChooseNumbers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "chooseNumber")
    public void getYourChooseNumbers(@RequestParam int number) {
        lottoFacade.addChoosenNumber(number);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "resetNumbers")
    public void resetYourNumbers() {
        lottoFacade.resetNumbers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "profit")
    public int getProfit() {
        return lottoFacade.calculateProfit();
    }
}

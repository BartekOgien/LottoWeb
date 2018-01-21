package com.application.lotto.controller;

import com.application.lotto.facade.LottoFacade;
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
    public BigDecimal getDrawCost() {
        return lottoFacade.getFacadeDrawCost();
    }

    @RequestMapping(method = RequestMethod.GET, value = "howManyWon")
    public int howManyWon(@RequestParam List<Integer> wonNumbers) {
        return lottoFacade.facadeHowManyWon(wonNumbers);
    }
}

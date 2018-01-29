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
    public List<Integer> compareNumbersAndCalculateHowManyWon(@RequestParam List<Integer> numbers) {
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
}

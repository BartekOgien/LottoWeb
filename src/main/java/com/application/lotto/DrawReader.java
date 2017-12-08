package com.application.lotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class DrawReader {
    private final static String URL = "http://megalotto.pl/wyniki/lotto/losowania-z-roku-";
    private final static int YEAR_OF_DRAW_BEGIN = 1957;
    private final static int YEAR_OF_DRAW_END = 1967;
    //private final static int YEAR_OF_DRAW_END = LocalDate.now().getYear();

    @Autowired
    private DrawsGetter drawsGetter;


    public void getDataOfAllDraws() {
        for(int i = YEAR_OF_DRAW_BEGIN; i <= YEAR_OF_DRAW_END; i++) {
            try {
                drawsGetter.getDataFromHTML(URL + i);
            }
            catch (IOException e) {
                System.out.println("Something was wrong");
            }
        }
    }

}

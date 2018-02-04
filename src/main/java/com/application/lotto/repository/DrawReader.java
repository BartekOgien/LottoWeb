package com.application.lotto.repository;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class DrawReader {
    public final static String URL = "http://megalotto.pl/wyniki/lotto/losowania-z-roku-";
    private final static int YEAR_OF_DRAW_BEGIN = 1957;

    @Autowired
    private DrawsGetter drawsGetter;

    public void getDataOfAllDraws() {
        for(int i = YEAR_OF_DRAW_BEGIN; i <= LocalDate.now().getYear(); i++) {
            try {
                drawsGetter.getDataFromHTML(URL + i);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

package com.application.lotto.scheduler;

import com.application.lotto.repository.DrawReader;
import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawsGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDate;

public class DrawReaderScheduler {
    private final static int DRAWSCOUNT = 6000;

    @Autowired
    private DbService dbService;

    @Autowired
    private DrawReader drawReader;

    @Autowired
    private DrawsGetter drawsGetter;

    @Scheduled(cron = "0 0 10 * * *")
    public void getDataOfDraws() {
        if(dbService.getNumbersOfAllDraws() == 0 || dbService.getNumbersOfAllDraws() < DRAWSCOUNT) {
            drawReader.getDataOfAllDraws();
        }
        else {
            try {
                drawsGetter.getDataFromHTML(DrawReader.URL + LocalDate.now().getYear());
            }
            catch (IOException e) {
            }
        }
    }

    @Scheduled(cron = "0 0 0 2 1 ?")
    public void getLastDrawsFromOldYear (){
        try {
            drawsGetter.getDataFromHTML(DrawReader.URL + (LocalDate.now().getYear()-1));
        }
        catch (IOException e) {

        }
    }
}



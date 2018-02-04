package com.application.lotto.scheduler;

import com.application.lotto.repository.DrawReader;
import com.application.lotto.repository.DbService;
import com.application.lotto.repository.DrawsGetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Component
public class DrawReaderScheduler {
    private final static int DRAWSCOUNT = 6000;

    @Autowired
    private DbService dbService;

    @Autowired
    private DrawReader drawReader;

    @Autowired
    private DrawsGetter drawsGetter;

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(cron = "0 0 * * * *")
    public void getDataOfDraws() {
        if(dbService.getNumbersOfAllDraws() == 0 || dbService.getNumbersOfAllDraws() < DRAWSCOUNT) {
            log.info("Starting read data of all draws");
            drawReader.getDataOfAllDraws();
        }
        else {
            try {
                log.info("Starting read data for current year draws");
                drawsGetter.getDataFromHTML(DrawReader.URL + LocalDate.now().getYear());
            }
            catch (IOException e) {
            }
        }
    }

    @Scheduled(cron = "0 0 0 2 1 ?")
    public void getLastDrawsFromOldYear (){
        try {
            log.info("Starting read data from previous year");
            drawsGetter.getDataFromHTML(DrawReader.URL + (LocalDate.now().getYear()-1));
        }
        catch (IOException e) {
        }
    }
}



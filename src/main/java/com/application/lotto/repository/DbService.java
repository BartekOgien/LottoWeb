package com.application.lotto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbService {
    @Autowired
    DrawNumbersDao drawNumbersDao;

    public int getNumbersOfAllDraws() {
        return (int)drawNumbersDao.count();
    }
}

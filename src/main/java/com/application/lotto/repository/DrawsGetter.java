package com.application.lotto.repository;

import com.application.lotto.model.DrawNumber;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DrawsGetter {
    private final String ID_STRING = "nr_in_list\">";
    private final String DATE_STRING = "date_in_list\">";
    private final String NUMBERS_STRING = "numbers_in_list \">";
    private final String LISTS_HTML = " </li>";
    private final String DIV_HTML = "div.lista_ostatnich_losowan";
    private final String ULISTS_HTML = "ul";
    private final String LISTNUMBERS_HTML = "li.numbers_in_list";
    private int drawId;
    private String drawDate;
    private List<Integer> numbers = new ArrayList<>();
    private DrawNumber drawNumber;
    private String elementString;
    private String tempString;
    private int numberIndex = 0;
    private int numberOfDot = 0;

    @Autowired
    private DrawNumbersDao drawNumbersDao;

    public void getNumbersFromHTML(Elements elementNumbers) {
        numbers.clear();

        for(int i=0; i<6; i++) {
            elementString = elementNumbers.get(i).toString();
            numberIndex = elementString.indexOf(NUMBERS_STRING);
            numberOfDot = elementString.indexOf(LISTS_HTML);
            tempString = elementString.substring(numberIndex + 18, numberOfDot);

            numbers.add(Integer.valueOf(tempString));
        }
    }

    public void getDataFromHTML(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements drawElements = document.select(DIV_HTML);


        for(Element elementList: drawElements) {
            Elements elementUl = elementList.select(ULISTS_HTML);

            for(Element elementUlResult: elementUl) {
                elementString = elementUlResult.toString();
                numberIndex = elementString.indexOf(ID_STRING);
                numberOfDot = elementString.indexOf(".");
                tempString = elementString.substring(numberIndex + 12, numberOfDot);
                drawId = Integer.valueOf(tempString);

                numberIndex = elementString.indexOf(DATE_STRING);
                tempString = elementString.substring(numberIndex + 14, numberIndex + 24);
                drawDate = tempString;

                Elements elementNumbers = elementUlResult.select(LISTNUMBERS_HTML);
                getNumbersFromHTML(elementNumbers);
                drawNumber = new DrawNumber(drawId, drawDate, numbers);

                try {
                    drawNumbersDao.save(drawNumber);
                }
                catch (NullPointerException e) {
                    log.error(e.getMessage());
                }
                catch (DataIntegrityViolationException ex) {
                    log.error(ex.getMessage());
                }
            }
        }
    }
}

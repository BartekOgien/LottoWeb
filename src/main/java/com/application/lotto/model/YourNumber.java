package com.application.lotto.model;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class YourNumber {
    private static final int YOUR_NUMBERS_SIZE = 6;
    private static final int NUMBERS_MIN = 1;
    private static final int NUMBERS_MAX = 49;
    private Set<Integer> yourNumbers = new HashSet<>();

    public void addYourNumber(int number) {
        if(yourNumbers.size() < YOUR_NUMBERS_SIZE && number >= NUMBERS_MIN && number <= NUMBERS_MAX) {
            yourNumbers.add(number);
        }
    }

    public Set<Integer> getYourNumbers() {
        return yourNumbers;
    }

    public String getStringYourNumbers() {
        String numbersString = yourNumbers.stream()
                                .map(n -> String.valueOf(n))
                                .collect(Collectors.joining("  "));
        return numbersString;
    }

    public void resetYourNumbers() {
        yourNumbers.clear();
    }
}

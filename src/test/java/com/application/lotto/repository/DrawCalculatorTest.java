package com.application.lotto.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DrawCalculatorTest {

    @InjectMocks
    DrawCalculator drawCalculator;

    @Mock
    DbService dbService;

    @Test
    public void shouldCalculateCostOfAllDraws() {
        //Given
        int numberOfAllDraws = 10;
        when(dbService.getNumbersOfAllDraws()).thenReturn(numberOfAllDraws);

        //When
        int cost = drawCalculator.calculateCostOfAllDraws();

        //Then
        assertEquals(30, cost);
    }

    @Test
    public void shouldCalculateWonCash() {
        //Given
        List<Integer> wonNumbersOnes = new ArrayList<>();
        List<Integer> wonNumbersZeros = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            wonNumbersOnes.add(1);
            wonNumbersZeros.add(0);
        }

        //When
        int resultOnes = drawCalculator.wonCash(wonNumbersOnes);
        int resultZeros = drawCalculator.wonCash(wonNumbersZeros);

        //Then
        assertEquals(2006024, resultOnes);
        assertEquals(0, resultZeros);
    }
}
package com.application.lotto.Mytest;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class COs {
    private Set<Integer> numbers = new HashSet<>();

    @Test
    public void testCos() {
        numbers.add(1);
        numbers.add(2);
        System.out.println(numbers.size());
        numbers.clear();
        System.out.println(numbers.size());
    }

}

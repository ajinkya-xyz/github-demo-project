package com.upgrad.springboottesting.unit;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class LoanCalculatorV2Test {

    LoanCalculator loanCalculator;

    @BeforeEach
    void setUp() {
        // Do not share data/object between tests, use before each
        loanCalculator = new LoanCalculator();
    }


    @Test
    // Naming -  should talk about scenario
    void shouldReturnZeroInterestWhenAmountIsZero() {
        double interest = loanCalculator.calculateInterest(0, 12, 6);
        //assertEquals(interest, 0)
        // Hamcrest
        assertThat(interest, is(0.0));
    }

    @Test
        // Naming -  should talk about scenario
    void shouldReturnZeroInterestWhenMonthIsZero() {
        double interest = loanCalculator.calculateInterest(10, 0, 6);
        // Assert per unit test
        assertThat(interest, is(0.0));
    }

    @ParameterizedTest
    @MethodSource("parameterProvider")
    void shouldReturnInterestForGivenAmountMonthAndRate(double amount, double month, double rate, double expectedInterest) {

        double interest = loanCalculator.calculateInterest(amount, month, rate);
        // Assert per unit test
        assertThat(interest, is(expectedInterest));
    }

    public static Stream<Arguments> parameterProvider(){
        return Stream.of(Arguments.of(10000, 1, 0.12, 100.0),
                Arguments.of(20000, 1, 0.12, 200.0),
                Arguments.of(30000, 1, 0.12, 300.0));
    }


}

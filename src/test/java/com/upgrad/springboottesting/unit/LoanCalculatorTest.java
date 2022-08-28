package com.upgrad.springboottesting.unit;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest {
    // DO NOT DO THIS
    // Test should be independent
    private LoanCalculator loanCalculator;

    @BeforeEach
    void setUp() {
        loanCalculator = new LoanCalculator();
    }

    // Name should indicate the scenario under test
    // DO NOT USE generic names
    @Test
    void shouldReturnZeroWhenAmountIsZero() {
        double interest = loanCalculator.calculateInterest(0, 12, 12);
        assertEquals(0.0, interest);
        // Hamcrest - Assertion Library
        assertThat(interest, is(0.0));
    }

    @Test
    void shouldReturnZeroWhenDurationIsZero() {
        double interest = loanCalculator.calculateInterest(10, 0, 12);
        assertEquals(0.0, interest);
        // Hamcrest - Assertion Library
        assertThat(interest, is(0.0));
    }

    //Do not put multiple asserts in one unit test
    @Test
    void shouldReturnInterestForGivenAmountAndDuration() {
        double interest = loanCalculator.calculateInterest(100, 12, 10);
        assertEquals(1000.0000000000001, interest);
        // Hamcrest - Assertion Library
        assertThat(interest, is(1000.0000000000001));
    }

    @ParameterizedTest
    @MethodSource("interestCalculateParameterProvider")
    void shouldReturnInterestForGivenAmountDurationAndInterestRate(double amount, double duration, double interestRate, double exptectedInterest) {
        double interest = loanCalculator.calculateInterest(amount, duration, interestRate);
        MatcherAssert.assertThat(interest, is(exptectedInterest));
    }

    public static Stream<Arguments> interestCalculateParameterProvider(){
        return Stream.of(Arguments.of(10000, "", 0.12, 100.0),
                Arguments.of(20000, 1, 0.12, 2000.0),
                Arguments.of(30000, 1, 0.12, 300.0));
    }

    public static Stream<Arguments> parameterProvider(){
        return Stream.of(Arguments.of(10000, "1", null, 0.12, 100.0),
                Arguments.of(20000, 1, 0.12, 2000.0),
                Arguments.of(30000, 1, 0.12, 300.0));
    }
}
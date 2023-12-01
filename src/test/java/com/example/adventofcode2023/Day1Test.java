package com.example.adventofcode2023;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    public void canParseInput() {
        String testInput = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """.trim();
        List<String> actual = new Day1().parseInput(testInput);
        List<String> expected = List.of("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet");
        assertEquals(expected, actual);
    }

    @Test
    void canReturnsNumbersInString() {
        String testInput = "treb7uchet";
        List<String> actual = new Day1().findNumbersInString(testInput);
        List<String> expected = List.of("7");
        assertEquals(expected, actual);

        String testInput1 = "1abc2";
        List<String> actual1 = new Day1().findNumbersInString(testInput1);
        List<String> expected1 = List.of("1", "2");
        assertEquals(expected1, actual1);
    }

    @Test
    void canCreateNewNumber() {
        List<String> testInput = List.of("1", "2");
        Integer actual = new Day1().getNewInteger(testInput);
        Integer expected = 12;
        assertEquals(expected, actual);

        List<String> testInput1 = List.of("7");
        Integer actual1 = new Day1().getNewInteger(testInput1);
        Integer expected1 = 77;
        assertEquals(expected1, actual1);

        List<String> testInput2 = List.of("1", "2", "3", "4", "5");
        Integer actual2 = new Day1().getNewInteger(testInput2);
        Integer expected2 = 15;
        assertEquals(expected2, actual2);
    }

    @Test
    void canFindFinalAnswer() {
        String testInput = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """.trim();
        Integer actual = new Day1().getAnswer(testInput);
        Integer expected = 142;
        assertEquals(expected, actual);
    }

    @Test
    void canFindFinalAnswerWithFile() throws IOException {
        String data = readFromFile("src/test/resources/com/example/adventofcode2023/day1part1.txt");
        Integer actual = new Day1().getAnswer(data);
        Integer expected = 53194;
        assertEquals(expected, actual);
    }

    private String readFromFile(String file)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @Test
    void canFindWordNumber() {
        String testInput = "two1nine";
        List<String> actual = new Day1().findNumbersInStringPartTwo(testInput);
        List<String> expected = List.of("2", "1", "9");
        assertEquals(expected, actual);

        String testInput1 = "abcone2threexyz";
        List<String> actual1 = new Day1().findNumbersInStringPartTwo(testInput1);
        List<String> expected1 = List.of("1", "2", "3");
        assertEquals(expected1, actual1);

        String testInput2 = "xtwone3four";
        List<String> actual2 = new Day1().findNumbersInStringPartTwo(testInput2);
        List<String> expected2 = List.of("2", "1", "3", "4");
        assertEquals(expected2, actual2);

        //Only caring about the first and last index of
        String testInput3 = "577gnmbvzf7";
        List<String> actual3 = new Day1().findNumbersInStringPartTwo(testInput3);
        List<String> expected3 = List.of("5","7","7");
        assertEquals(expected3, actual3);

        //Repeated number and letters
        String testInput4 = "zkxjhgprtrlcfeight795five8";
        List<String> actual4 = new Day1().findNumbersInStringPartTwo(testInput4);
        List<String> expected4 = List.of("8","7","9","5","5","8");
        assertEquals(expected4, actual4);

    }

    @Test
    void canAnswerPart2() {
        String testInput = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """.trim();
        Integer actual = new Day1().findAnswerPartTwo(testInput);
        assertEquals(281, actual);
    }

    @Test
    void canAnswerPart2WithFileInput() throws IOException {
        String data = readFromFile("src/test/resources/com/example/adventofcode2023/day1part1.txt");
        Integer actual = new Day1().findAnswerPartTwo(data);
        assertEquals(54249, actual);
    }

}
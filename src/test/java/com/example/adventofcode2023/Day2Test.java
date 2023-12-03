package com.example.adventofcode2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void canParseInput() {
        String testInput = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                """.trim();
        Map<Integer, List<String>> actual = new Day2().parseInput(testInput);
        Map<Integer, List<String>> expected = new HashMap<>();
        expected.put(1, List.of("3 blue, 4 red","1 red, 2 green, 6 blue", "2 green"));
        expected.put(2, List.of("1 blue, 2 green","3 green, 4 blue, 1 red", "1 green, 1 blue"));
        assertEquals(expected, actual);
    }

    @Test
    void canConfirmIfGamesIsPossible() {
        List<String> testInput = List.of("3 blue", "4 red", "1 red", "2 green", "6 blue", "2 green");

        boolean actual = new Day2().isGamePossible(testInput);
        assertEquals(true, actual);
    }

    @Test
    void canCreateCubeCountMapList() {
        List<String> testInput = List.of("3 blue, 4 red","1 red, 2 green, 6 blue", "2 green");

        List<String> actual = new Day2().getCubesFromAllBags(testInput);
        List<String> expected = List.of("3 blue", "4 red", "1 red", "2 green", "6 blue", "2 green");

        assertEquals(expected, actual);
    }

    @Test
    void canComputeFinalAnswer() {
        String testInput = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """.trim();

        Integer actual = new Day2().computeAnswer(testInput);

        assertEquals(8, actual);
    }

    @Test
    void canComputeFinalAnswerWithInputFile() throws IOException {
        String data = new Day1Test().readFromFile("src/test/resources/com/example/adventofcode2023/day2.txt");
        Integer actual = new Day2().computeAnswer(data);

        assertEquals(2317, actual);
    }

    @Test
    void canComputeFinalAnswerPartTwo() {
        String testInput = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """.trim();

        Integer actual = new Day2().computeAnswerPart2(testInput);

        assertEquals(2286, actual);
    }

    @Test
    void canComputeFinalAnswerPartTwoWithInputFile() throws IOException {
        String data = new Day1Test().readFromFile("src/test/resources/com/example/adventofcode2023/day2.txt");
        Integer actual = new Day2().computeAnswerPart2(data);

        assertEquals(74804, actual);
    }

    @Test
    void canFindMinimumFromBag() {
        List<String> testInput = List.of("3 blue", "4 red", "1 red", "2 green", "6 blue", "2 green");

        Map<String, Integer> actual = new Day2().computePower(testInput);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("blue", 6);
        expected.put("red", 4);
        expected.put("green", 2);
        assertEquals(expected, actual);
    }

}
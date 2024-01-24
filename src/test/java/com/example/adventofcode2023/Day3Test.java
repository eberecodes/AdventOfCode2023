package com.example.adventofcode2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void canParseInputAsCoordinates() {
        String testInput = """
                467..114.
                ...*.....
                """.trim();
        Map<Coordinate, Character> actual = new Day3().parseInput(testInput);
        Map<Coordinate, Character> expected = new HashMap<>();
        expected.put(new Coordinate(0,0), '4');
        expected.put(new Coordinate(0,1), '6');
        expected.put(new Coordinate(0,2), '7');
        expected.put(new Coordinate(0,3), '.');
        expected.put(new Coordinate(0,4), '.');
        expected.put(new Coordinate(0,5), '1');
        expected.put(new Coordinate(0,6), '1');
        expected.put(new Coordinate(0,7), '4');
        expected.put(new Coordinate(0,8), '.');
        expected.put(new Coordinate(1,0), '.');
        expected.put(new Coordinate(1,1), '.');
        expected.put(new Coordinate(1,2), '.');
        expected.put(new Coordinate(1,3), '*');
        expected.put(new Coordinate(1,4), '.');
        expected.put(new Coordinate(1,5), '.');
        expected.put(new Coordinate(1,6), '.');
        expected.put(new Coordinate(1,7), '.');
        expected.put(new Coordinate(1,8), '.');
        assertEquals(expected, actual);

    }

    @Test
    void canFindNonNumericChars() {
        Map<Coordinate, Character> testInput = new HashMap<>();
        testInput.put(new Coordinate(0,0), '4');
        testInput.put(new Coordinate(0,1), '6');
        testInput.put(new Coordinate(0,2), '7');
        testInput.put(new Coordinate(0,5), '1');
        testInput.put(new Coordinate(0,6), '1');
        testInput.put(new Coordinate(0,7), '4');
        testInput.put(new Coordinate(1,3), '*');
        Map<Coordinate, Character> actual = new Day3().findSpecialChars(testInput);
        Map<Coordinate, Character> expected = new HashMap<>();
        expected.put(new Coordinate(1,3), '*');
        assertEquals(expected, actual);
    }

    @Test
    void canFindConfirmIfAdjacentSpecialChar() {
        Map<Coordinate, Character> testInput = new HashMap<>();
        testInput.put(new Coordinate(0,0), '4');
        testInput.put(new Coordinate(0,1), '6');
        testInput.put(new Coordinate(0,2), '7');
        testInput.put(new Coordinate(0,3), '.');
        testInput.put(new Coordinate(0,4), '.');
        testInput.put(new Coordinate(0,5), '1');
        testInput.put(new Coordinate(0,6), '1');
        testInput.put(new Coordinate(0,7), '4');
        testInput.put(new Coordinate(0,8), '.');
        testInput.put(new Coordinate(1,0), '.');
        testInput.put(new Coordinate(1,1), '.');
        testInput.put(new Coordinate(1,2), '.');
        testInput.put(new Coordinate(1,3), '*');
        testInput.put(new Coordinate(1,4), '.');
        testInput.put(new Coordinate(1,5), '.');
        testInput.put(new Coordinate(1,6), '.');
        testInput.put(new Coordinate(1,7), '.');
        testInput.put(new Coordinate(1,8), '.');
        List<Integer> actual = new Day3().findAdjacentNumber(testInput);
        List<Integer> expected = List.of(467);
        assertEquals(expected, actual);
    }

    @Test
    void canFindAnswer() {
        String testInput = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """.trim();
        Integer answer = new Day3().findAnswer(testInput);
        assertEquals(4361, answer);
    }

    @Test
    void canFindAnswer2() {
        String testInput = """
                ...................819.......=..-.........................198..............=..........418...60............904...............................
                781...663..250...................281....905.+...187....%..............271..852...........=..*...484...517*.................................*
                .......*......=....865.826...%............&.679..*.....160..576.....................462+...717.............&........389........783....67...5
                """.trim();
        System.out.println(new Day3().findAdjacentNumber(new Day3().parseInput(testInput)));
        Integer answer = new Day3().findAnswer(testInput);
        assertEquals(7060, answer);
    }

    @Test
    void canFindAnswerWithTestFile() throws IOException {
        String testInput = new Day1Test().readFromFile("src/test/resources/com/example/adventofcode2023/day3.txt");
        Integer answer = new Day3().findAnswer(testInput);
        assertEquals(526404, answer);
    }
}
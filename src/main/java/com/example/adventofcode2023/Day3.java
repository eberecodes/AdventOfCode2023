package com.example.adventofcode2023;

import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    public Map<Coordinate, Character> parseInput(String input) {
        List<String> schematic = input.lines().toList();
        Map<Coordinate, Character> coordinateToChar = new HashMap<>();
        for (int i = 0; i < schematic.size(); i++) {
            String curr = schematic.get(i);
            for (int j = 0; j < curr.length(); j++) {
                coordinateToChar.put(new Coordinate(i, j), curr.charAt(j));
            }
        }
        return coordinateToChar;
    }

    public Map<Coordinate, Character> findSpecialChars(Map<Coordinate, Character> input) {
        return input.entrySet()
                .stream()
                .filter(entry -> !Character.isDigit(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> findAdjacentNumber(Map<Coordinate, Character> input) {
        var num = "";
        var adjacentNumbers = new ArrayList<Integer>();
        var maxX = input.keySet().stream().max(Comparator.comparing(Coordinate::x)).get().x();
        var maxY = input.keySet().stream().max(Comparator.comparing(Coordinate::y)).get().y();
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                Character curr = input.get(new Coordinate(i, j));
                if (Character.isDigit(curr)) {
                    num += curr.toString();
                } else {
                    var len = num.length();
                    if (len > 0 && specialCharAdjacent(input, len, new Coordinate(i, j))) {
                        try {
                            adjacentNumbers.add(Integer.parseInt(num));
                        } catch (Exception ignored) {
                        }
                    }
                    num = "";
                }
                if (Character.isDigit(curr) && j == maxY) {
                    Coordinate coordinateNext = new Coordinate(i, j + 1);
                    input.put(coordinateNext, '.');
                    if (specialCharAdjacent(input, num.length(), coordinateNext)) {
                        try {
                            adjacentNumbers.add(Integer.parseInt(num));
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
            num = "";
        }

        return adjacentNumbers;
    }

    private boolean specialCharAdjacent(Map<Coordinate, Character> input, Integer length, Coordinate currCoord) {
        if ((!Character.isDigit(input.get(currCoord)) && input.get(currCoord) != '.')) {
            return true;
        }
        if ((!Character.isDigit(input.getOrDefault(new Coordinate(currCoord.x(), currCoord.y() - (length + 1)), '.')) && input.getOrDefault(new Coordinate(currCoord.x(), currCoord.y() - (length + 1)), '.') != '.')) {
            return true;
        }
        for (int i = 0; i <= length + 1; i++) {
            Character curr = input.getOrDefault(new Coordinate(currCoord.x() - 1, currCoord.y() - i), '1');
            if ((!Character.isDigit(curr)) && curr != '.') {
                return true;
            }
        }
        for (int i = 0; i <= length + 1; i++) {
            Character curr = input.getOrDefault(new Coordinate(currCoord.x() + 1, currCoord.y() - i), '1');
            if ((!Character.isDigit(curr)) && curr != '.') {
                return true;
            }
        }

        return false;
    }


    public Integer findAnswer(String input) {
        return findAdjacentNumber(parseInput(input))
                .stream()
                .reduce(0, Integer::sum);
    }
}



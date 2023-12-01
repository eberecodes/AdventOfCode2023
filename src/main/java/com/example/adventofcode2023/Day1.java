package com.example.adventofcode2023;

import java.util.*;
import java.util.stream.Stream;

public class Day1 {
    public List<String> parseInput(String input) {
        return input.lines().toList();
    }

    public List<String> findNumbersInString(String input) {
        String[] charsString = input.split("");
        return Arrays.stream(charsString).filter(letter -> letter.matches("-?\\d+(\\.\\d+)?")).toList();
    }

    public Integer getAnswer(String input) {
        List<String> calibrationsLines = parseInput(input);
        List<List<String>> numbersInStrings = calibrationsLines.stream().map(this::findNumbersInString).toList();
        List<Integer> newIntegers = numbersInStrings.stream().flatMap(it -> Stream.of(getNewInteger(it))).toList();
        return newIntegers.stream().reduce(0, Integer::sum);
    }

    public Integer getNewInteger(List<String> input) {
        int len = input.size();
        return Integer.parseInt(input.get(0)+input.get(len-1));
    }

    public Map<String, String> createNumberMap(){
        Map<String, String> textNumberToShortNumber = new HashMap<>();
        textNumberToShortNumber.put("one", "1");
        textNumberToShortNumber.put("two", "2");
        textNumberToShortNumber.put("three", "3");
        textNumberToShortNumber.put("four", "4");
        textNumberToShortNumber.put("five", "5");
        textNumberToShortNumber.put("six", "6");
        textNumberToShortNumber.put("seven", "7");
        textNumberToShortNumber.put("eight", "8");
        textNumberToShortNumber.put("nine", "9");
        return textNumberToShortNumber;
    }

    public List<String> findNumbersInStringPartTwo(String input) {
        Map<String,String> numberMap = createNumberMap();
        String word = input.toLowerCase();
        Map<Integer, String> foundWordToIndex = new HashMap<>();
        for (Map.Entry<String, String> number : numberMap.entrySet()) {
            if(word.contains(number.getKey())){
                foundWordToIndex.put(word.indexOf(number.getKey()), numberMap.get(number.getKey()));
                foundWordToIndex.put(word.lastIndexOf(number.getKey()), numberMap.get(number.getKey()));
            }
            if (word.contains(number.getValue())){
                foundWordToIndex.put(word.indexOf(number.getValue()), number.getValue());
                foundWordToIndex.put(word.lastIndexOf(number.getValue()), number.getValue());
            }
        }
        return foundWordToIndex.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(Map.Entry::getValue).toList();
    }

    public Integer findAnswerPartTwo(String input) {
        List<String> calibrationsLines = parseInput(input);
        List<List<String>> numbersInStrings = calibrationsLines.stream().map(this::findNumbersInStringPartTwo).toList();
        List<Integer> newIntegers = numbersInStrings.stream().flatMap(it -> Stream.of(getNewInteger(it))).toList();
        return newIntegers.stream().reduce(0, Integer::sum);
    }
}

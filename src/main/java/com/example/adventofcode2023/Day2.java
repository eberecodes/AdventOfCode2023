package com.example.adventofcode2023;

import java.util.*;

public class Day2 {
    public Map<Integer, List<String>> parseInput(String input) {
        List<String> games = input.lines().toList();
        Map<Integer, List<String>> gameToBags = new HashMap<>();

        games.forEach(game -> {
            String[] gameSplit = game.split(": |; ");
            int gameNumber = Integer.parseInt(gameSplit[0].replaceAll("[^0-9]", ""));
            List<String> bags = new ArrayList<>(Arrays.stream(gameSplit).toList());
            bags.remove(0);
            gameToBags.put(gameNumber, bags);
        });

        return gameToBags;
    }

    public boolean isGamePossible(List<String> input) {
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        for (String cube : input){
            if(cube.contains("red")){
                if(Integer.parseInt(cube.replaceAll("[^0-9]", "")) > maxRed) {
                    return false;
                }
            }
            if(cube.contains("green")){
                if(Integer.parseInt(cube.replaceAll("[^0-9]", "")) > maxGreen) {
                    return false;
                }
            }
            if(cube.contains("blue")){
                if(Integer.parseInt(cube.replaceAll("[^0-9]", "")) > maxBlue) {
                    return false;
                }
            }
        }

        return true;
    }

    public List<String> getCubesFromAllBags(List<String> input) {
        List<String> cubesFromBag = new ArrayList<>();
        input.forEach(it -> {
            cubesFromBag.addAll(List.of(it.split(", ")));
        });

        return cubesFromBag;
    }

    public Integer computeAnswer(String input) {
        Map<Integer, List<String>> gameNumberToBags = parseInput(input);
        Map<Integer, Boolean> gamePossible = new HashMap<>();
        gameNumberToBags.forEach((key, value) -> gamePossible.put(key, isGamePossible(getCubesFromAllBags(value))));
        List<Integer> answer = gamePossible.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).toList();

        return answer.stream().reduce(0, Integer::sum);
    }

    public Integer computeAnswerPart2(String input) {
        Map<Integer, List<String>> gameNumberToBags = parseInput(input);
        Map<Integer, Integer> gamePower = new HashMap<>();
        gameNumberToBags.forEach((key, value) -> gamePower.put(key, computePower(getCubesFromAllBags(value)).values().stream().reduce(1, (curr, next) -> curr*next)));

        return gamePower.values().stream().reduce(0, Integer::sum);
    }

    public Map<String, Integer> computePower(List<String> input) {
        Map<String, Integer> colourToMinNumber = new HashMap<>();
        colourToMinNumber.put("red", 0);
        colourToMinNumber.put("green", 0);
        colourToMinNumber.put("blue", 0);

        for (String cube : input){
            if(cube.contains("red")){
                if(Integer.parseInt(cube.replaceAll("[^0-9]", "")) > colourToMinNumber.get("red")) {
                    colourToMinNumber.put("red", Integer.parseInt(cube.replaceAll("[^0-9]", "")));
                }
            }
            if(cube.contains("green")){
                if(Integer.parseInt(cube.replaceAll("[^0-9]", "")) > colourToMinNumber.get("green")) {
                    colourToMinNumber.put("green", Integer.parseInt(cube.replaceAll("[^0-9]", "")));
                }
            }
            if(cube.contains("blue")){
                if(Integer.parseInt(cube.replaceAll("[^0-9]", "")) > colourToMinNumber.get("blue")) {
                    colourToMinNumber.put("blue", Integer.parseInt(cube.replaceAll("[^0-9]", "")));
                }
            }
        }

        return colourToMinNumber;
    }
}

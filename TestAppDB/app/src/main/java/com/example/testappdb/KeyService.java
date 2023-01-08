package com.example.testappdb;

import java.util.Arrays;
import java.util.List;

public class KeyService {

    private static int hyperthymicity;
    private static int dysthymicity;
    private static int cyclothymicity;
    private static int excitability;
    private static int jamming;
    private static int emotivity;
    private static int exaltation;
    private static int anxiety;
    private static int pedantry;
    private static int demonstrativeness;

    public int getHyperthymicity() {
        return hyperthymicity;
    }

    public int getDysthymicity() {
        return dysthymicity;
    }

    public int getCyclothymicity() {
        return cyclothymicity;
    }

    public int getExcitability() {
        return excitability;
    }

    public int getJamming() {
        return jamming;
    }

    public int getEmotivity() {
        return emotivity;
    }

    public int getExaltation() {
        return exaltation;
    }

    public int getAnxiety() {
        return anxiety;
    }

    public int getPedantry() {
        return pedantry;
    }

    public int getDemonstrativeness() {
        return demonstrativeness;
    }


    private final List<Integer> hList = Arrays.asList(1,11,23,33,45,55,67,77);
    private final List<Integer> dysList = Arrays.asList(9,21,43,75,87);
    private final List<Integer> cList = Arrays.asList(6,18,28,40,50,62,72,84);
    private final List<Integer> excitList = Arrays.asList(8,20,30,42,52,64,74,86);
    private final List<Integer> jList = Arrays.asList(2,15,24,34,37,56,68,78,81);
    private final List<Integer> emotList = Arrays.asList(3,13,35,47,57,69,79);
    private final List<Integer> exalList = Arrays.asList(10,32,54,76);
    private final List<Integer> aList = Arrays.asList(16,27,38,49,60,71,82);
    private final List<Integer> pList = Arrays.asList(4,14,17,26,39,48,58,61,70,80,83);
    private final List<Integer> demList = Arrays.asList(7,19,22,29,41,44,63,66,73,85,88);

    private final List<Integer> dysListMinus = Arrays.asList(31,53,65);
    private final List<Integer> jListMinus = Arrays.asList(12,46,59);
    private final Integer emotListMinus = 25;
    private final Integer aListMinus = 5;
    private final Integer pListMinus = 36;
    private final Integer demListMinus = 51;

    public void resetScore(){
        hyperthymicity = 0;
        dysthymicity = 0;
        cyclothymicity = 0;
        excitability = 0;
        jamming = 0;
        emotivity = 0;
        exaltation = 0;
        anxiety = 0;
        pedantry = 0;
        demonstrativeness = 0;
    }


    public void AddPoints(int index, boolean choose){
        index = index +1;
        if(choose) {
            if (hList.contains(index)) hyperthymicity = hyperthymicity + 1;
            if (dysList.contains(index)) dysthymicity = dysthymicity + 1;
            if (cList.contains(index)) cyclothymicity = cyclothymicity + 1;
            if (excitList.contains(index)) excitability = excitability + 1;
            if (jList.contains(index)) jamming = jamming + 1;
            if (emotList.contains(index)) emotivity = emotivity + 1;
            if (exalList.contains(index)) exaltation = exaltation + 1;
            if (aList.contains(index)) anxiety = anxiety + 1;
            if (pList.contains(index)) pedantry = pedantry + 1;
            if (demList.contains(index)) demonstrativeness = demonstrativeness + 1;
        }
        else{
            if (dysListMinus.contains(index)) dysthymicity = dysthymicity + 1;
            if (jListMinus.contains(index)) jamming = jamming + 1;
            if (emotListMinus == index) emotivity = emotivity + 1;
            if (aListMinus == index) anxiety = anxiety + 1;
            if (pListMinus == index) pedantry = pedantry + 1;
            if (demListMinus == index) demonstrativeness = demonstrativeness + 1;
        }
    }

}

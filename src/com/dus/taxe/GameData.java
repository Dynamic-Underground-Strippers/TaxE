package com.dus.taxe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Matt Carabine on 20/11/2014.
 * Not sure how it'll be used yet
 * Had an idea of storing possible maps in here?
 */
public class GameData {
    private static ArrayList<Goal> possibleGoals = new ArrayList<Goal>();
    public static Goal getRandomGoal() {
       return possibleGoals.get(((int) Math.random() * possibleGoals.size()));
    }
    //Maybe this time plzzzzzzzzzzz
}

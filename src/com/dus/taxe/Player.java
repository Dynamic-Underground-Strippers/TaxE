package com.dus.taxe;

import java.util.ArrayList;
import java.lang.Object;
import java.util.Random;


//TODO: Give player a random engine
//TODO: Give player a random upgrade
//TODO: Implement basic trains: storing them, check max for each; implement discarding random upgrade and engines and unstarted goals
public class Player {
    private final String name;
    private int points;
    private ArrayList<Goal> currentGoals;
    private ArrayList<Upgrade> upgradeInventory;
    private ArrayList<Engine> engineInventory;
    private ArrayList<Train> currentTrains;


    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.currentGoals = new ArrayList<Goal>();
        this.upgradeInventory = new ArrayList<Upgrade>();
        this.engineInventory = new ArrayList<Engine>();
    }


    public void addTrain(){
        if (currentTrains.size()<3){
            currentTrains.add(new Train());
        }
    }

    public void removeTrain(int i){
        currentTrains.remove(i);
    }

    public int getTrainIndex(Train t){
        return currentTrains.indexOf(t);
    }

    public ArrayList<Train> getTrains(){
        return currentTrains;
    }

    public Train getTrain(int i){
        return currentTrains.get(i);
    }


    public boolean hasMaxUpgrades()
    {
        if (upgradeInventory.size()==4)
        {
            return true;

        }
        return false;

    }

    public boolean hasMaxEngines()
    {
        if (engineInventory.size()==3)
        {
            return true;

        }
        return false;

    }

    public boolean hasMaxGoals (){
        if (currentGoals.size()==3)
        {
            return true;
        }
        return false;
    }

    public void giveRandomEngine(){
        this.engineInventory.add(new Engine());
    }

    public void giveRandomUpgrade(){ //Need to come up with more upgrades
        this.upgradeInventory.add(new Upgrade());

    }


    public int randomUnstartedGoal () {

        for (int i = 2; i >= 0; i--) {
            if (currentGoals.get(i).getCurrentTrain() != null) {
                return i;
            }
        }
        return -1;
    }

    public Goal discardUnstartedGoal(){
        if (! this.hasMaxGoals() && randomUnstartedGoal()!=-1)
        {
            int randomIndex=randomUnstartedGoal();
            Goal discardedGoal= currentGoals.get(randomIndex);

            currentGoals.remove(randomIndex);

            return discardedGoal;
        }
        return null;
    }

    public Upgrade discardRandUpgrade()
    {
        //This method removes a random upgrade from the list, provided the player has maximum number
        //of upgrades, and returns it.
        if (this.hasMaxUpgrades())
        {
            int randomIndex=new Random().nextInt(upgradeInventory.size());
            Upgrade discardedUpgrade= upgradeInventory.get(randomIndex);

            for (int i = randomIndex; i<upgradeInventory.size()-1; i++)
            {
                upgradeInventory.set(i, upgradeInventory.get(i+1));
            }

            {
                return discardedUpgrade;
            }


        }
        return null;
    }

    public Engine discardRandEngine()
    {
        //This method removes a random engine from the list, provided the player has maximum number
        //of upgrades, and returns it.
        if (this.hasMaxEngines())
        {
            int randomIndex=new Random().nextInt(engineInventory.size());
            Engine discardedEngine= engineInventory.get(randomIndex);

            for (int i=randomIndex; i<engineInventory.size()-1; i++)
            {
                engineInventory.set(i, engineInventory.get(i+1));
            }

            {
                return discardedEngine;
            }



        }
        return null;
    }



    public String getName() {

        return this.name;
    }

    public int getPoints() {

        return this.points;
    }

    public void addPoints(int points) {

        this.points += points;
    }

    public void selectRandomEngine() {
    }

    public void completeGoals(){
        for (Goal goal: currentGoals){
            if (goal.isComplete()){
                addPoints(goal.getPoints());
                currentGoals.remove(goal);
            }
        }
    }

    public void selectRandomUpgrade() {
    }
}

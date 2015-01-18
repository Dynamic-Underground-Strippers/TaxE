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
    private ArrayList<Train> currentTrains = new ArrayList<Train>();


    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.currentGoals = new ArrayList<Goal>();
        this.upgradeInventory = new ArrayList<Upgrade>();
        this.engineInventory = new ArrayList<Engine>();
    }

    public int trainSize (){
        return currentTrains.size();
    }
    public void addTrain(){
        if (this.trainSize()<3){
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

    public void moveTrains(){
        for (Train train: currentTrains){
            train.moveTrain();
        }
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
        if (!this.hasMaxEngines()) {
            this.engineInventory.add(new Engine());
        }
    }

    public void giveRandomUpgrade(){ //Need to come up with more upgrades
        if (!this.hasMaxUpgrades()) {
            this.upgradeInventory.add(new Upgrade());
        }
    }


    public int randomUnstartedGoal () {
        for (int i = 2; i >= 0; i--) {
            if (currentGoals.get(i).getCurrentTrain() != null) {
                return i;
            }
        }
        return -1;
    }

    public Goal discardUnstartedGoal() {
        int randomIndex = randomUnstartedGoal();
        Goal discardedGoal = null;
        if (randomIndex!=-1) {
            discardedGoal = currentGoals.get(randomIndex);
            currentGoals.remove(randomIndex);
        }
        return discardedGoal;
    }

    public int upgradeSize(){
        return upgradeInventory.size();

    }
    public Upgrade discardRandUpgrade()
    {
        //This method removes a random upgrade from the list, provided the player has maximum number
        //of upgrades, and returns it.
        if (this.hasMaxUpgrades())
        {
            int randomIndex=new Random().nextInt(upgradeInventory.size());
            Upgrade discardedUpgrade= upgradeInventory.get(randomIndex);
            upgradeInventory.remove(randomIndex);
            return discardRandUpgrade();

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
            engineInventory.remove(randomIndex);

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
    public ArrayList<Goal> getCurrentGoals(){
        return this.currentGoals;
    }
    public void addGoal(Goal goal){
        if (!hasMaxGoals()){
            currentGoals.add(goal);
        }
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

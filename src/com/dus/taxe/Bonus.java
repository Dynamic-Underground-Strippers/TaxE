package com.dus.taxe;

public class Bonus {
    private final Engine engine;
    private final int points;
    private final Station station;
    private final int turns;

    public Bonus(int points, Engine engine, int turns, Station station) {
        this.points = points;
        this.engine = engine;
        this.turns = turns;
        this.station = station;
    }

    public Engine getEngine() {
        return engine;
    }

    public int getPoints() {
        return points;
    }

    public Station getStation() {
        return station;
    }

    public int getTurns() {
        return turns;
    }
}

package com.dus.taxe;

import java.util.Random;


public class Engine implements Resource, Comparable<Engine> {
	private EngineType type;

	public enum EngineType { //enumerated type containing types of engine
		//EngineType (String name, String description, int speed)
		HAND_CART("Hand Cart", "", 15),
		STEAM("Steam Engine", "", 25),
		DIESEL("Diesel Engine", "", 50),
		ELECTRIC("Electric Engine", "", 75),
		ROCKET("Rocket Engine", "", 100);

		private String description; //description variable internal to enumerated type
		private String name; //name variable internal to enumerated type
		private int speed; //speed variable internal to enumerated type

		private EngineType(String name, String description,
				int speed) { //setting engine type sets names to those defined in enum
			this.name = name;
			this.description = description;
			this.speed = speed;
		}

	}

	public Engine(EngineType type) {
		this.type = type;
	} //generate engine with specified type

	public Engine() { //generate engine with random type
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;
		if (chance < 40) {
			this.type = EngineType.STEAM;
		} else if (chance < 70) {
			this.type = EngineType.DIESEL;
		} else if (chance < 90) {
			this.type = EngineType.ELECTRIC;
		} else {
			this.type = EngineType.ROCKET;
		}
	}

	@Override
	public int compareTo(Engine engine) {
		return (this.getName().compareTo(engine.getName()));
	}

	public String getDescription() {
		return this.type.description;
	}

	public String getName() {
		return this.type.name;
	}

	public int getSpeed() {
		return this.type.speed;
	}

	public EngineType getType() {
		return type;
	}

	public void use(Train train) {
		train.setSpeed(type.speed);
	}
}
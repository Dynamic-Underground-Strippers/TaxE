package com.dus.taxe;

import java.util.Random;

/**
 * Representation of a {@link com.dus.taxe.Train}'s engine
 */
public class Engine implements Resource, Comparable<Engine> {
	private final EngineType type;

	public enum EngineType { //enumerated type containing types of engine
		//EngineType (String name, String description, int speed)
		HAND_CART("Hand Cart", "", 20),
		STEAM("Steam Engine", "", 35),
		DIESEL("Diesel Engine", "", 50),
		ELECTRIC("Electric Engine", "", 75),
		ROCKET("Rocket Engine", "", 100);

		private final String description; //description variable internal to enumerated type
		private final String name; //name variable internal to enumerated type
		private final int speed; //speed variable internal to enumerated type

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

	/**
	 * @return a description of the {@link com.dus.taxe.Engine}
	 */
	public String getDescription() {
		return this.type.description;
	}

	/**
	 * @return the {@link com.dus.taxe.Engine}'s name
	 */
	public String getName() {
		return this.type.name;
	}

	/**
	 * @return the {@link com.dus.taxe.Engine}'s speed
	 */
	public int getSpeed() {
		return this.type.speed;
	}

	/**
	 * @return the {@link com.dus.taxe.Engine}'s type
	 */
	public EngineType getType() {
		return type;
	}

	/**
	 * Uses this {@link com.dus.taxe.Engine} on the given {@link com.dus.taxe.Train}
	 * @param train the {@link com.dus.taxe.Train} to use this {@link com.dus.taxe.Engine} on
	 */
	public void use(Train train) {
		train.setSpeed(type.speed);
	}
}
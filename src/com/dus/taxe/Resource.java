package com.dus.taxe;

/**
 * Base class for all in-game resources
 */
public interface Resource {

	public String getDescription();

	public String getName();

	public void use(Train train);

}
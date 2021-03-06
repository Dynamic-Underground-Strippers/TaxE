package com.dus.taxe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	private Connection[][] connections;
	private ArrayList<Node> listOfNodes;
	private ArrayList<Goal> possibleGoals;

	public Map() {
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add("nodes.json");
		ArrayList<Node> loadedNodes = new ArrayList<Node>();
		ArrayList<ArrayList<Connection>> loadedConnections = new ArrayList<ArrayList<Connection>>();
		ArrayList<Goal> loadedGoals = new ArrayList<Goal>();
		JSONParser parser = new JSONParser();
		try {
			Random rand = new Random();
			Object obj = parser.parse(new InputStreamReader(getClass().getClassLoader()
																	  .getResourceAsStream(fileNames
																			  .get(rand.nextInt(
																					  fileNames
																							  .size())))));
			JSONObject mapList = (JSONObject) obj;
			JSONArray nodeList = (JSONArray) mapList.get("nodes");
			JSONArray connectionList = (JSONArray) mapList.get("connections");
			JSONArray goalList = (JSONArray) mapList.get("goals");

			for (int i = 0; i < nodeList.size(); i++) {
				JSONObject nodeJSON = (JSONObject) nodeList.get(i);
				if (nodeJSON.get("type").toString().equals("Station")) {
					Station node = new Station(i, nodeJSON.get("name").toString(),
							new Point(nodeJSON.get("location").toString()));
					loadedNodes.add(node);
				} else {
					Junction node = new Junction(i, nodeJSON.get("name").toString(),
							new Point(nodeJSON.get("location").toString()));
					loadedNodes.add(node);
				}
			}
			for (int i = 0; i < connectionList.size(); i++) {
				ArrayList<Connection> innerList = new ArrayList<Connection>();
				JSONArray innerArray = (JSONArray) connectionList.get(i);
				for (int x = 0; x < innerArray.size(); x++) {
					if (innerArray.get(x) != null) {
						innerList
								.add(new Connection(Integer.valueOf(innerArray.get(x).toString())));
					} else {
						innerList.add(null);
					}
				}
				loadedConnections.add(innerList);
			}
			this.connections = new Connection[loadedNodes.size()][loadedNodes.size()];
			for (int x = 0; x < loadedNodes.size(); x++) {
				for (int y = 0; y < loadedNodes.size(); y++) {
					this.connections[x][y] = loadedConnections.get(x).get(y);
				}
			}
			this.listOfNodes = loadedNodes;
			for (int i = 0; i < goalList.size(); i++) {
				JSONObject goalJSON = (JSONObject) goalList.get(i);
				Node startNode = null;
				Node endNode = null;
				for (Node node : loadedNodes) {
					if (node.getName().equals(goalJSON.get("start"))) {
						startNode = node;
					}
					if (node.getName().equals(goalJSON.get("end"))) {
						endNode = node;
					}
				}
				Goal tempGoal = new Goal(startNode, endNode);
				loadedGoals.add(tempGoal);
				this.possibleGoals = loadedGoals;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds the distance between the 2 given {@link com.dus.taxe.Node}s
	 *
	 * @param node1
	 * @param node2
	 * @return the distance betwen the 2 given {@link com.dus.taxe.Node}s
	 */
	public int findDistance(Node node1, Node node2) {
		// returns the distance between two nodes if a connection exists, if it doesn't returns 0
		return connections[node1.getId()][node2.getId()].getDistance();


	}

	/**
	 * @return all {@link com.dus.taxe.Connection}s between {@link com.dus.taxe.Node}s
	 */
	public Connection[][] getConnections() {
		return connections;
	}

	/**
	 * @return all {@link com.dus.taxe.Node}s on the {@link com.dus.taxe.Map}
	 */
	public ArrayList<Node> getListOfNodes() {
		return listOfNodes;
	}

	/**
	 * @return a random {@link com.dus.taxe.Node}
	 */
	public Node getRandomNode(){
		int randomIndex = new Random().nextInt(listOfNodes.size());
		while (listOfNodes.get(randomIndex) instanceof Junction){
			randomIndex = new Random().nextInt(listOfNodes.size());
		}
		return listOfNodes.get(randomIndex);
	}

	/**
	 * Returns the {@link com.dus.taxe.Node} with the given index
	 *
	 * @param index the index of the {@link com.dus.taxe.Node}
	 * @return the {@link com.dus.taxe.Node} with the given index
	 */
	public Node retrieveNode(int index) {
		//returns a node based on index;
		return listOfNodes.get(index);
	}
}

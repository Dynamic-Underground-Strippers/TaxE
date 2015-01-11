package com.dus.taxe.gui;

import com.dus.taxe.Map;
import com.dus.taxe.Node;
import com.dus.taxe.Station;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		new Test();
	}

	public Test() {
		GuiController.init(new GUI());
		GuiController.setMap(new Map(load("test_nodes.json"), null, null));}

	private ArrayList<Node> load(String fileName) {
		ArrayList<Node> loadedNodes = new ArrayList<Node>();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new InputStreamReader(getClass().getClassLoader().getResource(fileName).openStream()));
			JSONArray nodeList = (JSONArray) obj;

			for (int i = 0; i < nodeList.size(); i++) {
				JSONObject nodeJSON = (JSONObject) nodeList.get(i);
				Station node = new Station(i, nodeJSON.get("name").toString(), new Point(nodeJSON.get("location").toString()));
				loadedNodes.add(node);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loadedNodes;
	}
}
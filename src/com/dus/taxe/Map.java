package com.dus.taxe;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Map {
    public ArrayList<Node> listOfNodes;
    public Connection[][] connections;
    public ArrayList<Goal> possibleGoals;
    private ArrayList<String> fileNames = new ArrayList<String>();
    public Map(){
        fileNames.add("nodes.json");
        ArrayList<Node> loadedNodes = new ArrayList<Node>();
        ArrayList<ArrayList<Connection>> loadedConnections = new ArrayList<ArrayList<Connection>>();
        ArrayList<Goal> loadedGoals = new ArrayList<Goal>();
        JSONParser parser = new JSONParser();
        try {
            Random rand = new Random();
            Object obj = parser.parse(new FileReader(
                    fileNames.get(rand.nextInt(fileNames.size()))));
            JSONObject mapList = (JSONObject) obj;
            JSONArray nodeList = (JSONArray) mapList.get("nodes");
            JSONArray connectionList = (JSONArray) mapList.get("connections");
            JSONArray goalList = (JSONArray) mapList.get("goals");

            for (int i =0; i<nodeList.size();i++){
                JSONObject nodeJSON = (JSONObject) nodeList.get(i);
                if (nodeJSON.get("type").toString().equals("Station")){
                    Station node = new Station(i,nodeJSON.get("name").toString(),new Point(nodeJSON.get("location").toString()));
                    loadedNodes.add(node);
                } else{
                    Junction node = new Junction(i,nodeJSON.get("name").toString(),new Point(nodeJSON.get("location").toString()));
                    loadedNodes.add(node);
                }
            }
            for (int i =0; i<connectionList.size();i++){
                ArrayList<Connection> innerList = new ArrayList<Connection>();
                JSONArray innerArray = (JSONArray) connectionList.get(i);
                for (int x =0; x<innerArray.size();x++){
                    if (innerArray.get(x)!=null){
                        innerList.add(new Connection(Integer.valueOf(innerArray.get(x).toString())));
                    }else{
                        innerList.add(null);
                    }
                }
                loadedConnections.add(innerList);
            }
            this.connections = new Connection[loadedNodes.size()][loadedNodes.size()];
            for (int x = 0; x<loadedNodes.size();x++){
                for (int y = 0; y<loadedNodes.size();y++){
                    this.connections[x][y] = loadedConnections.get(x).get(y);
                }
            }
            this.listOfNodes = loadedNodes;
            for (int i =0; i<goalList.size();i++){
                JSONObject goalJSON = (JSONObject) goalList.get(i);
                Node startNode=null;
                Node endNode=null;
                for (Node node: loadedNodes){
                    if (node.getName().equals(goalJSON.get("start"))){
                        startNode = node;
                    } if (node.getName().equals(goalJSON.get("end"))){
                        endNode = node;
                    }
                }
                Goal tempGoal = new Goal(Integer.valueOf(goalJSON.get("points").toString()),startNode,endNode);
                loadedGoals.add(tempGoal);
                this.possibleGoals=loadedGoals;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Node> findAdjacentNodes(Node node1){
        // returns a list of all nodes that have a connection with the node passed to the function
        ArrayList<Node> adjacentNodes = new ArrayList<Node>();
        for (int i=0; i<listOfNodes.size(); i++) {
            if (connections[node1.getId()][i] != null) {
                adjacentNodes.add(listOfNodes.get(i));
            }
        }
        return adjacentNodes;
    }

    public int findDistance(Node node1,Node node2){
        // returns the distance between two nodes if a connection exists, if it doesn't returns 0
        return connections[node1.getId()][node2.getId()].getDistance();


    }

    public Node retrieveNode(int index){
        //returns a node based on index;
        return listOfNodes.get(index);

    }

}

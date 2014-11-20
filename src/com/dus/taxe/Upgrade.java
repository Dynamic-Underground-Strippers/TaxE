package com.dus.taxe;

import java.util.ArrayList;

public class Upgrade extends Resource {
    private UpgradeType type;

    public enum UpgradeType {
        doubleSpeed, //apply to trains
        //engineer, //apply to trains should be implemented with obstacles
        teleport //, // use on train, modifies route and current node
        //obstacle,
        //removeObstacle,

        ;


    }

    public Upgrade(UpgradeType type) {
        this.setType(type);

        String name = "";
        String description = "";

        switch (type){
            case doubleSpeed:
                name = "Double Speed";
                description = "This upgrade doubles the speed of one of your trains!";
                break;
            /* should be implemented with obstacles
            case engineer:
                name = "Engineer";
                description = "Carrying an engineer allows you to instantly repair an obstacle";
                //code
                break;
            */
            case teleport:
                name = "Teleport";
                description = "Brings a train to a Station instantly";
                //code
                break;
        }
        super.setName(name);
        super.setDescription(description);
    }

    public UpgradeType getType() {
        return type;
    }

    public void setType(UpgradeType type) {
        this.type = type;
    }

    /*
    *
    * useUpgrade allows to use an upgrade.
    *
    * if called with a train object as parameter allows to apply modifiers to trains
    *
    * if called with a train object and a node as parameters allows to warp a train to a node given that the node is contained in the route
    *
    * if called with a node or a connection as parameter allows to use obstacles related upgrades
    *
    * */

     public void useUpgrade(Train train){
        switch (type){
            case doubleSpeed:
                if(train.hasUpgrade("doubleSpeed")){
                    //Throw exception
                }
                train.setSpeed(train.getSpeed()*2);
                break;
            /*case engineer:
                if(train.hasUpgrade("engineer")){
                    //Throw exception
                }
                train.setEngineer(true);
                break;*/
        }

    }

    public void useUpgrade(Train train, Node node){
        if (this.type == UpgradeType.teleport){
            if(train.route.nodes.contains(node)) { //can teleport only on nodes contained in the route.
                train.setCurrentNode(node);
            }else{
                //throw exception
            }
        }else{
            //throw exception
        }

    }



    /*
    public void useUpgrade(Object node){
        node.createObstacle();
    }
    */
}

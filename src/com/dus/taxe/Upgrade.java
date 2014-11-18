package com.dus.taxe;

public class Upgrade extends Resource {
    private UpgradeType type;

    public enum UpgradeType {
        doubleSpeed, //apply to trains
        Engineer, //apply to trains
        Teleport, // use on train, modifies route and current node
        Obstacle,
        removeObstacle,

        ;


    }

    public Upgrade(UpgradeType type) {
        this.type = type;

        String name = "";
        String description = "";

        switch (type){
            case doubleSpeed:
                name = "";
                description = "";
                //code
                break;
            case Engineer:
                name = "";
                description = "";
                //code
                break;
            case Teleport:
                name = "";
                description = "";
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

    public void useUpgrade(Train train){
        switch (type){
            case doubleSpeed:
                train.setSpeed(train.getSpeed()*2);
                break;
            case Engineer:
                if(!train.haveEngineer()) {
                    train.setEngineer(True);
                }
                else {
                    //throw exception
                }
                break;
        }

    }

    public void useUpgrade(Train train, Node node){
        if (this.type == UpgradeType.Teleport){
            if(train.route.nodes.contains(node)) { //can teleport only on nodes contained in the route.
                train.setCurrentNode(node);
                //need to modify route too
            }
        }else{
            //throw exception
        }

    }

    public void useUpgrade(Object node){
        node.createObstable();

    }

}

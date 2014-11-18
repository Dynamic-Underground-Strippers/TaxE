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

    }

    public void useUpgrade(Train train, Node node){
        if (this.type == UpgradeType.Teleport){
            //code
        }else{
            //throw exception
        }

    }

    public void useUpgrade(Object node){
        node.createObstable();

    }

}

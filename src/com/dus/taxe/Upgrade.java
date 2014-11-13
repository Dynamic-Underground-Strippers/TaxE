package com.dus.taxe;

public class Upgrade extends Resource {
    private UpgradeType type;

    enum UpgradeType {}

    public Upgrade(String name, String description, UpgradeType type) {
        super(name, description);
        this.type = type;
    }

    public UpgradeType getType() {
        return type;
    }

    public void setType(UpgradeType type) {
        this.type = type;
    }
}

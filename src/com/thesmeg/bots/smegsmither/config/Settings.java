package com.thesmeg.bots.smegsmither.config;

public class Settings {

    private String barToSmelt;
    private boolean userConfigSet = false;

    public String getBarToSmelt() {
        return barToSmelt;
    }

    public void setBarToSmelt(String barToSmelt) {
        this.barToSmelt = barToSmelt;
    }

    public boolean getUserConfigSet() {
        return userConfigSet;
    }

    public void setUserConfigSet(boolean userConfigSet) {
        this.userConfigSet = userConfigSet;
    }
}

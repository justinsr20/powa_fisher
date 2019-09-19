package com.thesmeg.bots.smegsmither.config;

import com.runemate.game.api.hybrid.util.Timer;
import com.runemate.game.api.hybrid.util.calculations.Random;


public class Settings {

    private String barToSmelt;
    private String locationToSmelt;
    private boolean userConfigSet = false;
    private Timer timer;

    public long getRandomPlayTime() {
        return Random.nextLong(900000, 3600000);
    }

    public long getRandomBreakTime() {
        return Random.nextLong(300000,900000);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void startTimer() {
        timer.start();
    }

    public String getBarToSmelt() {
        return barToSmelt;
    }

    public void setBarToSmelt(String barToSmelt) {
        this.barToSmelt = barToSmelt;
    }

    public String getLocationToSmelt() {
        return locationToSmelt;
    }

    public void setLocationToSmelt(String locationToSmelt) {
        this.locationToSmelt = locationToSmelt;
    }

    public boolean getUserConfigSet() {
        return userConfigSet;
    }

    public void setUserConfigSet(boolean userConfigSet) {
        this.userConfigSet = userConfigSet;
    }
}

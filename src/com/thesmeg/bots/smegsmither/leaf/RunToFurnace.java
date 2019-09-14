package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class RunToFurnace extends LeafTask {
    private SmegSmither smegSmither;

    public RunToFurnace(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
        String locationToSmelt = smegSmither.settings.getLocationToSmelt();
        Area furnaceArea = smegSmither.data.getLocationArea(locationToSmelt, "Furnace");
        smegSmither.lib.webPathToDestination(furnaceArea.getRandomCoordinate(), locationToSmelt + " Furnace", getLogger());
    }
}

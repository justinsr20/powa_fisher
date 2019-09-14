package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class RunToBank extends LeafTask {
    private SmegSmither smegSmither;

    public RunToBank(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
        String locationToSmelt = smegSmither.settings.getLocationToSmelt();
        Area bankArea = smegSmither.data.getLocationArea(locationToSmelt, "Bank");
        smegSmither.lib.webPathToDestination(bankArea.getRandomCoordinate(), locationToSmelt + " Bank", getLogger());
    }
}

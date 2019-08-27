package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class RunToFurnace extends LeafTask {
    private SmegSmither smegSmither;

    public RunToFurnace(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
        Area furnace = smegSmither.data.getEdgevilleFurnace();
        if (!furnace.isVisible()) {
            //@TODO make variable
            smegSmither.lib.webPathToDestination(furnace.getRandomCoordinate(), "Edgeville Furnace", getLogger());
        }
    }
}

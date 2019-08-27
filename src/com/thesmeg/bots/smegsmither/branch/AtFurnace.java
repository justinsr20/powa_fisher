package com.thesmeg.bots.smegsmither.branch;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class AtFurnace extends BranchTask {
    private SmegSmither smegSmither;

    public AtFurnace(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public boolean validate() {
        //TODO make location variable
        Area furnaceArea = smegSmither.data.getEdgevilleFurnace();
        GameObject furnace = GameObjects.newQuery().names("Furnace").within(furnaceArea).results().first();
        try {
            if (furnace.isVisible()) {
                return true;
            }
        } catch (NullPointerException ignored) {

        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return smegSmither.smelt;
    }

    @Override
    public TreeTask failureTask() {
        return smegSmither.runToFurnace;
    }
}

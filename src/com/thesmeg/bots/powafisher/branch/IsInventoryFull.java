package com.thesmeg.bots.powafisher.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.leaf.DropFish;

public class IsInventoryFull extends BranchTask {

    private DropFish dropFish = new DropFish();
    private IsNearFishSpot isNearFishSpot = new IsNearFishSpot();

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return isNearFishSpot;
    }

    @Override
    public TreeTask successTask() {
        return dropFish;
    }
}

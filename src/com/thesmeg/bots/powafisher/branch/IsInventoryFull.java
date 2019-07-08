package com.thesmeg.bots.powafisher.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.leaf.DropFish;

public class IsInventoryFull extends BranchTask {

    private DropFish dropfish = new DropFish();
    private IsNearFishSpot isnearfishspot = new IsNearFishSpot();

    @Override
    public boolean validate() {
        return Inventory.containsAnyExcept("Small fishing net");
    }

    @Override
    public TreeTask failureTask() {
        return isnearfishspot;
    }

    @Override
    public TreeTask successTask() {
        return dropfish;
    }
}

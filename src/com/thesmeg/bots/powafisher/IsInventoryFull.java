package com.thesmeg.bots.powafisher;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.branch.DropFish;
import com.thesmeg.bots.powafisher.branch.IsNearFishSpot;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsInventoryFull extends BranchTask {

    private DropFish dropfish = new DropFish();
    private IsNearFishSpot isnearfishspot = new IsNearFishSpot();

    @Override
    public boolean validate() {
        return Inventory.getEmptySlots() <= 27;
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

package com.thesmeg.bots.powafisher.branch;


import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.leaf.Fish;
import com.thesmeg.bots.powafisher.leaf.Logout;


/**
 * NOTES:
 */
public class IsNearFishSpot extends BranchTask {

    private Fish fish = new Fish();
    private Logout logout = new Logout();

    private Npc fishingSpot;

    @Override
    public boolean validate() {
        fishingSpot = Npcs.newQuery().names("Fishing spot").actions("Net").results().nearest();
        if (fishingSpot != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public TreeTask failureTask() {
        return logout;
    }

    @Override
    public TreeTask successTask() {
        return fish;
    }
}

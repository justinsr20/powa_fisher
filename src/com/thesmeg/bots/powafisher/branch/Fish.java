package com.thesmeg.bots.powafisher.branch;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.leaf.DropFish;
import com.thesmeg.bots.powafisher.leaf.Logout;

/**
 * NOTES:
 */
public class Fish extends BranchTask {

    private DropFish dropFish = new DropFish();
    private Logout logout = new Logout();

    Player player;
    Npc fishingSpot;

    @Override
    public boolean validate() {
        player = Players.getLocal();
        fishingSpot = Npcs.newQuery().names("Fishing spot").actions("Net").results().nearest();

        if (fishingSpot != null) {
            if (fishingSpot.interact("Net")) {
                Execution.delayUntil(() -> Inventory.isFull(), () -> player.getAnimationId() != -1, 50, 1000, 2000);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TreeTask successTask() {
        return dropFish;
    }

    @Override
    public TreeTask failureTask() {
        return logout;
    }
}

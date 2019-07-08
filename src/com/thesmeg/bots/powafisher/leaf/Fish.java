package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.Execution;

/**
 * NOTES:
 */
public class Fish extends LeafTask {

    Player player;
    Npc fishingSpot;

    @Override
    public void execute() {
        player = Players.getLocal();
        fishingSpot = Npcs.newQuery().names("Fishing spot").actions("Net").results().nearest();
        if (fishingSpot.interact("Net")) {
            Execution.delayUntil(() -> Inventory.isFull(), () -> player.getAnimationId() != -1, 50, 1000, 2000);
        }
    }
}

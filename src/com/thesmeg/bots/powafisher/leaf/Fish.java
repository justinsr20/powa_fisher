package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 */
public class Fish extends LeafTask {
    private Player player;


    @Override
    public void execute() {
        int animation_id = Players.getLocal().getAnimationId();
        if (animation_id == -1) {
            Npcs.newQuery().names("Fishing spot").actions("Net").results().nearest().click();
        }
    }
}

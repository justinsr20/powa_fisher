package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 */
public class Fish extends LeafTask {

    @Override
    public void execute() {
        Npcs.newQuery().names("Fishing spot").actions("Net").results().nearest().click();
    }
}

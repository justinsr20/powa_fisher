package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES:
 */
public class DropFish extends LeafTask {
//    String[] to_drop = {"Raw shrimps", "Anchovies"};

    @Override
    public void execute() {
        SpriteItemQueryResults shrimp_to_drop = Inventory.newQuery().names("Raw shrimps").results();
        if (shrimp_to_drop.size() > 0) {
            shrimp_to_drop.first().interact("Drop");
        }
    }

}

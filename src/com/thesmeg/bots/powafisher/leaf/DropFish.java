package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES:
 */
public class DropFish extends LeafTask {

    @Override
    public void execute() {
        SpriteItemQueryResults shrimpToDrop = Inventory.newQuery().names("Raw shrimps").results();
        SpriteItemQueryResults anchoviesToDrop = Inventory.newQuery().names("Anchovies").results();
        if (shrimpToDrop.size() > 0) {
            shrimpToDrop.first().interact("Drop");
        }
        if (anchoviesToDrop.size() > 0) {
            anchoviesToDrop.first().interact("Drop");
        }
    }

}

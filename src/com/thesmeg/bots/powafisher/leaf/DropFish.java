package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.awt.event.KeyEvent;


/**
 * NOTES:
 */
public class DropFish extends LeafTask {

    @Override
    public void execute() {
        SpriteItemQueryResults shrimpToDrop = Inventory.newQuery().names("Raw shrimps").results();
        SpriteItemQueryResults anchoviesToDrop = Inventory.newQuery().names("Raw anchovies").results();
        //TODO make sure this setting is enabled otherwise bot will bug out
        Keyboard.pressKey(KeyEvent.VK_SHIFT);
        if (shrimpToDrop.size() > 0) {
            shrimpToDrop.first().click();
        }
        if (anchoviesToDrop.size() > 0) {
            anchoviesToDrop.first().click();
        }
        Keyboard.releaseKey(KeyEvent.VK_SHIFT);
    }

}

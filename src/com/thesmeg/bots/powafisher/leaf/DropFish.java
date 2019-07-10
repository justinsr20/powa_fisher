package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.awt.event.KeyEvent;
import java.util.List;


/**
 * NOTES:
 */
public class DropFish extends LeafTask {

    @Override
    public void execute() {
        List<SpriteItem> listOfItems = Inventory.getItems().asList();

        //TODO make sure this setting is enabled otherwise bot will bug out
        Keyboard.pressKey(KeyEvent.VK_SHIFT);
        listOfItems.forEach(item -> {
            ItemDefinition itemDefinition = item.getDefinition();
            if (!itemDefinition.getName().equals("Small fishing net")) {
                item.click();
            }
        });
        Keyboard.releaseKey(KeyEvent.VK_SHIFT);
    }
}

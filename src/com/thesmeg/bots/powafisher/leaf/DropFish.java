package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.awt.event.KeyEvent;
import java.util.List;


/**
 * NOTES:
 */
public class DropFish extends LeafTask {

    @Override
    public void execute() {
        if (Inventory.isFull()) {
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
            //TODO found bug when is last item in inventory is selected it tries to use it on fishing spot
            if (Inventory.getSelectedItem() != null) {
                Inventory.getSelectedItem().click();
            }
        }
    }
}

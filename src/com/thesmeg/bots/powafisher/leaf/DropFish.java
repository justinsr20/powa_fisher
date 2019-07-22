package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.osrs.local.hud.interfaces.OptionsTab;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;


/**
 * NOTES:
 */
public class DropFish extends LeafTask {

    List<String> fishingGear = Arrays.asList("Small fishing net", "Fishing rod", "Fly fishing rod", "Lobster pot",
            "Harpoon", "Fishing bait", "Feather", "Coins");

    @Override
    public void execute() {
        if (!OptionsTab.isShiftDroppingEnabled()) {
            OptionsTab.setShiftDropping(true);
        }

        if (Inventory.isFull()) {
            List<SpriteItem> listOfItems = Inventory.getItems().asList();
            Keyboard.pressKey(KeyEvent.VK_SHIFT);
            listOfItems.forEach(item -> {
                ItemDefinition itemDefinition = item.getDefinition();
                if (!fishingGear.contains(itemDefinition.getName())) {
                    item.click();
                }
            });
            Keyboard.releaseKey(KeyEvent.VK_SHIFT);
        }
    }
}

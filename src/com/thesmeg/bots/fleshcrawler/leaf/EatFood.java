package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatFood extends LeafTask {
    @Override
    public void execute() {
        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }
        getLogger().info("Low health, attempting to eat");
        Inventory.getItems("Pike").first().click();
    }
}

package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatFood extends LeafTask {
    @Override
    public void execute() {
        Inventory.getItems("Pike").last().click();
    }
}

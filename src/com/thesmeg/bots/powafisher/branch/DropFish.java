package com.thesmeg.bots.powafisher.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 */
public class DropFish extends BranchTask {


    @Override
    public boolean validate() {
        //@TODO hold down shift as .click() is left click
        Boolean shrimp_to_drop = Inventory.newQuery().names("Raw shrimps").actions("Drop").results().first().click();
        return shrimp_to_drop;
    }

    @Override
    public TreeTask failureTask() {
        return null;
    }

    @Override
    public TreeTask successTask() {
        return null;
    }
}

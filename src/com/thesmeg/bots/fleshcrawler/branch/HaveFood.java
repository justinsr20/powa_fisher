package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.leaf.EatFood;
import com.thesmeg.bots.fleshcrawler.leaf.TeleportToVarrock;

public class HaveFood extends BranchTask {

    String foodName = "Pike";

    @Override
    public boolean validate() {
        if (Inventory.contains(foodName)) {
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new EatFood();
    }

    @Override
    public TreeTask failureTask() {
        return new TeleportToVarrock();
    }
}

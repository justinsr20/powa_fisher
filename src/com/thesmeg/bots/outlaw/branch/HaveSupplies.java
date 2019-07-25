package com.thesmeg.bots.outlaw.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveSupplies extends BranchTask {

    String foodToEat = "Pike";

    @Override
    public boolean validate() {
        if (Inventory.contains(foodToEat)) {
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new InOutlawArea();
    }

    @Override
    public TreeTask failureTask() {
        return new InBankArea();
    }
}

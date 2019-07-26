package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveSupplies extends BranchTask {

    String foodToEat = "Tuna";

    @Override
    public boolean validate() {
        if (Inventory.contains(foodToEat)) {
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new InFleshCrawlerArea();
    }

    @Override
    public TreeTask failureTask() {
        return new InBankArea();
    }
}

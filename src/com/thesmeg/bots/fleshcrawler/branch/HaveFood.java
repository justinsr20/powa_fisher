package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class HaveFood extends BranchTask {

    private FleshCrawler fleshCrawler;

    public HaveFood(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

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
        return fleshCrawler.eatFood;
    }

    @Override
    public TreeTask failureTask() {
        return fleshCrawler.teleportToVarrock;
    }
}

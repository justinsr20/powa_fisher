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

    @Override
    public boolean validate() {
        return Inventory.contains(fleshCrawler.getFoodToEat());
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

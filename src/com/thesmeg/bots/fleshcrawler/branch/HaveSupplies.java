package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

import java.util.Map;

public class HaveSupplies extends BranchTask {
    private FleshCrawler fleshCrawler;

    public HaveSupplies(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public boolean validate() {
        for (Map.Entry<String, Integer> item : fleshCrawler.requiredItems.entrySet()) {
            if (!Inventory.contains(item.getKey())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public TreeTask successTask() {
        return fleshCrawler.inFleshCrawlerArea;
    }

    @Override
    public TreeTask failureTask() {
        return fleshCrawler.inBankArea;
    }
}

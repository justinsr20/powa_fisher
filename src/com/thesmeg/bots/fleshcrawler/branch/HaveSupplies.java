package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

import java.util.Arrays;
import java.util.List;

public class HaveSupplies extends BranchTask {
    private FleshCrawler fleshCrawler;

    public HaveSupplies(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    private List<String> requiredItems = Arrays.asList("Pike", "Fire rune", "Law rune", "Air rune");

    @Override
    public boolean validate() {
        for (String item : requiredItems) {
            if (!Inventory.contains(item)) {
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

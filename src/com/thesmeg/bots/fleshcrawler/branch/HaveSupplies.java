package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.Arrays;
import java.util.List;

public class HaveSupplies extends BranchTask {

    List<String> requiredItems = Arrays.asList("Tuna", "Fire rune", "Law rune", "Air rune");

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
        return new InFleshCrawlerArea();
    }

    @Override
    public TreeTask failureTask() {
        return new InBankArea();
    }
}

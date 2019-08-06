package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.leaf.GetSupplies;

import java.util.Map;

public class HaveSupplies extends BranchTask {
    private GetSupplies getSupplies = new GetSupplies();

    @Override
    public boolean validate() {
        for (Map.Entry<String, Integer> item : getSupplies.requiredItems().entrySet()) {
            if (!Inventory.contains(item.getKey()) || Inventory.getQuantity(item.getKey()) != item.getValue()) {
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

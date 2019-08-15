package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class LootAvailable extends BranchTask {

    private FleshCrawler fleshCrawler;

    public LootAvailable(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public boolean validate() {
        LocatableEntityQueryResults<GroundItem> itemsOnGround = GroundItems.getLoadedWithin(fleshCrawler.fleshCrawlerArea);
        for (GroundItem item : itemsOnGround) {
            if (fleshCrawler.itemsToLoot.contains(item.getDefinition().getName())) {
                if (item.getPosition() != null && item.getPosition().isReachable()) {
                    if (Inventory.contains(item.getDefinition().getName()) && item.getQuantity() > 1) {
                        getLogger().info("Stackable item " + item + " found on ground, attempting to loot");
                        return true;
                    } else if (!Inventory.isFull()) {
                        getLogger().info("Unstackable item " + item + " found on ground, attempting to loot");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return fleshCrawler.loot;
    }

    @Override
    public TreeTask failureTask() {
        return fleshCrawler.fight;
    }
}

package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.leaf.Fight;
import com.thesmeg.bots.fleshcrawler.leaf.Loot;

public class LootAvailable extends BranchTask {
    InFleshCrawlerArea inFleshCrawlerArea = new InFleshCrawlerArea();
    Loot loot = new Loot();

    @Override
    public boolean validate() {
        LocatableEntityQueryResults<GroundItem> itemsOnGround = GroundItems.getLoadedWithin(inFleshCrawlerArea.fleshCrawlerArea);
        for (GroundItem item : itemsOnGround) {
            if (loot.itemsToLoot.contains(item.getDefinition().getName())) {
                if (item.getPosition() != null && item.getPosition().isReachable()) {
                    if (Inventory.contains(item.getDefinition().getName()) && item.getQuantity() > 1) {
//                        getLogger().info("Stackable item " + item + " found on ground, attempting to loot");
                        return true;
                    } else if (!Inventory.isFull()) {
//                        getLogger().info("Unstackable item " + item + " found on ground, attempting to loot");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new Loot();
    }

    @Override
    public TreeTask failureTask() {
        return new Fight();
    }
}

package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.GroundItemQueryBuilder;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class Loot extends LeafTask {
    private FleshCrawler fleshCrawler;

    public Loot(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        try {
            if (!Players.getLocal().isMoving() && Players.getLocal().getTarget() == null) {
                for (String itemName : fleshCrawler.itemsToLoot) {
                    GroundItem itemToClick = new GroundItemQueryBuilder().names(itemName).within(fleshCrawler.fleshCrawlerArea).results().nearest();
                    if (itemToClick != null) {
                        if (itemToClick.getQuantity() > 1 && Inventory.contains(itemToClick.getDefinition().getName())) {
                            if (itemToClick.interact("Take")) {
                                Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                                break;
                            }
                        } else if (!Inventory.isFull()) {
                            if (itemToClick.interact("Take")) {
                                Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}

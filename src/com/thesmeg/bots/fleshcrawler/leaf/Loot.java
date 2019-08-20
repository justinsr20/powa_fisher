package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.GroundItemQueryBuilder;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
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
                LocatableEntityQueryResults<GroundItem> itemsOnGround = new GroundItemQueryBuilder().within(fleshCrawler.fleshCrawlerArea).results().sortByDistance();
                for (GroundItem item : itemsOnGround) {
                    if (fleshCrawler.itemsToLoot.contains(item.getDefinition().getName()) && item.getPosition().isReachable()) {
                        if (item.getQuantity() > 1 && Inventory.contains(item.getDefinition().getName())) {
                            if (item.interact("Take")) {
                                Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                                return;
                            }
                        } else if (!Inventory.isFull()) {
                            if (item.interact("Take")) {
                                Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                                return;
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}

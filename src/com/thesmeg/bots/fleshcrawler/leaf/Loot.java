package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.GroundItemQueryBuilder;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import com.thesmeg.bots.fleshcrawler.playersense.CustomPlayerSense;

public class Loot extends LeafTask {
    private FleshCrawler fleshCrawler;

    public Loot(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();
        final int executionDelayMin = CustomPlayerSense.Key.EXECUTION_DELAY_MIN.getAsInteger();
        final int executionDelayMax = CustomPlayerSense.Key.EXECUTION_DELAY_MAX.getAsInteger();
        GroundItem itemToLoot = new GroundItemQueryBuilder().within(fleshCrawler.fleshCrawlerArea).filter(
                item -> fleshCrawler.itemsToLoot.contains(item.getDefinition().getName())).results().sortByDistance().first();
        Player player = Players.getLocal();

        if (player != null && !player.isMoving() && player.getTarget() == null && itemToLoot != null) {
            if (fleshCrawler.itemsToLoot.contains(itemToLoot.getDefinition().getName()) && itemToLoot.getPosition().isReachable()) {
                if (itemToLoot.getQuantity() > 1 && Inventory.contains(itemToLoot.getDefinition().getName())) {
                    for (int i = 0; i < clicks; i++) {
                        itemToLoot.interact("Take");
                    }
                    Execution.delayUntil(() -> player.isMoving(), () -> false, 50, executionDelayMin, executionDelayMax);
                } else if (!Inventory.isFull()) {
                    for (int i = 0; i < clicks; i++) {
                        itemToLoot.interact("Take");
                    }
                    Execution.delayUntil(() -> player.isMoving(), () -> false, 50, executionDelayMin, executionDelayMax);
                }
            }
        }
    }
}

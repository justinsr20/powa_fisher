package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.GroundItemQueryBuilder;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.branch.InFleshCrawlerArea;

import java.util.Arrays;
import java.util.List;

public class Loot extends LeafTask {
    InFleshCrawlerArea inFleshCrawlerArea = new InFleshCrawlerArea();
    public List<String> itemsToLoot = Arrays.asList("Shield left half", "Dragon spear", "Rune spear", "Tooth half of key",
            "Loop half of key", "Uncut diamond", "Uncut ruby", "Rune javelin", "Uncut emerald",
            "Nature talisman", "Uncut sapphire", "Iron ore", "Coins", "Body rune", "Iron arrow");

    @Override
    public void execute() {
        if (Players.getLocal().getTarget() == null && !Players.getLocal().isMoving()) {
            for (String itemName : itemsToLoot) {
                GroundItem itemToClick = new GroundItemQueryBuilder().names(itemName).within(inFleshCrawlerArea.fleshCrawlerArea).results().nearest();
                if (itemToClick != null) {
                    if (Inventory.getQuantity(itemName) > 1) {
                        itemToClick.interact("Take");
                    } else if (!Inventory.isFull()) {
                        itemToClick.interact("Take");
                    }
                    Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 500, 1500);
                }
            }
        }
    }
}

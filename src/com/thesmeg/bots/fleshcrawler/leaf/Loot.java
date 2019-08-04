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
    List<String> itemsToLoot = Arrays.asList("Shield left half", "Dragon spear", "Rune spear", "Tooth half of key",
            "Loop half of key", "Uncut diamond", "Uncut ruby", "Rune javelin", "Uncut emerald",
            "Nature talisman", "Uncut sapphire", "Iron ore", "Coins", "Body rune");

    @Override
    public void execute() {
        //@todo create pickup method for stackables
        if (Players.getLocal().getTarget() == null && !Players.getLocal().isMoving()) {
            for (String itemName : itemsToLoot) {
                GroundItem itemToClick = new GroundItemQueryBuilder().names(itemName).within(inFleshCrawlerArea.fleshCrawlerArea).results().nearest();
                if (itemToClick != null) {
                    if (!Inventory.isFull()) {
                        itemToClick.interact("Take");
                        Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                    }
                }
            }
            if (Inventory.contains("Coins") && Inventory.contains("Body rune")) {
                GroundItem coins = new GroundItemQueryBuilder().names("Coins").within(inFleshCrawlerArea.fleshCrawlerArea).results().nearest();
                GroundItem bodyRune = new GroundItemQueryBuilder().names("Body rune").within(inFleshCrawlerArea.fleshCrawlerArea).results().nearest();
                if (coins != null) {
                    coins.click();
                    Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                }
                if (bodyRune != null) {
                    bodyRune.click();
                    Execution.delayUntil(() -> Players.getLocal().isMoving(), () -> false, 50, 1000, 2000);
                }
            }
        }
    }
}

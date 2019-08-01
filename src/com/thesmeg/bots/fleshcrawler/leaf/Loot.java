package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.queries.GrandExchangeQueryBuilder;
import com.runemate.game.api.hybrid.queries.GroundItemQueryBuilder;
import com.runemate.game.api.hybrid.region.Players;
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
        for (String itemName : itemsToLoot) {
            GroundItem itemToClick = new GroundItemQueryBuilder().names(itemName).within(inFleshCrawlerArea.fleshCrawlerArea).results().nearest();
            if (itemToClick != null && Players.getLocal().getAnimationId() == -1) {
                itemToClick.interact("Take");
            }
        }

    }
}

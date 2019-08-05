package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Fight extends LeafTask {

    Boolean useRanged = true;

    @Override
    public void execute() {
        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }

        if (useRanged) {
            if (Equipment.getItems("Iron arrow").isEmpty()) {
                if (Inventory.contains("Iron arrow")) {
                    Inventory.getItems("Iron arrow").first().click();
                } else {
                    Environment.getBot().stop("Run out of arrows");
                }
            }
        }

        //@todo filter on things targeting me & attack by farthest when using range
        LocatableEntityQueryResults<Npc> nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").results().sortByDistance();
        for (Npc fleshCrawler : nearestFleshCrawler) {
            if (Players.getLocal().getTarget() == null && !Players.getLocal().isMoving() && Players.getLocal().getAnimationId() != 390) {
                if (fleshCrawler.getTarget() == null && fleshCrawler.getAnimationId() != 1184 && fleshCrawler.getAnimationId() != 1186
                        && fleshCrawler.getAnimationId() != 1190) {
                    if (fleshCrawler.interact("Attack")) {
                        getLogger().info("Attacked Flesh Crawler");
                        Execution.delayUntil(() -> Players.getLocal().getAnimationId() == 1156,
                                () -> Players.getLocal().getTarget() != null, 50, 1000, 2000);
                        break;
                    }
                }
            }
        }

    }
}

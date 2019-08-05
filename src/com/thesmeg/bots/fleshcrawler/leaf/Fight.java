package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Fight extends LeafTask {

    Boolean useRanged = true;

    @Override
    public void execute() {
        //@todo randomize and player sense
        if (Traversal.getRunEnergy() > 20) {
            if (!Traversal.isRunEnabled()) {
                Traversal.toggleRun();
            }
        }
        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }

        if (useRanged) {
            if (Equipment.getItems("Iron arrow").isEmpty()) {
                if (Inventory.contains("Iron arrow")) {
                    Inventory.getItems("Iron arrow").first().click();
                } else {
                    Environment.getBot().stop("Ran out of arrows");
                }
            }
        }

        //@todo Attack by farthest when using range
        LocatableEntityQueryResults<Npc> nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").results().sortByDistance();
        for (Npc flesh : nearestFleshCrawler) {
            if (flesh.getTarget() != null) {
                if (flesh.getTarget().equals(Players.getLocal())) {
                    if (Players.getLocal().getTarget() != null && Players.getLocal().getTarget().equals(flesh)) {
                        break;
                    } else {
                        flesh.interact("Attack");
                        break;
                    }
                }
            } else if (Players.getLocal().getTarget() == null) {
                getLogger().info("Attacking Flesh Crawler");
                if (flesh.getAnimationId() != 1190 && flesh.getAnimationId() != 1184 && flesh.getAnimationId() != 1186) {
                    flesh.interact("Attack");
                    break;
                }
            }
        }
    }
}

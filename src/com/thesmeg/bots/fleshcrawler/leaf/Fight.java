package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import com.thesmeg.bots.fleshcrawler.playersense.CustomPlayerSense;

public class Fight extends LeafTask {

    private FleshCrawler fleshCrawler;

    public Fight(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        final int minRunEnergy = CustomPlayerSense.Key.MIN_RUN_ENERGY.getAsInteger();
        if (Traversal.getRunEnergy() > Random.nextInt(minRunEnergy)) {
            if (!Traversal.isRunEnabled()) {
                Traversal.toggleRun();
            }
        }

        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }

        Player player = Players.getLocal();
        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();
        LocatableEntityQueryResults<Npc> nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").within(fleshCrawler.fleshCrawlerArea).results().sortByDistance();
        for (Npc flesh : nearestFleshCrawler) {
            if (flesh != null && player != null) {
                Actor fleshTarget = flesh.getTarget();
                Actor playerTarget = player.getTarget();
                if (fleshTarget != null && playerTarget != null) {
                    if (playerTarget.equals(flesh) && fleshTarget.equals(player)) {
                        getLogger().info("In combat");
                        return;
                    } else if (player.getAnimationId() != 426 && fleshTarget.equals(player)) {
                        getLogger().info("Attacking Flesh Crawler targeting me");
                        if (flesh.getAnimationId() != 1190) {
                            for (int i = 0; i < clicks; i++) {
                                flesh.interact("Attack");
                            }
                            return;
                        }
                    }
                } else if (player.getAnimationId() != 426 && playerTarget == null && fleshTarget == null) {
                    if (flesh.getAnimationId() != 1190) {
                        getLogger().info("Attacking closest Flesh Crawler");
                        for (int i = 0; i < clicks; i++) {
                            flesh.interact("Attack");
                        }
                        return;
                    }
                }
            }
        }
    }
}

package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
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

        if (fleshCrawler.useRange) {
            if (Equipment.getItems(fleshCrawler.getAmmunitionName()).isEmpty()) {
                if (Inventory.contains(fleshCrawler.getAmmunitionName())) {
                    Inventory.getItems(fleshCrawler.getAmmunitionName()).first().click();
                } else {
                    Environment.getBot().stop("Ran out of arrows");
                }
            }
        }

        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();
        final int executionDelayMin = CustomPlayerSense.Key.EXECUTION_DELAY_MIN.getAsInteger();
        final int executionDelayMax = CustomPlayerSense.Key.EXECUTION_DELAY_MAX.getAsInteger();
        LocatableEntityQueryResults<Npc> nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").within(fleshCrawler.fleshCrawlerArea).results().sortByDistance();
        Player p = Players.getLocal();
        try {
            for (Npc flesh : nearestFleshCrawler) {
                if (flesh != null && p != null) {
                    if (flesh.getTarget() != null && p.getTarget() != null && p.getTarget().equals(flesh) && flesh.getTarget().equals(p)) {
                        getLogger().info("In combat");
                        return;
                    } else if (p.getAnimationId() != 426 && flesh.getTarget() != null && flesh.getTarget().equals(p)) {
                        getLogger().info("Attacking Flesh Crawler targeting me");
                        if (flesh.getAnimationId() != 1190) {
                            for (int i = 0; i < clicks; i++) {
                                flesh.interact("Attack");
                            }
                            Execution.delayUntil(() -> p.getTarget().equals(flesh), () -> false, 50, executionDelayMin, executionDelayMax);
                            return;
                        }
                    } else if (p.getAnimationId() != 426 && p.getTarget() == null) {
                        if (flesh.getAnimationId() != 1190) {
                            getLogger().info("Attacking closest Flesh Crawler");
                            for (int i = 0; i < clicks; i++) {
                                flesh.interact("Attack");
                            }
                            Execution.delayUntil(() -> p.getTarget().equals(flesh), () -> false, 50, executionDelayMin, executionDelayMax);
                            return;
                        }
                    }
                }
            }
        } catch (NullPointerException ignore) {
        }
    }
}

package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.branch.InFleshCrawlerArea;

public class Fight extends LeafTask {
    private static Integer baseXp;

    @Override
    public void execute() {
        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }

        //@todo implement whats targeting me
//        LocatableEntityQueryResults<Npc> fleshCrawler1TargetingMe = Npcs.newQuery().targeting(Players.getLocal()).results();

        LocatableEntityQueryResults<Npc> nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").results().sortByDistance();
        for (Npc fleshCrawler : nearestFleshCrawler) {
            if (Players.getLocal().getTarget() == null && !Players.getLocal().isMoving()) {
                if (fleshCrawler.getTarget() == null && fleshCrawler.getAnimationId() != 1184 && fleshCrawler.getAnimationId() != 1186) {
                    if (fleshCrawler.interact("Attack")) {
                        getLogger().info("Attacked Flesh Crawler");
                        Execution.delayUntil(() -> Players.getLocal().getAnimationId() == 1156,
                                () -> Players.getLocal().getTarget() != null, 50, 1000, 2000);
                    }
                }
            }
        }

    }
}

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
//        if (baseXp == null) {
//            baseXp = Skill.valueOf("STRENGTH").getExperience();
//        }
//        Integer xpGained = (Skill.STRENGTH.getExperience() - baseXp);
//        getLogger().info("XP gained: " + xpGained);

        LocatableEntityQueryResults<Npc> nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").results().sortByDistance();
        //@todo implement whats targeting me
        LocatableEntityQueryResults<Npc> fleshCrawler1TargetingMe = Npcs.newQuery().targeting(Players.getLocal()).results();
        try {
            getLogger().info("NPC-> Animation " + Players.getLocal().getTarget().getAnimationId());
////            getLogger().info("Player-> Animation " + Players.getLocal().getAnimationId());

        } catch (NullPointerException e) {

        }
        for (Npc fleshCrawler : nearestFleshCrawler) {
            if (Players.getLocal().getTarget() == null && !Players.getLocal().isMoving()) {
                if (fleshCrawler.getTarget() == null) {
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

package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Fight extends LeafTask {

    Npc nearestFleshCrawler;
    private static Integer baseXp;


    @Override
    public void execute() {
        if (baseXp == null) {
            baseXp = Skill.STRENGTH.getExperience();
        }
        Integer xpGained = (Skill.STRENGTH.getExperience() - baseXp);
        getLogger().info("XP gained: " + xpGained);

        nearestFleshCrawler = Npcs.newQuery().names("Flesh Crawler").animations(-1).actions("Attack").results().nearest();

        if (Players.getLocal().getTarget() == null) {
            if (nearestFleshCrawler.interact("Attack")) {
                getLogger().info("Attacked Flesh Crawler");
                // Don't really need to delay here but it does help slow things down a little
                Execution.delayUntil(() -> Players.getLocal().getTarget() == null, () -> Players.getLocal().getTarget() != null, 50, 1000, 3000);
            }
        }
    }
}

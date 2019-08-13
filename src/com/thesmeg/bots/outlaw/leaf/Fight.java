package com.thesmeg.bots.outlaw.leaf;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Fight extends LeafTask {

    Npc nearestOutlaw;
    Player player;
    private static Integer baseXp;


    @Override
    public void execute() {
        if (baseXp == null) {
            baseXp = Skill.STRENGTH.getExperience();
        }
        System.out.println("Base xp: " + baseXp);
        Integer xpGained = (Skill.STRENGTH.getExperience() - baseXp);
        System.out.println("current xp: " + Skill.STRENGTH.getExperience());
        System.out.println("XP gained: " + xpGained);

        if (Camera.getPitch() < 0.8) {
            Camera.concurrentlyTurnTo(1.0);
        }

        nearestOutlaw = Npcs.newQuery().names("Outlaw").actions("Attack").results().nearest();
        player = Players.getLocal();

        if (nearestOutlaw.interact("Attack")) {
            Execution.delayUntil(() -> Inventory.isEmpty(), () -> player.getAnimationId() != -1, 50, 1000, 2000);
        }
    }
}

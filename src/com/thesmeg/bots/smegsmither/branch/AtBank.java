package com.thesmeg.bots.smegsmither.branch;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class AtBank extends BranchTask {
    private SmegSmither smegSmither;

    public AtBank(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public boolean validate() {
//        getLogger().info("AtBank");
        Area edgevilleBank = smegSmither.data.getEdgevilleBank();
        Npc banker = Npcs.newQuery().names("Banker").results().first();
        try {
            if (edgevilleBank.contains(Players.getLocal()) && banker.isVisible()) {
                return true;
            }
        } catch (NullPointerException ignore) {

        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return smegSmither.withdrawMaterials;
    }

    @Override
    public TreeTask failureTask() {
        return smegSmither.runToBank;
    }
}
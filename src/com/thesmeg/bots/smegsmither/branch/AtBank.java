package com.thesmeg.bots.smegsmither.branch;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
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
        String locationToSmelt = smegSmither.settings.getLocationToSmelt();
        Area bankArea = smegSmither.data.getLocationArea(locationToSmelt, "Bank");
        Npc banker = Npcs.newQuery().names("Banker").results().first();
        Player player = Players.getLocal();

        if (banker != null && player != null) {
            return bankArea.contains(player) && banker.isVisible();
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

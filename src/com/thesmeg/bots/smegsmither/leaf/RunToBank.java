package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class RunToBank extends LeafTask {
    private SmegSmither smegSmither;

    public RunToBank(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
        Area bank = smegSmither.data.getEdgevilleBank();
        Npc banker = Npcs.newQuery().names("Banker").results().first();
        if (!bank.contains(Players.getLocal()) && !banker.isVisible()) {
            //@TODO make variable
            smegSmither.lib.webPathToDestination(bank.getRandomCoordinate(), "Edgeville bank", getLogger());
        }
    }
}

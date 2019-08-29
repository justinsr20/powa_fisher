package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.location.Area;
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
        //@TODO make variable
        smegSmither.lib.webPathToDestination(bank.getRandomCoordinate(), "Edgeville bank", getLogger());
    }
}

package com.thesmeg.bots.outlaw.branch;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.outlaw.leaf.GetSupplies;
import com.thesmeg.bots.outlaw.leaf.WalkToBank;

public class InBankArea extends BranchTask {

    Coordinate bottomLeftBank = new Coordinate(3097, 3498, 0);
    Coordinate topRightBank = new Coordinate(3091, 3488, 0);
    Area bankArea = new Area.Rectangular(bottomLeftBank, topRightBank);

    @Override
    public boolean validate() {
        if (bankArea.contains(Players.getLocal())) {
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new GetSupplies();
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToBank();
    }
}

package com.thesmeg.bots.outlaw.branch;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.outlaw.leaf.Fight;
import com.thesmeg.bots.outlaw.leaf.WalkToOutlaw;

public class InOutlawArea extends BranchTask {

    Coordinate bottomLeftOutlaw = new Coordinate(3113, 3469, 0);
    Coordinate topRightOutlaw = new Coordinate(3128, 3480, 0);
    Area outlawArea = new Area.Rectangular(bottomLeftOutlaw, topRightOutlaw);

    @Override
    public boolean validate() {
        if(outlawArea.contains(Players.getLocal())){
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new HaveEnoughHp();
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToOutlaw();
    }


}

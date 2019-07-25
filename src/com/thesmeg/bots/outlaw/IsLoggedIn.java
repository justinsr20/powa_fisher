package com.thesmeg.bots.outlaw;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.outlaw.branch.HaveSupplies;
import com.thesmeg.bots.outlaw.leaf.WaitUntilLoggedIn;

public class IsLoggedIn extends BranchTask {

    Player p;

    @Override
    public boolean validate() {
        p = Players.getLocal();
        if (p != null) {
            if (p.isVisible()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return new WaitUntilLoggedIn();
    }


    @Override
    public TreeTask successTask() {
        return new HaveSupplies();
    }
}

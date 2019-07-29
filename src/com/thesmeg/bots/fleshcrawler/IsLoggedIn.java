package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.branch.HaveSupplies;
import com.thesmeg.bots.fleshcrawler.leaf.WaitUntilLoggedIn;


public class IsLoggedIn extends BranchTask {

    Player p;

    @Override
    public boolean validate() {
        p = Players.getLocal();
        if (p != null) {
            if (p.isVisible()) {
                if (Camera.getPitch() < 0.8) {
                    Camera.concurrentlyTurnTo(1.0);
                }
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

package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsLoggedIn extends BranchTask {
    private FleshCrawler fleshCrawler;

    IsLoggedIn(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    Player p;

    @Override
    public boolean validate() {
        p = Players.getLocal();
        if (p != null && fleshCrawler.foodToEat != null) {
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
        return fleshCrawler.waitUntilLoggedIn;
    }


    @Override
    public TreeTask successTask() {
        return fleshCrawler.haveSupplies;
    }
}

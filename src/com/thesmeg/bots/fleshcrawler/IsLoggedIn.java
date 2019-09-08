package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsLoggedIn extends BranchTask {
    private final double cameraPitch = Random.nextDouble(0.8, 1.0);
    private final int cameraYaw = Random.nextInt(0, 360);
    private FleshCrawler fleshCrawler;
    IsLoggedIn(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public boolean validate() {
        Player p = Players.getLocal();
        if (p != null && fleshCrawler.getFoodToEat() != null) {
            if (p.isVisible()) {
                if (Camera.getPitch() < 0.1) {
                    Camera.concurrentlyTurnTo(cameraYaw, cameraPitch);
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

package com.thesmeg.bots.powafisher;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.branch.Fish;
import com.thesmeg.bots.powafisher.leaf.WaitUntilLoggedIn;

/**
 * NOTES:
 * This is the root node.
 */

public class IsLoggedIn extends BranchTask {

    private Fish fish = new Fish();
    private WaitUntilLoggedIn waitUntilLoggedIn = new WaitUntilLoggedIn();

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
        return waitUntilLoggedIn;
    }

    @Override
    public TreeTask successTask() {
        return fish;
    }

}

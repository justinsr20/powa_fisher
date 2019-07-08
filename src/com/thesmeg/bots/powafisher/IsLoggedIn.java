package com.thesmeg.bots.powafisher;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.branch.IsInventoryFull;
import com.thesmeg.bots.powafisher.leaf.WaitUntilLoggedIn;

/**
 * NOTES:
 * This is the root node.
 */

public class IsLoggedIn extends BranchTask {

    private IsInventoryFull isinventoryfull = new IsInventoryFull();
    private WaitUntilLoggedIn waituntilloggedin = new WaitUntilLoggedIn();

    Player p;

    @Override
    public boolean validate() {
        p = Players.getLocal();
        if (p == null || !p.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TreeTask failureTask() { return waituntilloggedin; }

    @Override
    public TreeTask successTask() {
        return isinventoryfull;
    }

}

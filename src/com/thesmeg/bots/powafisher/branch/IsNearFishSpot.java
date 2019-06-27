package com.thesmeg.bots.powafisher.branch;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.leaf.Logout;
import com.thesmeg.bots.powafisher.leaf.Fish;

//import path.to.your.Fish
//import path.to.your.Logout

/**
 * NOTES:
 * 
 */
public class IsNearFishSpot extends BranchTask {

    private Fish fish = new Fish();
    private Logout logout = new Logout();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return logout;
    }

    @Override
    public TreeTask successTask() {
        return fish;
    }
}

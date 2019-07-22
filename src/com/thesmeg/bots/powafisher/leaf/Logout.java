package com.thesmeg.bots.powafisher.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 */
public class Logout extends LeafTask {

    @Override
    public void execute() {
        Environment.getBot().stop("Could not find a fishing spot, please check settings and character location");
    }
}

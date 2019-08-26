package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.script.framework.tree.LeafTask;

public class EmptyLeaf extends LeafTask {
    @Override
    public void execute() {
        //@todo try figure out who called this class
        getLogger().warn("Reached empty leaf task");
    }
}

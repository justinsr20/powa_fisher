package com.thesmeg.bots.smegsmither.branch;

import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class Root extends BranchTask {
    private SmegSmither smegSmither;

    public Root(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public boolean validate() {
        try {
            return Players.getLocal().isVisible();
        } catch (NullPointerException ignored) {
            return false;
        }
    }

    @Override
    public TreeTask successTask() {
        return smegSmither.emptyLeaf;
    }

    @Override
    public TreeTask failureTask() {
        return smegSmither.emptyLeaf;
    }


}

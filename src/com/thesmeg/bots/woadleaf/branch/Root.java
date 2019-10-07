package com.thesmeg.bots.woadleaf.branch;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.woadleaf.WoadLeaf;

public class Root extends BranchTask {
    private WoadLeaf woadLeaf;

    public Root(WoadLeaf woadLeaf) {
        this.woadLeaf = woadLeaf;
    }

    @Override
    public TreeTask successTask() {
        return null;
    }

    @Override
    public TreeTask failureTask() {
        return null;
    }

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        if (player != null) {
            return player.isVisible();
        }
        return false;
    }
}

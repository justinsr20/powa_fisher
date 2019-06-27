package com.thesmeg.bots.powafisher;

import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class PowaFisher extends TreeBot {

    @Override
    public TreeTask createRootTask() {
        return new IsInventoryFull();
    }
}

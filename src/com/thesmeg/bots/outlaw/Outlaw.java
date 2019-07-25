package com.thesmeg.bots.outlaw;

import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Outlaw extends TreeBot {
    @Override
    public TreeTask createRootTask() {
        return new IsLoggedIn();
    }
}
